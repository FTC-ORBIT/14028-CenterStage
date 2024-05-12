package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.catcher.Catcher;
import org.firstinspires.ftc.teamcode.catcher.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.drive.DriveState;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;
import org.firstinspires.ftc.teamcode.utils.Vector;

@TeleOp(name = "TeleOp14028")
public class TeleOp14028 extends OpMode {
    ElevatorState state;

    DriveState driveState;
    ElapsedTime travelTime = new ElapsedTime();
    boolean shouldTravel;

    @Override
    public void init() {
        // Initialize drive's the hardware map.
        Drive.init(hardwareMap, gamepad1);

        // Initialize gyro's hardware map.
        CHGyro.init(hardwareMap);

        // Initialize elevator's hardware map.
        //Elevator.init(hardwareMap, gamepad1);
        Elevator.init(hardwareMap);

        // Initialize catcher's(Box and Pixel together) hardware map.
        Catcher.init(hardwareMap);

        // Initialize Airplane's hardware map.
        // Airplane.init(hardwareMap);

        Catcher.deplateBox();
        driveState = DriveState.NORMAL;
        state = ElevatorState.INTAKE;
        shouldTravel = false;
    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper && Elevator.getPos() >= Elevator.level1Height) {
            Catcher.dropPixel();
        }   else if (gamepad1.right_stick_y != 0) {
            state = ElevatorState.CONTROLLER;
        }
        else if (gamepad1.right_bumper) {
            Catcher.intakeCatcher();
        } else if (gamepad1.left_bumper && Catcher.isBoxOpen) {
            Catcher.deplateCatcher();
        } else if (gamepad1.a && Elevator.getPos() < 300) {
            driveState = DriveState.SLOW;
            Catcher.intakeBox();
        } else if (gamepad1.x) {
            state = ElevatorState.INTAKE;
            driveState = DriveState.NORMAL;
            Catcher.deplateBox();
            //state = ElevatorState.TRAVEL;
        } else if(gamepad1.dpad_down) {
            state = ElevatorState.LEVEL1;
            Catcher.deplateBox();
        } else if (gamepad1.dpad_right || gamepad1.dpad_left) {
            state = ElevatorState.LEVEL2;
            Catcher.deplateBox();
        } else if (gamepad1.dpad_up) {
            state = ElevatorState.LEVEL3;
            Catcher.deplateBox();
        } else if (gamepad1.back) {
            CHGyro.resetGyro();
        }

        Drive.drive(Drive.slowedDrive(new Vector(gamepad1.left_stick_x, -gamepad1.left_stick_y)),gamepad1.right_trigger- gamepad1.left_trigger, driveState);
        Elevator.operate(state, gamepad1);


    }
}