package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Motors {
    public static void setPowerMotorList(DcMotor[] motors, double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    /*public static int setWantedHeight(int height) {
        return (int) (height * (96 / 537.7));
    }*/
}
