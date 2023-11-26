package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Test Movement opmode")
public class TestOpMode extends OpMode {

    DcMotor lf;
    DcMotor rf;
    DcMotor lb;
    DcMotor rb;
    DcMotor motors[] = new DcMotor[4];

    @Override
    public void init() {
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

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        //learning exactly how everything works
        telemetry.addData(Double.toString(y), "left y joystick input");
        telemetry.addData(Double.toString(x), "left x joystick input");
        telemetry.addData(Double.toString(rx), "right x joystick input");

        motors[0].setPower(y + x + rx);
        motors[1].setPower(y - x - rx);
        motors[2].setPower(y - x + rx);
        motors[3].setPower(y + x - rx);
    }

}
