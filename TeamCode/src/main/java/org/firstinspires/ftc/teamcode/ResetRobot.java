package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.elevator.Elevator;
import org.firstinspires.ftc.teamcode.utils.Motors;

@TeleOp(name = "Reset Robot")
public class ResetRobot extends OpMode {

    // reset the any robot variables that may change after every run.
    @Override
    public void init() {
        Elevator.motors[0] = hardwareMap.get(DcMotor.class, "el");
        Elevator.motors[1] = hardwareMap.get(DcMotor.class, "er");

        Motors.resetEncoders(Elevator.motors);
    }

    @Override
    public void loop() {
        return;
    }
}
