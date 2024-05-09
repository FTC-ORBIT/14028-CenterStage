package org.firstinspires.ftc.teamcode.catcher;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pixel {
    static Servo servo;

    //public static boolean isOpen;

    final static double intakePosition = 0.14;
    final static double boxDeplatedPosition = 0.72;
    final static double deplatePosition = 0.47;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "pixel");
        servo.setDirection(Servo.Direction.FORWARD);
        //isOpen = false;
    }

    public static void open() {
        servo.setPosition(intakePosition);
        //isOpen = true;
    }

    public static void boxClose() {
        servo.setPosition(boxDeplatedPosition);
    }

    public static void close() {
        servo.setPosition(deplatePosition);
        //isOpen = false;
    }

    public static void setPosition(double pos) { servo.setPosition(pos); }

    public static double getPosition() {
        return servo.getPosition();
    }
}
