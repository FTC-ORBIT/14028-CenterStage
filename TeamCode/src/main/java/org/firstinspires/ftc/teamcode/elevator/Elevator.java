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
    static boolean isTimerOn = false;

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

        state = ElevatorState.down;

        wantedHeight = 1000;
    }

    public static void controllerBased() {
        Motors.setPowerMotorList(motors, gamepad.right_stick_y);
    }

    public static void changeState() {
        switch (state) {
            case up:
                up();
            case down:
                down();
        }
    }

    public static void up() {
        double vMin = 0.1, vMax = 0.3;
        currentHeight = Math.abs(motors[0].getCurrentPosition());

        state = ElevatorState.up;

        if (currentHeight >= wantedHeight) {
            Motors.setPowerMotorList(motors, 0);
        } else if (currentHeight <= per1 * wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
        } else if (currentHeight <= per2 * wantedHeight && currentHeight <= wantedHeight * per1) {
            Motors.setPowerMotorList(motors, vMax);
        } else {
            Motors.setPowerMotorList(motors, (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * wantedHeight);
        }
    }

    public static void down() {
        double vMin = -0.1, vMax = -0.3;
        currentHeight = Math.abs(motors[0].getCurrentPosition());

        state = ElevatorState.down;

        if (currentHeight <= wantedHeight && currentHeight > 0) {
            // go the rest of the way there with timer
            if (currentHeight == wantedHeight && !isTimerOn) {
                downTimer.reset();
                isTimerOn = true;
                Motors.setPowerMotorList(motors, 0);
            }

            if (downTimer.seconds() == 0.1) {
                Motors.setPowerMotorList(motors, 0);
                isTimerOn = false;
            }

        } else {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(currentHeight-wantedHeight) * currentHeight + vMin);
        }
    }

    public static boolean atUp() {
        if (currentHeight >= wantedHeight && Elevator.state == ElevatorState.up) {
            return true;
        }
        return false;
    }
    public static boolean atDown() {
        if (currentHeight >= wantedHeight && Elevator.state == ElevatorState.down) {
            return true;
        }
        return false;
    }
}
