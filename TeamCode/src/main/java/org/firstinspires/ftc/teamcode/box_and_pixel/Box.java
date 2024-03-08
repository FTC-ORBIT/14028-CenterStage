package org.firstinspires.ftc.teamcode.box_and_pixel;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Box {
    public static boolean isOpen;
    public static Servo servo;
    static double openPosition = 0;
    static double closedPosition = 0;

    public static int changePositionHeight = 0;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "box");

        //servo.setDirection(Servo.Direction.FORWARD); // need to check if the direction is correct.
        //servo.setPosition(closedPosition);
        isOpen = false;
    }

    public static void changeState() {
        if (!isOpen) {
            open();
        } else {
            close();
        }
    }

    public static void open() {
        servo.setPosition(openPosition);
        //Pixel.servo.setPosition(-openPosition);
        isOpen = true;
    }

    public static void close() {
        servo.setPosition(closedPosition);
        isOpen = false;
    }

    public static void setPosition(double position) {
        servo.setPosition(position);
    }
}
