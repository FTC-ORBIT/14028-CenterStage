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
        Elevator.init(hardwareMap, gamepad1);
        //Elev.init(hardwareMap);

        // Initialize catcher's(Box and Pixel together) hardware map.
        Catcher.init(hardwareMap);

        // Initialize Airplane's hardware map.
        // Airplane.init(hardwareMap);

        state = ElevatorState.TRAVEL;
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.
        Drive.drive(telemetry);

//        if (gamepad1.a) {
//            //Catcher.intakeCatcher();
//        } else if (gamepad1.x) {
//            //Catcher.deplateCatcher();
//        } else if (gamepad1.b) {
//            Elev.goToPosition(1000);
//            //state = ElevatorState.INTAKE;
//            //Catcher.intakeBox();
//        } else if (gamepad1.y) {
//            //Catcher.deplateBox();
//            state = ElevatorState.TRAVEL;
//            Elev.goToPosition(15);
//        }

//        Elev.setElevatorPower(gamepad1.right_stick_y);
//        Elev.operate(state);

        if (gamepad1.b) {
            Elevator.intakeLevel();
        } else if (gamepad1.y) {
            Elevator.downedLevel();
        }

        Elevator.stateBased(telemetry);

        telemetry.addData("Elevator Height", Elevator.getPos(Elevator.motors[0]));
    }
}