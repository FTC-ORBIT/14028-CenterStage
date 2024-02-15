package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.Motors;

public class Elevator {
    static DcMotor[] motors;
    public static int currentHeight;
    public static int wantedHeight;
    public static ElevatorState state;
    static Gamepad gamepad;
    static ElapsedTime downTimer = new ElapsedTime();
    static double per1 = 0.15;
    static double per2 = 0.9;

    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        Elevator.gamepad = gamepad;

        motors = new DcMotor[1];
        motors[0] = hardwareMap.get(DcMotor.class, "el");
        motors[1] = hardwareMap.get(DcMotor.class, "er");

        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        state = ElevatorState.downing;
    }

    public static void controllerBased() {
        Motors.setPowerMotorList(motors, gamepad.right_stick_y);
    }

    public static void stateBased() {
        // check if the state is up - put the power to go up
        switch (state) {
            case uping:
                up();
            // check if the state is downing - put the power to go downing
            // if the state is downed then this wont be executed because the elevator is at the bottom.
            case downing:
                down();
        }
    }

    public static void up() {
        double vMin = 0.1, vMax = 0.4;
        currentHeight = getCurrentHeight();

        // if the elevator is at or above wanted height
        if (currentHeight >= wantedHeight) {
            Motors.setPowerMotorList(motors, 0);
        }
        // below some precent have a different power.
        else if (currentHeight <= per1 * wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);

        } else if (currentHeight <= per2 * wantedHeight && currentHeight >= wantedHeight * per1) {
            Motors.setPowerMotorList(motors, vMax);

        } else {
            Motors.setPowerMotorList(motors, (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * wantedHeight);
        }
    }

    public static void down() {
        // setting min and max power, and timer time and power.
        double vMin = -0.1, vMax = -0.3;
        double time = 0.1, timerPower = 0.1;
        currentHeight = getCurrentHeight();

        // go the rest of the way there with timer.
        if (currentHeight == wantedHeight) {
            downTimer.reset();
            Motors.setPowerMotorList(motors, timerPower);
        }

        // if the timer is done then the elevator is at the bottom and the power of the motors should be 0.
        if (downTimer.seconds() == time) {
            Motors.setPowerMotorList(motors, 0);
            state = ElevatorState.downed;
        }

        // if the elevator is above the wanted height - insert the power based on height.
        if (currentHeight > wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin) / (currentHeight - wantedHeight) * currentHeight + vMin);
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

    public static int getCurrentHeight() {
        return currentHeight = (Math.abs(motors[0].getCurrentPosition()) + Math.abs(motors[1].getCurrentPosition()))/2;
    }
}
