package org.firstinspires.ftc.teamcode.box_and_pixel;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pixel {

    static Servo servo;

    static boolean isOpen;

    static double openPosition = 0;
    static double closedPosition = 0;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "pixel");
        servo.setPosition(closedPosition);
    }

    public static void open() {
        if (!isOpen) {
            servo.setPosition(openPosition);
            isOpen = true;
        }
    }

    public static void close() {
        if (isOpen) {
            servo.setPosition(closedPosition);
            isOpen = false;
        }
    }
}
