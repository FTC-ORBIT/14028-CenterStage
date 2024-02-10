package org.firstinspires.ftc.teamcode.elevator;

import static org.firstinspires.ftc.teamcode.utils.Motors.setPowerMotorList;

import android.widget.Button;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Motors;

public class Elevator {
    DcMotor[] motors;
    public int currentHeight;
    public int startHeight;
    public int wantedHeight;
    public ElevatorState state;
    Gamepad gamepad;

    public void init(HardwareMap hardwareMap, Gamepad gamepad) {
        this.gamepad = gamepad;

        motors = new DcMotor[1];
        motors[0] = hardwareMap.get(DcMotor.class, "el");
        motors[1] = hardwareMap.get(DcMotor.class, "er");

        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wantedHeight = 2000;

        state = ElevatorState.down;
    }

    public void up() {
        if (state != ElevatorState.up) return;

        double per1 = 0.15f, per2 = 0.9;
        double vMin = 0.1, vMax = 0.3;

        if (currentHeight <= per1 * wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
        } else if (currentHeight <= per2 * wantedHeight) {
            Motors.setPowerMotorList(motors, vMax);
        } else if (currentHeight > per2 * wantedHeight || currentHeight < wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * wantedHeight);
        }
    }

    public void down() {
        if (state != ElevatorState.down) return;

        double per1 = 0.15, per2 = 0.9;
        double vMin = -0.1, vMax = -0.3;

        if (currentHeight <= per1 * wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/(wantedHeight * per1) * currentHeight + vMin);
        } else if (currentHeight <= per2 * wantedHeight) {
            Motors.setPowerMotorList(motors, vMax);
        } else if (currentHeight > per2 * wantedHeight || currentHeight < wantedHeight) {
            Motors.setPowerMotorList(motors, (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * currentHeight + vMin - (vMax - vMin)/((wantedHeight * per2) - wantedHeight) * wantedHeight);
        }
    }
}
