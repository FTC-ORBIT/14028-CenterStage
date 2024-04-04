package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.catcher.Box;
import org.firstinspires.ftc.teamcode.catcher.Catcher;
import org.firstinspires.ftc.teamcode.catcher.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.elevator.Elev;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

@TeleOp(name = "TeleOp14028")
public class TeleOp14028 extends OpMode {
    ElevatorState state;
    @Override
    public void init() {
        // Initialize drive's the hardware map.
        Drive.init(hardwareMap, gamepad1);

        // Initialize gyro's hardware map.
        CHGyro.init(hardwareMap);

        // Initialize elevator's hardware map.
        //Elevator.init(hardwareMap, gamepad1);
        Elev.init(hardwareMap);
        // Initialize catcher's(Box and Pixel together) hardware map.
        Catcher.init(hardwareMap);

        // Initialize Airplane's hardware map.
        // Airplane.init(hardwareMap);
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.
        Drive.drive(telemetry);

//        // check if the controller right y stick has any input, and move t e elevator
//        // according to it.
//        if (gamepad1.dpad_right) {
//            Elevator.setState(ElevatorState.controller);
//        }
//        // check if the dpad_up button is clicked - set the state of the elevator to up.
//        else if (gamepad1.dpad_left) {
//            Elevator.level1();
//        } else if (gamepad1.dpad_up) {
//            Elevator.level2();
//        }
//        // check if the dpad_down button is clicked - set the state of the elevator to
//        // down.
//        else if (gamepad1.dpad_down && !Elevator.isDown()) {
//            Elevator.downedLevel();
//        }

        if (gamepad1.a) {
            Catcher.intakeCatcher();
        } else if (gamepad1.x) {
            Catcher.deplateCatcher();
        } else if (gamepad1.b) {
            Elevator.intakeLevel();
        } else if (gamepad1.y) {
            Catcher.deplateBox();
        } else if (gamepad1.dpad_down) {
            state = ElevatorState.TRAVEL;
        } else if (gamepad1.dpad_up) {
            state = ElevatorState.INTAKE;
        }
        Elev.setElevatorPower(gamepad1.right_stick_y);
        //Elev.operate(state);
        // power the motors based on the state of the elevator

//        telemetry.addData("elevator height in ticks", Elevator.getCurrentHeight(Elevator.motors[0]));
//        telemetry.addData("elevator height in ticks", Elevator.getCurrentHeight(Elevator.motors[1]));
//        telemetry.addData("Elevator State", Elevator.getState());
//        telemetry.addData("Wanted Height", Elevator.getWantedHeight());
        telemetry.addData("Pixel Servo Position", Pixel.getPosition());
        telemetry.addData("Box Servo Position", Box.getPosition());
        //telemetry.addData("height", Elevator.getPos());
        telemetry.update();
    }
}