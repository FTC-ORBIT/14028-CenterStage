package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elevator {

    DcMotor[] motors;

    public void init(HardwareMap hardwareMap) {
        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotor.class, "ELf");
        motors[1] = hardwareMap.get(DcMotor.class, "ELf");
    }

    // make a enum for elevator state.
    // make a function to change elevator state.
    // make another function to change the height using the state.

    public void changeHeight(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.a) {

        }
    }

}
