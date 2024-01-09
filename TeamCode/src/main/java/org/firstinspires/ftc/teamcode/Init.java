package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

@TeleOp(name = "Movement Opmode")
public class Init extends OpMode {

    Drive drive = new Drive();

    @Override
    public void init() {
        // Initialize drive init and input the hardware map.
        drive.init(hardwareMap);

        // Initialize gyro and input the hardware map.
        CHGyro.init(hardwareMap);
    }

    @Override
    public void loop() {
        // drive and robot using the controller gamepad1.
        drive.drive(gamepad1, telemetry);
    }
}
