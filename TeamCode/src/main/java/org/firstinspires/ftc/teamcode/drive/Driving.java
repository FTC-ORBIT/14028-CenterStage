package org.firstinspires.ftc.teamcode.drive;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

public class Driving {
    DcMotor motors[] = new DcMotor[4];

    CHGyro chGyro = new CHGyro();

    public void init(HardwareMap hardwareMap) {
        motors[0] = hardwareMap.get(DcMotor.class, "lf");
        motors[1] = hardwareMap.get(DcMotor.class, "rf");
        motors[2] = hardwareMap.get(DcMotor.class, "lb");
        motors[3] = hardwareMap.get(DcMotor.class, "rb");
        motors[0].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[3].setDirection(DcMotorSimple.Direction.REVERSE);
        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


    }

    public void drive(Gamepad gamepad1, Telemetry telemetry) {
        float y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
        float x = gamepad1.left_stick_x;
        float rx = gamepad1.right_stick_x;

        //learning exactly how everything works
        telemetry.addData("left y joystick input", Double.toString(y));
        telemetry.addData("left x joystick input", Double.toString(x));
        telemetry.addData("right x joystick input", Double.toString(rx));
        telemetry.addData("rotating of the robot", Float.toString((float) chGyro.getAngle()));

        float cAngle = (float) Math.atan(x / y);
        cAngle = (float) Math.toDegrees(cAngle);

        telemetry.addData("controller angle", Float.toString(cAngle));

        x = (float) Math.cos(Math.toRadians(cAngle - chGyro.getAngle())) * distance(x, y);

        telemetry.addData("x", x);
        telemetry.addData("y", y);

        telemetry.update();

        motors[0].setPower(y + x + rx);
        motors[1].setPower(y - x - rx);
        motors[2].setPower(y - x + rx);
        motors[3].setPower(y + x - rx);
    }

    private static float distance(float x, float y) {
        return (float) Math.sqrt(x + y);
    }
}