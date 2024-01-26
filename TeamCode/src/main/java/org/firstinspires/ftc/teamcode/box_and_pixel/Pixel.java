package org.firstinspires.ftc.teamcode.box_and_pixel;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pixel {

    Servo servo;

    boolean isOpen;

    double openPosition = 0;
    double closedPosition = 0;

    public void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "pixel");
        servo.setPosition(closedPosition);
    }

    public void open() {
        if (!isOpen) {
            servo.setPosition(openPosition);
            isOpen = true;
        }
    }

    public void close() {
        if (isOpen) {
            servo.setPosition(closedPosition);
            isOpen = false;
        }
    }
}
