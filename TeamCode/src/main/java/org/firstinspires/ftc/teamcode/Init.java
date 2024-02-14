package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.airplane.Airplane;
import org.firstinspires.ftc.teamcode.box_and_pixel.Box;
import org.firstinspires.ftc.teamcode.box_and_pixel.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;

@TeleOp(name = "TeleOp1428")
public class Init extends OpMode {
    public static GameState gameState;

    @Override
    public void init() {
        // Initialize drive's the hardware map.
        //Drive.init(hardwareMap);

        // Initialize gyro's hardware map.
        //CHGyro.init(hardwareMap);

        // Initialize elevator's hardware map.
        Elevator.init(hardwareMap, gamepad1);

        // Initialize box's hardware map.
        //Box.init(hardwareMap);

        // Initialize pixel's hardware map.
        //Pixel.init(hardwareMap);

        // Initialize pixel's hardware map.
        //Airplane.init(hardwareMap);
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.

        //Drive.drive(gamepad1, telemetry);

        telemetry.addData("Current elevator height in ticks", Elevator.currentHeight);

        if (gamepad1.dpad_up /*&& Elevator.state != ElevatorState.up*/) {
            Elevator.state = ElevatorState.up;
            Elevator.wantedHeight = 1000;
        } else if (gamepad1.dpad_down /*&& Elevator.state != ElevatorState.down*/) {
            Elevator.state = ElevatorState.down;
            Elevator.wantedHeight = 100;
        }

        //Elevator.controllerBased();
        Elevator.stateBased();

        telemetry.addData("Current elevator height in ticks", Elevator.currentHeight);
        telemetry.update();
    }

    /*if (gamepad1.x && Elevator.atDown() && !Box.isOpen && !Pixel.isOpen) {
            Box.changeState();
            Pixel.open();
        } else if (gamepad1.x && Elevator.atDown() && Box.isOpen && Pixel.isOpen) {
            Box.changeState();
            Pixel.close();
        } else if (gamepad1.x && Elevator.atUp()) {
            Pixel.open();
        }*/

        /*if (gamepad1.y) {
            Airplane.launch();
        }*/
}
