package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Motors;

public class Elevator {
    static DcMotor[] motors;
    public static int currentHeight;
    public static int startHeight;
    public static int wantedHeight;
    public static ElevatorState state;
    static Gamepad gamepad;

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

        wantedHeight = 2000;
    }

    public static void controllerBased() {
        Motors.setPowerMotorList(motors, gamepad.right_stick_y);
    }

    public static void changeState() {
        if (state == ElevatorState.up) {
            up();
        } else if (state == ElevatorState.down) {
            down();
        }
    }

    public static void up() {
        double vMin = 0.1, vMax = 0.3;
        currentHeight = motors[0].getCurrentPosition();

        if (currentHeight <= per1 * wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
        } else if (currentHeight <= per2 * wantedHeight) {
            Motors.setPowerMotorList(motors, vMax);
        } else if (currentHeight > per2 * wantedHeight || currentHeight < wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * wantedHeight);
        }
    }

    public static void down() {
        double vMin = -0.1, vMax = -0.3;
        currentHeight = wantedHeight - motors[0].getCurrentPosition();

        if (currentHeight <= per1 * wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
        } else if (currentHeight <= per2 * wantedHeight) {
            Motors.setPowerMotorList(motors, vMax);
        } else if (currentHeight > per2 * wantedHeight || currentHeight < wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * wantedHeight);
        }
    }

}
