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

        if (gamepad1.a) {
            Catcher.intakeCatcher();
        } else if (gamepad1.x) {
            Catcher.deplateCatcher();
        } else if (gamepad1.b) {
            Elev.setState(ElevatorState.INTAKE);
            Catcher.intakeBox();
        } else if (gamepad1.y) {
            Catcher.deplateBox();
            Elev.setState(ElevatorState.TRAVEL);
        }

        Elev.updateHeight();
        // power the motors based on the state of the elevator
    }
}