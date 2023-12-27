package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensor.CHGyro;

@TeleOp(name = "Movement Opmode")
public class InitDrive extends OpMode {

    Driving drive = new Driving();
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
