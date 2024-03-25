package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.catcher.Box;
import org.firstinspires.ftc.teamcode.catcher.Catcher;
import org.firstinspires.ftc.teamcode.catcher.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

@TeleOp(name = "TeleOp14028")
public class TeleOp14028 extends OpMode {
    @Override
    public void init() {
        // Initialize drive's the hardware map.
        Drive.init(hardwareMap, gamepad1);

        // Initialize gyro's hardware map.
        CHGyro.init(hardwareMap);

        // Initialize elevator's hardware map.
        Elevator.init(hardwareMap, gamepad1);

        // Initialize catcher's(Box and Pixel together) hardware map.
        Catcher.init(hardwareMap);

        // Initialize Airplane's hardware map.
        // Airplane.init(hardwareMap);
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.

        Drive.drive(telemetry);

        // check if the controller right y stick has any input, and move t e elevator
        // according to it.
        if (gamepad1.dpad_right) {
            Elevator.setState(ElevatorState.controller);
        }
        // check if the dpad_up button is clicked - set the state of the elevator to up.
        else if (gamepad1.dpad_left) {
            Elevator.level1();
        } else if (gamepad1.dpad_up) {
            Elevator.level2();
        }
        // check if the dpad_down button is clicked - set the state of the elevator to
        // down.
        else if (gamepad1.dpad_down && Elevator.getState() != ElevatorState.downed) {
            Elevator.downedLevel();
        }

        if (gamepad1.a) {
            Catcher.openCatcher();
        } else if (gamepad1.x) {
            Catcher.closeCatcher();
        } else if (gamepad1.b) {
            Elevator.collLevel();
            Catcher.shouldOpenBox = true;
        } else if (gamepad1.y) {
            Catcher.closeBox();
            Catcher.shouldOpenBox = false;
            Elevator.downedLevel();
        }

        if (Catcher.shouldOpenBox && Elevator.isUp() && !Catcher.isBoxOpen) {
            Catcher.openBox();
        }

        // power the motors based on the state of the elevator
        Elevator.stateBased(telemetry);

        /*telemetry.addData("Current elevator height in ticks", Elevator.getCurrentHeight(Elevator.motors[0]));
        telemetry.addData("Current elevator height in ticks", Elevator.getCurrentHeight(Elevator.motors[1]));
        telemetry.addData("Current Elevator State", Elevator.getState());
        telemetry.addData("Current Wanted Height", Elevator.getWantedHeight());*/
        telemetry.addData("Current Pixel Servo Position", Pixel.getPosition());
        telemetry.addData("Current Box Servo Position", Box.getPosition());
        telemetry.update();
    }
}

/*
 * if (gamepad1.x && Elevator.atDown() && !Box.isOpen && !Pixel.isOpen) {
 * Box.changeState();
 * Pixel.open();
 * } else if (gamepad1.x && Elevator.atDown() && Box.isOpen && Pixel.isOpen) {
 * Box.changeState();
 * Pixel.close();
 * } else if (gamepad1.x && Elevator.atUp()) {
 * Pixel.open();
 * }
 */

/*
 * if (gamepad1.y) {
 * Airplane.launch();
 * }
 */