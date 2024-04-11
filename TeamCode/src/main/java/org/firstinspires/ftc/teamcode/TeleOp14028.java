package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.catcher.Catcher;
import org.firstinspires.ftc.teamcode.catcher.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
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
        Elevator.init(hardwareMap);

        // Initialize catcher's(Box and Pixel together) hardware map.
        Catcher.init(hardwareMap);

        // Initialize Airplane's hardware map.
        // Airplane.init(hardwareMap);

        Catcher.deplateBox();
        state = ElevatorState.TRAVEL;
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.
        Drive.drive(telemetry);

        if (gamepad1.right_bumper) {
            Catcher.intakeCatcher();
        } else if (gamepad1.left_bumper) {
            Catcher.deplateCatcher();
        } else if (gamepad1.a) {
            state = ElevatorState.INTAKE;
            Catcher.intakeBox();
        } else if (gamepad1.x) {
            Catcher.deplateBox();
            state = ElevatorState.TRAVEL;
        } else if(gamepad1.dpad_down) {
            state = ElevatorState.LEVEL1;
        } else if (gamepad1.dpad_right || gamepad1.dpad_left) {
            state = ElevatorState.LEVEL2;
        } else if (gamepad1.back) {
            CHGyro.resetGyro();
        }

        Elevator.operate(state);
    }
}