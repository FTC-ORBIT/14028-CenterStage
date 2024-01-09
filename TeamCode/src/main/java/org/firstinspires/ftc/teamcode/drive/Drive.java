package org.firstinspires.ftc.teamcode.drive;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.google.ftcresearch.tfod.tracking.ObjectTracker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;
import org.firstinspires.ftc.teamcode.utils.Angle;
import org.firstinspires.ftc.teamcode.utils.Vector;

public class Drive {
    DcMotor motors[] = new DcMotor[4];

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
        double rx = gamepad1.right_stick_x;

        double rAngle = CHGyro.getAngle();

        Vector gamepadVector = new Vector(gamepad1.left_stick_x, -gamepad1.left_stick_y);

        //learning exactly how everything works
        telemetry.addData("left y joystick input", Double.toString(gamepadVector.y));
        telemetry.addData("left x joystick input", Double.toString(gamepadVector.x));
        telemetry.addData("right x joystick input", Double.toString(rx));
        telemetry.addData("rotating of the robot", Double.toString(Angle.wrapAngle0_360(rAngle)));

        gamepadVector = gamepadVector.rotate(-Math.toRadians(Angle.wrapAngle0_360(rAngle)));


        telemetry.addData("x", gamepadVector.x);
        telemetry.addData("y", gamepadVector.y);

        telemetry.update();

        motors[0].setPower(gamepadVector.y + gamepadVector.x + rx);
        motors[1].setPower(gamepadVector.y - gamepadVector.x - rx);
        motors[2].setPower(gamepadVector.y - gamepadVector.x + rx);
        motors[3].setPower(gamepadVector.y + gamepadVector.x - rx);
    }
}