package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.box_and_pixel.Box;
import org.firstinspires.ftc.teamcode.box_and_pixel.Pixel;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;

import java.lang.reflect.Array;

@TeleOp(name = "TeleOp1428")
public class Init extends OpMode {
    @Override
    public void init() {
        // Initialize drive's the hardware map.
        // Drive.init(hardwareMap);

        // Initialize gyro's hardware map.
        // CHGyro.init(hardwareMap);

        // Initialize elevator's hardware map.
        //Elevator.init(hardwareMap, gamepad1);

        // Initialize box's hardware map.
        Box.init(hardwareMap);

        // Initialize pixel's hardware map.
        Pixel.init(hardwareMap);

        // Initialize pixel's hardware map.
        // Airplane.init(hardwareMap);
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.

        // Drive.drive(gamepad1, telemetry);

        // check if the controller right y stick has any input, and move t e elevator
        // according to it.
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
//        else if (gamepad1.dpad_down && Elevator.getState() != ElevatorState.downed) {
//            Elevator.level0();
//        }

        if (gamepad1.a) {
            Pixel.open();
            //Box.close();
        } else if (gamepad1.x) {
            Pixel.close();
            //Box.close();
        }

        if (gamepad1.b) {
            Box.open();
            Pixel.boxOpen();
        } else if (gamepad1.y) {
            Box.close();
            Pixel.close();
        }

        // power the motors based on the state of the elevator
        //Elevator.stateBased(telemetry);

        /*telemetry.addData("Current elevator height in ticks", Elevator.getCurrentHeight(Elevator.motors[0]));
        telemetry.addData("Current elevator height in ticks", Elevator.getCurrentHeight(Elevator.motors[1]));
        telemetry.addData("Current Elevator State", Elevator.getState());
        telemetry.addData("Current Wanted Height", Elevator.getWantedHeight());*/
        telemetry.addData("Current Box Servo Position", Pixel.getPosition());
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