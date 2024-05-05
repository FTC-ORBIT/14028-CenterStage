package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Motors;

public class Elevator {

    public static int maxEncoderTick = 2000;
    static int travelHeight = 15;
    static int intakeHeight = 210;
    static int level1Height = 1000;
    static int level2Height = 1500;
    static int climbHeight = 15;

    public static double power = 0.6;
    public static DcMotor[] motors;
    public static void init(HardwareMap hardwareMap) {
        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotor.class, "el");
        motors[1] = hardwareMap.get(DcMotor.class, "er");

        motors[0].setDirection(DcMotorSimple.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void operate(ElevatorState state) {
//        if ((motors[0].getCurrentPosition() < 0 || motors[1].getCurrentPosition() < 0) || (motors[0].getCurrentPosition() > maxEncoderTick || motors[1].getCurrentPosition() > maxEncoderTick)) {
//            stop();
//            return;
//        }

        switch (state) {
            case TRAVEL:
                goToPosition(travelHeight);
                break;
            case INTAKE:
                goToPosition(intakeHeight);
                break;
            case LEVEL1:
                goToPosition(level1Height);
                break;
            case LEVEL2:
                goToPosition(level2Height);
                break;
            case CLIMB:
                goToPosition(climbHeight);
                break;
        }
    }

    public static void setElevatorPower(double power){
        motors[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[1].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if ((motors[0].getCurrentPosition() < 0 || motors[1].getCurrentPosition() < 0) || (motors[0].getCurrentPosition() > maxEncoderTick || motors[1].getCurrentPosition() > maxEncoderTick)) {
            stop();
            return;
        }
        motors[0].setPower(power);
        motors[1].setPower(power);
    }
    public static void stop(){
        motors[0].setPower(0);
        motors[1].setPower(0);
    }

    public static void goToPosition(int wantedHeight) {
        motors[0].setTargetPosition(-wantedHeight);
        motors[1].setTargetPosition(-wantedHeight);

        motors[0].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Motors.setPowerMotorList(motors, power);
    }
    public static int getPos() { return motors[1].getCurrentPosition(); }
}
