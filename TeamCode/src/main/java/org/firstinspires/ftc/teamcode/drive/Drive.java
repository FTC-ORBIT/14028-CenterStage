package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;
import org.firstinspires.ftc.teamcode.utils.Angle;
import org.firstinspires.ftc.teamcode.utils.Vector;

public class Drive {
    static DcMotor[] motors = new DcMotor[4];

    public static Vector gamepadVector;
    static Gamepad gamepad;

    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        Drive.gamepad = gamepad;

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

    public static void drive(Telemetry telemetry) {

        // get the right stick input.
        double rx = gamepad.right_stick_x;

        // get the angle of the robot and wrap it to 0-360.
        double robotAngle = Angle.wrapAngle0_360(CHGyro.getAngle());

        // create a vector using the input from the left stick.
        gamepadVector = new Vector(gamepad.left_stick_x, -gamepad.left_stick_y);

        telemetry.addData("robot rotation", robotAngle);

        telemetry.update();

        gamepadVector = setElevatorBasedSpeed(Elevator.getWantedHeight(), Elevator.getCurrentHeight(Elevator.motors[0]));

        // rotate the vector by minus the angle of the robot(in radians).
        gamepadVector = gamepadVector.rotate(-Math.toRadians(robotAngle));

        // set the power of the motors.
        motors[0].setPower(gamepadVector.y + gamepadVector.x + gamepad.left_trigger - gamepad.right_trigger);
        motors[1].setPower(gamepadVector.y - gamepadVector.x - gamepad.left_trigger + gamepad.right_trigger);
        motors[2].setPower(gamepadVector.y - gamepadVector.x + gamepad.left_trigger - gamepad.right_trigger);
        motors[3].setPower(gamepadVector.y + gamepadVector.x - gamepad.left_trigger - gamepad.right_trigger);
    }

    public static Vector setElevatorBasedSpeed(double maxHeight, double currentHeight) {
        // If the elevator is in the downed state, the speed of the robot remains the same.
        if (Elevator.getState() == ElevatorState.downed) {
            return new Vector(gamepadVector.x, gamepadVector.y);
        }

        // If the current height of the elevator is greater than or equal to the maximum height,
        // the speed of the robot is set to 0.2 volt in the direction of the gamepad.
        if (currentHeight >= maxHeight) {
            gamepadVector.x = Math.abs(gamepadVector.x) > 0 ? 0.2 * Math.signum(gamepadVector.x) : 0;
            gamepadVector.y = Math.abs(gamepadVector.x) > 0 ? 0.2 * Math.signum(gamepadVector.y) : 0;

            return new Vector(gamepadVector.x , gamepadVector.y);
        }

        // If the current height of the elevator is less than the maximum height,
        // the speed of the robot is adjusted proportionally based on the current height and the maximum height.
        return new Vector((gamepadVector.x / -maxHeight) * currentHeight, (gamepadVector.y / -maxHeight) * currentHeight);
    }

}