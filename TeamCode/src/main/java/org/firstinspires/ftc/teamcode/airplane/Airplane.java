package org.firstinspires.ftc.teamcode.airplane;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Airplane {

    static Servo servo;
    static int closePos = 0;
    static int openPos = 0;

    public static void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "aps");
    }

    public static void launch() {
        servo.setPosition(openPos);
    }
    public static void close() {
        servo.setPosition(closePos);
    }
}
