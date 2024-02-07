package org.firstinspires.ftc.teamcode.elevator;

import static org.firstinspires.ftc.teamcode.utils.Motors.setPowerMotorList;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {

    DcMotor[] motors;
    public double currentHeight;
    public ElevatorState state;

    Gamepad gamepad;

    public void init(HardwareMap hardwareMap, Gamepad gamepad) {
        this.gamepad = gamepad;

        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotor.class, "Elf");
        //motors[1] = hardwareMap.get(DcMotor.class, "ELf");

        motors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //motors[1].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        state = ElevatorState.down;
    }

    public void dynamic_controller() {
        setPowerMotorList(motors, gamepad.right_stick_y);
    }

    public void up() {
        if (state != ElevatorState.up) {
            setPower(motors, state, setWantedHeight(0), 0, 0);
            state = ElevatorState.up;
        }
    }

    public void middle() {
        if (state != ElevatorState.middle) {
            setPower(motors, state, setWantedHeight(0), 0, 0);
            state = ElevatorState.middle;
        }
    }

    public void down() {
        if (state != ElevatorState.down) {
            setPower(motors, state, setWantedHeight(0), 0, 0);
            state = ElevatorState.down;
        }
    }

    void setPower(DcMotor[] motors, ElevatorState state, double wantedHeight, double vMin, double vMax) {
        double per1 = 0.15, per2 = 0.1;

        currentHeight = motors[0].getCurrentPosition();

        //setPowerFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2);

        /*switch (state) {
            case up:
                if (currentHeight <= 0.15 * wantedHeight) {
                    setPowerMotorList(motors, ((vMax - vMin) / 0.15 * wantedHeight) * currentHeight + vMin);
                } else if (currentHeight <= 0.9 * wantedHeight) {
                    setPowerMotorList(motors, vMax);

                } else {
                    setPowerMotorList(motors, (10 / wantedHeight) * (vMin - vMax) + 10 * vMax - 9 * vMin);

                }
                break;

            case middle:
                //setPowerFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2);
                break;

            case down:
                //setPowerFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2);
                break;
        }*/
    }

    /*void setPowerFunction(double currentHeight , double wantedHeight, double vMax, double vMin, double per1, double per2) {
        if (currentHeight >= wantedHeight) {
            setPowerMotorList(motors, 0);
        }
        else if (currentHeight <= per1 * wantedHeight) {
            setPowerMotorList(motors, ((vMax - vMin) / (per1 * wantedHeight)) * currentHeight + vMin);
        }
        else if (currentHeight <= (1 - per2) * wantedHeight) {
            setPowerMotorList(motors, vMax);
        }
        else if (currentHeight >= (1 - per2) * wantedHeight){
            setPowerMotorList(motors, (vMax -vMin) / (wantedHeight * (1 - per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin) / (wantedHeight * (1-per2) - wantedHeight) * wantedHeight);
        }
    }*/

    double setWantedHeight(double height) {
        return height / (96 / 537.7);
    }
}
