package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.box_and_pixel.Box;
import org.firstinspires.ftc.teamcode.box_and_pixel.Pixel;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

@TeleOp(name = "Movement Opmode")
public class Init extends OpMode {

    public static Drive drive = new Drive();
    public static Elevator elevator = new Elevator();
    public static Box box = new Box();
    public static Pixel pixel = new Pixel();

    public static GameState gameState;

    @Override
    public void init() {
        // Initialize drive's and input the hardware map.
        //drive.init(hardwareMap);

        // Initialize gyro's input the hardware map.
        //CHGyro.init(hardwareMap);

        // Initialize elevator's input the hardware map.
        elevator.init(hardwareMap, gamepad1);

        // Initialize box's input the hardware map.
        //box.init(hardwareMap);

        // Initialize pixel's input the hardware map.
        //pixel.init(hardwareMap);

        telemetry.addData("start height", elevator.startHeight);
        telemetry.update();

        gameState = GameState.pickup_closed;
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.

        drive.drive(gamepad1, telemetry);

        telemetry.addData("Current elevator height in ticks", elevator.currentHeight);
    }
}
