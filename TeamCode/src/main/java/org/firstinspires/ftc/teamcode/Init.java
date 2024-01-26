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
        drive.init(hardwareMap);

        // Initialize gyro's input the hardware map.
        CHGyro.init(hardwareMap);

        // Initialize elevator's input the hardware map.
        elevator.init(hardwareMap);

        // Initialize box's input the hardware map.
        box.init(hardwareMap);

        // Initialize pixel's input the hardware map.
        pixel.init(hardwareMap);

        gameState = GameState.pickup_closed;
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.
        drive.drive(gamepad1, telemetry);

        if (gamepad1.a && gameState == GameState.pickup_closed) {
            box.open();
            pixel.open();
            elevator.down();

            gameState = GameState.pickup_opened;
        } else if (gamepad1.a && gameState == GameState.pickup_opened) {
            pixel.close();
            box.close();
            elevator.down();

            gameState = GameState.pickup_closed;
        } else if (gamepad1.x && gameState == GameState.pickup_closed) {
            elevator.up();
            box.drop();

            gameState = GameState.elevator_up;

            while (true) {
                if (gamepad1.x) {
                    pixel.open();
                    return;
                }
            }
        } else if (gamepad1.y && gameState == GameState.elevator_up) {
            pixel.close();
            box.close();
            elevator.down();

            gameState = GameState.pickup_closed;
        }

    }
}
