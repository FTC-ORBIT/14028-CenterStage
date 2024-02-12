package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.box_and_pixel.Box;
import org.firstinspires.ftc.teamcode.box_and_pixel.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.elevator.ElevatorState;

@TeleOp(name = "TeleOp1428")
public class Init extends OpMode {

    public static Pixel pixel = new Pixel();

    public static GameState gameState;

    @Override
    public void init() {
        // Initialize drive's and input the hardware map.
        //Drive.init(hardwareMap);

        // Initialize gyro's input the hardware map.
        //CHGyro.init(hardwareMap);

        // Initialize elevator's input the hardware map.
        Elevator.init(hardwareMap, gamepad1);

        // Initialize box's input the hardware map.
        //Box.init(hardwareMap);

        // Initialize pixel's input the hardware map.
        //pixel.init(hardwareMap);
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.

        //Drive.drive(gamepad1, telemetry);

        telemetry.addData("Current elevator height in ticks", Elevator.currentHeight);

        if (gamepad1.a && Elevator.state == ElevatorState.down) {
            Elevator.state = ElevatorState.up;
        } else if (gamepad1.a && Elevator.state == ElevatorState.up) {
            Elevator.state = ElevatorState.down;
        }

        Elevator.controllerBased();
        //Elevator.heightState();

        //if (Elevator.currentHeight == Box.changePositionHeight) Box.controlState();

        telemetry.addData("Current elevator height in ticks", Elevator.currentHeight);
        telemetry.update();
    }
}
