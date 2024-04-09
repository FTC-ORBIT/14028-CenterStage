package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.utils.Motors;

public class Elev {

    public static int maxEncoderTick = 2000;
    int travelHeight = 15;
    int intakeHeight = 210;

    private static DcMotor[] motors;
    public static void init(HardwareMap hardwareMap) {
        motors = new DcMotor[2];
        motors[0] = hardwareMap.get(DcMotor.class, "el");
        motors[1] = hardwareMap.get(DcMotor.class, "er");

        motors[0].setDirection(DcMotorSimple.Direction.REVERSE);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void operate(ElevatorState state) {
        motors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors[1].setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        if ((motors[0].getCurrentPosition() < 0 || motors[1].getCurrentPosition() < 0) || (motors[0].getCurrentPosition() > maxEncoderTick || motors[1].getCurrentPosition() > maxEncoderTick)) {
//            stop();
//            return;
//        }

        switch (state) {
            case INTAKE:
                goToPosition(210);
                break;
            case TRAVEL:
                goToPosition(15);
                break;
        }
    }

    public static void setElevatorPower(double power){
        motors[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[1].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        if ((motors[0].getCurrentPosition() < 0 || motors[1].getCurrentPosition() < 0) || (motors[0].getCurrentPosition() > maxEncoderTick || motors[1].getCurrentPosition() > maxEncoderTick)) {
//            stop();
//            return;
//        }
        motors[0].setPower(power);
        motors[1].setPower(power);
    }
    public static void stop(){
        motors[0].setPower(0);
        motors[1].setPower(0);
    }

    public static void goToPosition(int wantedHeight) {

        // make one of them will be a -target.

        motors[0].setTargetPosition(-wantedHeight);
        motors[1].setTargetPosition(-wantedHeight);

        motors[0].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Motors.setPowerMotorList(motors, 0.5);
    }
    public static int getPos() { return motors[1].getCurrentPosition();}
}
