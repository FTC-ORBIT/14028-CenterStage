package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elevator {

    DcMotor[] motors;
    double currentHeight;
    double wantedHeight = 0;

    ElevatorState state;

    public void init(HardwareMap hardwareMap) {
        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotorEx.class, "ELf");
        motors[1] = hardwareMap.get(DcMotorEx.class, "ELf");

        motors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[1].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        wantedHeight = setWantedHeight(wantedHeight);
        state = ElevatorState.down;
    }

    public void changeHeight(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.a) {
            state = ElevatorState.up;
            wantedHeight = 0;
            setPower(motors, state);
        } else if (gamepad.b) {
            state = ElevatorState.middle;
            wantedHeight = 0;
            setPower(motors, state);
        } else if (gamepad.y) {
            state = ElevatorState.down;
            wantedHeight = 0;
            setPower(motors, state);
        }
    }

    void setPower(DcMotor[] motors, ElevatorState state) {
        double vMin = 0, vMax = 0;
        double per1 = 0.15, per2 = 0.1;

        currentHeight = getCurrentHeight(motors[0]);

        switch (state) {
            case up:
                /*if (currentHeight <= 0.15 * wantedHeight) {
                    setPowerMotorList(motors, ((vMax - vMin) / 0.15 * wantedHeight) * currentHeight + vMin);

                } else if (currentHeight <= 0.9 * wantedHeight) {
                    setPowerMotorList(motors, vMax);

                } else {
                    setPowerMotorList(motors, (10 / wantedHeight) * (vMin - vMax) + 10 * vMax - 9 * vMin);

                }
                break;*/
                setFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2, false);

            case middle:
                setFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2, false);
                break;

            case down:
                setFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2, true);
                break;
        }
    }

    void setFunction(double currentHeight , double wantedHeight, double vMax, double vMin, double per1, double per2, boolean reversePower) {

        if (reversePower) {
            vMax = -vMax;
            vMin = -vMin;
        }

        if (currentHeight <= per2 * wantedHeight) {
            setPowerMotorList(motors, ((vMax - vMin) / per1 * wantedHeight) * currentHeight + vMin);

        } else if (currentHeight <= (1 - per2) * wantedHeight) {
            setPowerMotorList(motors, vMax);

        } else {
            setPowerMotorList(motors, (per2 / wantedHeight) * (vMin - vMax) + 10 * vMax - 9 * vMin);

        }
    }

    double setWantedHeight(double height) {
        return height * 537.7;
    }

    double getCurrentHeight(DcMotor motor) {
        return motor.getCurrentPosition();
    }

    void setPowerMotorList(DcMotor[] motors, double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }
}
