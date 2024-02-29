package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Motors {
    public static void setPowerMotorList(DcMotor[] motors, double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    public static void resetEncoders(DcMotor[] motors) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    /*public static int setWantedHeight(int height) {
        return (int) (height * (96 / 537.7));
    }*/
}
