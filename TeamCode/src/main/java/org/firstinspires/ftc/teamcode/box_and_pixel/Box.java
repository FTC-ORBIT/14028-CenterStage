package org.firstinspires.ftc.teamcode.box_and_pixel;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Box {
    public static boolean isOpen;
    public static Servo servo;
    final static double openPosition = 0.2;
    final static double closedPosition = 0;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "box");
        servo.setDirection(Servo.Direction.REVERSE);
        isOpen = false;
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

    public static double getPosition() { return servo.getPosition(); }

    public static void setPosition(double pos) { servo.setPosition(pos); }
}
