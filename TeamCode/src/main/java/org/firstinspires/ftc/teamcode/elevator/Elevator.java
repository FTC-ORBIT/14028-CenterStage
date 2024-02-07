package org.firstinspires.ftc.teamcode.elevator;

import static org.firstinspires.ftc.teamcode.utils.Motors.setPowerMotorList;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Motors;

public class Elevator {

    DcMotor[] motors;
    public double currentHeight;
    public double startHeight;
    public ElevatorState state;

    Gamepad gamepad;

    public void init(HardwareMap hardwareMap, Gamepad gamepad) {
        this.gamepad = gamepad;

        motors = new DcMotor[1];
        motors[0] = hardwareMap.get(DcMotor.class, "el");
        //motors[1] = hardwareMap.get(DcMotor.class, "ELf");
        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        startHeight = Math.abs(motors[0].getCurrentPosition());

        state = ElevatorState.down;
    }

    public void dynamic_controller() {
        Motors.setPowerMotorList(motors, gamepad.right_stick_y);
        currentHeight = Math.abs(motors[0].getCurrentPosition()) - startHeight;
    }

    public void up() {
        if (state != ElevatorState.up) {
            setPower(motors, setWantedHeight(100), 0.1, 0.2);
            state = ElevatorState.up;
        }
    }

    public void middle() {
        if (state != ElevatorState.middle) {
            setPower(motors, setWantedHeight(0), 0, 0);
            state = ElevatorState.middle;
        }
    }

    public void down() {
        if (state != ElevatorState.down) {
            setPower(motors, setWantedHeight(2), -0.1, -0.2);
            state = ElevatorState.down;
        }
    }

    void setPower(DcMotor[] motors, double wantedHeight, double vMin, double vMax) {
        double per1 = 0.15, per2 = 0.1;

        currentHeight = Math.abs(motors[0].getCurrentPosition()) - startHeight;

        setPowerFunction(currentHeight, wantedHeight, vMax, vMin, per1, per2);
    }

    void setPowerFunction(double currentHeight , double wantedHeight, double vMax, double vMin, double per1, double per2) {
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
    }

    double setWantedHeight(double height) {
        return height / (96 / 537.7);
    }
}
