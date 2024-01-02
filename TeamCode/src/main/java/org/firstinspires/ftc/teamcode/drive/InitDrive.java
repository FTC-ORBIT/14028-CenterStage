package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sensor.CHGyro;

@TeleOp(name = "Movement Opmode")
public class InitDrive extends OpMode {

    Drive drive = new Drive();
    CHGyro chGyro = new CHGyro();

    @Override
    public void init() {
        drive.init(hardwareMap);
        chGyro.init(hardwareMap);
    }

    @Override
    public void loop() {
        drive.drive(gamepad1, telemetry);
    }





}
