package org.firstinspires.ftc.teamcode.airplane;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Airplane {

    static Servo servo;
    static double closePos = 0.4;
    static double openPos = 0.2;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "ap");
        servo.setDirection(Servo.Direction.REVERSE);
    }

    public static void open() {
        servo.setPosition(openPos);
    }
    public static void close() {
        servo.setPosition(closePos);
    }

    public static double getPos() {
        return servo.getPosition();
    }
}
