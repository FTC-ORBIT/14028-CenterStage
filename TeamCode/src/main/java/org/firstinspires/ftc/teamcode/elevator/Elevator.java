package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.State;
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
    static double per1 = 0.15;
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

    public static void level0() {
        setWantedHeight(15);
        setState(ElevatorState.downing);
    }
    public static void level1() {
        setWantedHeight(1000);
        setState(ElevatorState.uping);
    }
    public static void level2() {
        setWantedHeight(1500);
        setState(ElevatorState.uping);
    }

    public static void level3() {
        setWantedHeight(1700);
        setState(ElevatorState.uping);
    }
    public static void stateBased(Telemetry telemetry) {

        switch (state) {
            // switch to controller.
            case controller:
                controllerBased();
                break;

            // check if the state is up - put the power to go up.
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

    public static void controllerBased() {
        // control the height using the controller.
        Motors.setPowerMotorList(motors, gamepad.right_stick_y);
    }

    static void up(DcMotor motor) {
        // setting min and max volt
        double vMin = 0.6, vMax = 0.9;
        currentHeight = getCurrentHeight(motor);

        // if the elevator is at or above wanted height
        if (currentHeight >= getWantedHeight()) {
            //Motors.setPowerMotorList(motors, 0);
            motor.setPower(0);
        }
        // below some precent have a different power.
        else if (currentHeight <= per1 * getWantedHeight()) {
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

    static void down(DcMotor motor) {
        // setting min and max volt, and timer time and power.
        double vMin = -0.4, vMax = -0.7;

        currentHeight = getCurrentHeight(motor);

        if (currentHeight <= getWantedHeight()) {
            motor.setPower(0);
            state = ElevatorState.downed;

        }
        // above some precent have a different power.
        else if (currentHeight >= per2 * getWantedHeight()) {
            //Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
            motor.setPower((vMax - vMin)/((getWantedHeight() * per2) - getWantedHeight()) * currentHeight + (vMin * per2 - vMax)/(per2 - 1));

        } else if (currentHeight >= per2 * getWantedHeight() && currentHeight < getWantedHeight() * per1) {
            //Motors.setPowerMotorList(motors, vMax);
            motor.setPower(vMax);

        } else {
            //Motors.setPowerMotorList(motors, (vMax - vMin)/((getWantedHeight() * per2) - getWantedHeight()) * currentHeight + (vMin * per2 - vMax)/(per2 - 1));
            motor.setPower((vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);

        }

        // if the elevator is above the wanted height - insert the power based on height.
//        if (currentHeight > wantedHeight) {
//            //Motors.setPowerMotorList(motors, (vMax - vMin) / (currentHeight - wantedHeight) * currentHeight + vMin);
//        }

//        if (getCurrentHeight(motor) <= getWantedHeight()) {
//            Motors.setPowerMotorList(motors, 0);
//            state = ElevatorState.downed;
//        }
    }

    static void proportion() {
        // check if the state of elevator is downed.
        if (state == ElevatorState.downed) {
            // if true - stop running function.
            return;
        }

        // calculate the difference between the encoders.
        int dif = getCurrentHeight(motors[0]) - getCurrentHeight(motors[1]);

        // multiply the difference by a small number.
        double prop = dif * 0.001;

        // check if the absolute value of the proportion is bigger then num.
        if (Math.abs(prop) > 0.1) {
            // multiply the prop by the absolute value of prop which can be -1 and 1.
            prop = 0.1 * Math.signum(prop);
        }

        // check the state to know if to remove or add to the motor power.
        switch (state) {
            case uping:
                motors[0].setPower(motors[0].getPower() - prop);
                motors[1].setPower(motors[1].getPower() + prop);
                break;
            case downing:
                motors[0].setPower(motors[0].getPower() + prop);
                motors[1].setPower(motors[1].getPower() - prop);
                break;
        }
    }

    // check if the elevator is at or higher than the wanted height.
    public static boolean atUp() {
        if (currentHeight >= wantedHeight && getState() == ElevatorState.uping) {
            return true;
        }
        return false;
    }

    // check if the elevator at the bottom.
    public static boolean atDown() {
        if (getState() == ElevatorState.downed) {
            return true;
        }
        return false;
    }

    // get the current state.
    public static ElevatorState getState() { return state; }

    // set the current state.
    public static void setState(ElevatorState state) { Elevator.state = state; }

    // get the wanted wanted height.
    public static int getWantedHeight() { return wantedHeight; }

    // set the wanted height.

    /*public static void setWantedHeight(int wantedHeightCm) {
        double ticksPerRevolution = 0.96;
        int wheelCircumference = 435;

        double ticksPerCm = ticksPerRevolution / wheelCircumference;
        wantedHeight = (int) (wantedHeightCm * ticksPerCm);
    }*/

    public static void setWantedHeight(int wantedHeight) {
        Elevator.wantedHeight = wantedHeight;
    }

    // get current height.
    public static int getCurrentHeight(DcMotor motor) { return currentHeight = Math.abs(motor.getCurrentPosition()) - startHeight; }

}
