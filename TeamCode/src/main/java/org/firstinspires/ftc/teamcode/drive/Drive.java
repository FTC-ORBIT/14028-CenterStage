package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Init;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;
import org.firstinspires.ftc.teamcode.utils.Angle;
import org.firstinspires.ftc.teamcode.utils.Vector;

public class Drive {
    static DcMotor[] motors = new DcMotor[4];

    public static Vector gamepadVector;

    public static void init(HardwareMap hardwareMap) {
        // map all the motors.
        motors[0] = hardwareMap.get(DcMotor.class, "lf");
        motors[1] = hardwareMap.get(DcMotor.class, "rf");
        motors[2] = hardwareMap.get(DcMotor.class, "lb");
        motors[3] = hardwareMap.get(DcMotor.class, "rb");

        // reverse the direction of the motors if needed.
        //motors[0].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[3].setDirection(DcMotorSimple.Direction.REVERSE);

        // set the zero power behavior of the motors to brake.
        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public static void drive(Gamepad gamepad, Telemetry telemetry) {

        // get the right stick input.
        double rx = gamepad.right_stick_x;

        // get the angle of the robot and wrap it to 0-360.
        double robotAngle = Angle.wrapAngle0_360(CHGyro.getAngle());

        // create a vector using the input from the left stick.
        gamepadVector = new Vector(gamepad.left_stick_x, -gamepad.left_stick_y);

        telemetry.addData("robot rotation", robotAngle);

        telemetry.update();

        /*if (Init.elevator.state != ElevatorState.down) {
            gamepadVector = setElevatorBasedSpeed(gamepadVector.x, gamepadVector.y, 0, 0, Init.elevator.currentHeight);
        }*/

        // rotate the vector by minus the angle of the robot(in radians).
        gamepadVector = gamepadVector.rotate(-Math.toRadians(robotAngle));

        // set the power of the motors.
        motors[0].setPower(gamepadVector.y + gamepadVector.x + rx);
        motors[1].setPower(gamepadVector.y - gamepadVector.x - rx);
        motors[2].setPower(gamepadVector.y - gamepadVector.x + rx);
        motors[3].setPower(gamepadVector.y + gamepadVector.x - rx);
    }

    public static Vector setElevatorBasedSpeed(double vXMax, double vYMax,double vMin, double hMax, double currentHeight) {
        return new Vector((vXMax - vMin) / (-hMax) * currentHeight + vXMax, (vYMax - vMin) / (-hMax) * currentHeight + vYMax);
        //return new Vector(0,0);
    }

}