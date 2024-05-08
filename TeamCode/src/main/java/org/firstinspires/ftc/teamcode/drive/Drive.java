package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;
import org.firstinspires.ftc.teamcode.utils.Angle;
import org.firstinspires.ftc.teamcode.utils.Vector;

public class Drive {
    static DcMotor[] motors = new DcMotor[4];

    public static Vector gamepadVector;
    static Gamepad gamepad;

    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        Drive.gamepad = gamepad;

        motors[0] = hardwareMap.get(DcMotor.class, "lf");
        motors[1] = hardwareMap.get(DcMotor.class, "rf");
        motors[2] = hardwareMap.get(DcMotor.class, "lb");
        motors[3] = hardwareMap.get(DcMotor.class, "rb");

        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[3].setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public static void drive() {
        double rx = gamepad.right_trigger- gamepad.left_trigger;

        double robotAngle = Angle.wrapAngle0_360(CHGyro.getAngle());

        gamepadVector = new Vector(gamepad.left_stick_x, -gamepad.left_stick_y);
        gamepadVector = gamepadVector.rotate(-Math.toRadians(robotAngle));

        //gamepadVector = gamepadVector.scale(Math.max(0.34, (2000 - Elevator.getPos()) / 2000));

        motors[0].setPower(gamepadVector.y + gamepadVector.x + rx);
        motors[1].setPower(gamepadVector.y - gamepadVector.x - rx);
        motors[2].setPower(gamepadVector.y - gamepadVector.x + rx);
        motors[3].setPower(gamepadVector.y + gamepadVector.x - rx);
    }
}