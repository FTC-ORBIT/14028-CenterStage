package org.firstinspires.ftc.teamcode.catcher;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pixel {
    static Servo servo;

    public static boolean isOpen;

    final static double openPosition = 0.15;
    final static double boxOpenPosition = 0.6;
    final static double closedPosition = 0.45;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "pixel");
        servo.setDirection(Servo.Direction.FORWARD);
        isOpen = false;
    }

    public static void open() {
        servo.setPosition(openPosition);
        isOpen = true;
    }

    public static void boxClose() {
        servo.setPosition(boxOpenPosition);
    }

    public static void close() {
        servo.setPosition(closedPosition);
        isOpen = false;
    }

    public static void setPosition(double pos) { servo.setPosition(pos); }

    public static double getPosition() {
        return servo.getPosition();
    }
}
