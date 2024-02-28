package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.Motors;
import org.firstinspires.ftc.teamcode.utils.PID;

public class Elevator {
    public static DcMotor[] motors;
    static int currentHeight;
    static int wantedHeight;
    static int startHeight;
    static ElevatorState state;
    static Gamepad gamepad;
    static ElapsedTime downTimer = new ElapsedTime();
    static double per1 = 0.1;
    static double per2 = 0.9;

    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        Elevator.gamepad = gamepad;

        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotor.class, "el");
        motors[1] = hardwareMap.get(DcMotor.class, "er");

        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        startHeight = getCurrentHeight(motors[0]);

        state = ElevatorState.downed;
    }

    public static void controllerBased() {
        // control the height using the controller.
        Motors.setPowerMotorList(motors, gamepad.right_stick_y);
    }

    public static void stateBased(Telemetry telemetry) {
        // getting the average height of the elevators.

        // check if the state is up - put the power to go up.
        switch (state) {
            case uping:
                up(motors[0]);
                up(motors[1]);
                break;
            // check if the state is downing - put the power to go downing.
            // *if the state is downed then this wont be executed because the elevator is at the bottom*
            case downing:
                down(motors[0]);
                down(motors[1]);
                break;
        }

        proportion();
    }

    public static void up(DcMotor motor) {
        // setting min and max volt
        double vMin = 0.6, vMax = 0.9;
        currentHeight = getCurrentHeight(motor);

        // if the elevator is at or above wanted height
        if (currentHeight >= getWantedHeight() - 30) {
            //Motors.setPowerMotorList(motors, 0);
            motor.setPower(0);
        }
        // below some precent have a different power.
        if (currentHeight <= per1 * getWantedHeight()) {
            //Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
            motor.setPower((vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
        } else if (currentHeight <= per2 * getWantedHeight() && currentHeight > getWantedHeight() * per1) {
            //Motors.setPowerMotorList(motors, vMax);
            motor.setPower(vMax);
        } else {
            //Motors.setPowerMotorList(motors, (vMax - vMin)/((getWantedHeight() * per2) - getWantedHeight()) * currentHeight + (vMin * per2 - vMax)/(per2 - 1));
            motor.setPower((vMax - vMin)/((getWantedHeight() * per2) - getWantedHeight()) * currentHeight + (vMin * per2 - vMax)/(per2 - 1));
        }
    }

    static void proportion() {
        int dif = getCurrentHeight(motors[0]) - getCurrentHeight(motors[1]);

        double prop = dif * 0.005;

        if (prop > 0.3) {
            prop = 0.3;
        }

        if (state == ElevatorState.downed) {
            return;
        }

        switch (state) {
            case uping:
                motors[0].setPower(motors[0].getPower() - prop);
                motors[1].setPower(motors[1].getPower() + prop);
                break;
            case downing:
                motors[0].setPower(motors[0].getPower() + prop);
                motors[1].setPower(motors[1].getPower() - prop);
        }
    }

    public static void down(DcMotor motor) {
        // setting min and max volt, and timer time and power.
        double vMin = -0.5, vMax = -0.8;
        double time = 0.5, timerPower = -0.8;

        currentHeight = getCurrentHeight(motor);

        // if the elevator is above the wanted height - insert the power based on height.
        if (currentHeight > wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin) / (currentHeight - wantedHeight) * currentHeight + vMin);
            return;
        }

        if (getCurrentHeight(motors[0]) <= getWantedHeight()) {
            Motors.setPowerMotorList(motors, 0);
            state = ElevatorState.downed;
        }
    }

    public static boolean atUp() {
        if (currentHeight >= wantedHeight && Elevator.state == ElevatorState.uping) {
            return true;
        }
        return false;
    }
    public static boolean atDown() {
        if (state == ElevatorState.downed) {
            return true;
        }
        return false;
    }

    public static ElevatorState getState() {
        return state;
    }

    public static void setState(ElevatorState newState) {
        state = newState;
    }

    public static void setWantedHeight(int newWantedHeight) {
        wantedHeight = newWantedHeight;
    }

    public static int getWantedHeight() { return wantedHeight; }

    public static int getCurrentHeight(DcMotor motor) {
        return currentHeight = Math.abs(motor.getCurrentPosition()) - startHeight;
    }
}
