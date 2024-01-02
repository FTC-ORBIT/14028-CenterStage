package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

public class Drive {
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
        double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double rAngle = chGyro.getAngle();

        //learning exactly how everything works
        telemetry.addData("left y joystick input", Double.toString(y));
        telemetry.addData("left x joystick input", Double.toString(x));
        telemetry.addData("right x joystick input", Double.toString(rx));
        telemetry.addData("rotating of the robot", Double.toString(rAngle));

        double[] vector = rotateVector(rAngle, x, y);

        y = vector[0];
        x = vector[1];

        telemetry.addData("x", x);
        telemetry.addData("y", y);

        telemetry.update();

        for (DcMotor motor : motors) {
            if (motor.getPower() > 1) {
                motors[0].setPower(motors[0].getPower() / motor.getPower());
                motors[1].setPower(motors[1].getPower() / motor.getPower());
                motors[2].setPower(motors[2].getPower() / motor.getPower());
                motors[3].setPower(motors[3].getPower() / motor.getPower());
            }
        }

        motors[0].setPower(y + x + rx);
        motors[1].setPower(y - x - rx);
        motors[2].setPower(y - x + rx);
        motors[3].setPower(y + x - rx);
    }

    private static double[] rotateVector(double rAngle, double x, double y) {
        x = Math.cos(rAngle)*x - Math.sin(rAngle)*y;
        y = Math.sin(rAngle)*x + Math.cos(rAngle)*y;

        double[] vector = {y,x};

        return vector;
    }
}