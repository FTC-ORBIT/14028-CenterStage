package org.firstinspires.ftc.teamcode.elevator;

import static org.firstinspires.ftc.teamcode.utils.Motors.setPowerMotorList;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
        motors[1] = hardwareMap.get(DcMotor.class, "er");

        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        startHeight = Math.abs(motors[0].getCurrentPosition());

        state = ElevatorState.down;
    }

    

    double setWantedHeight(double height) {
        return height * (96 / 537.7);
    }
}
