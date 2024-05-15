package org.firstinspires.ftc.teamcode.catcher;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Box {
    public static boolean isOpen;
    public static Servo servo;
    final static double intakePosition = 0.05;
    final static double deplatePosition = 0.27;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "box");
        servo.setDirection(Servo.Direction.REVERSE);
        isOpen = false;
    }

    public static void intake() {
        servo.setPosition(intakePosition);
        //Pixel.servo.setPosition(-openPosition);
        isOpen = true;
    }

    public static void deplate() {
        servo.setPosition(deplatePosition);
        isOpen = false;
    }

    public static double getPosition() { return servo.getPosition(); }

    public static void setPosition(double pos) { servo.setPosition(pos); }
}
