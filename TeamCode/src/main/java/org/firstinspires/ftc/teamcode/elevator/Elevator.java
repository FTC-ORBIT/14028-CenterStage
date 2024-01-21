package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elevator {

    DcMotor[] motors;

    public void init(HardwareMap hardwareMap) {
        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotorEx.class, "ELf");
        motors[1] = hardwareMap.get(DcMotorEx.class, "ELf");
    }

    // make a enum for elevator state.
    // make a function to change elevator state.
    // make another function to change the height using the state.

    void changeHeight(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.a) {
            ElevatorState.setState(motors, ElevatorState.State.up);
        } else if (gamepad.b) {
            ElevatorState.setState(motors, ElevatorState.State.down);
        }
    }

    double setPower(DcMotor[] motors) {
        double currentHeight = getCurrentHeight(motors[0]);

        if (ElevatorState.state == ElevatorState.State.up) {
            switch (currentHeight) {
                case currentHeight >=
            }
        } else if (ElevatorState.state == ElevatorState.State.middle) {

        } else {

        }
        return 0;
    }

    double setWantedHeight(double height) {
        return height * 537.7;
    }

    double getCurrentHeight(DcMotor motor) {
        return motor.getCurrentPosition();
    }

}
