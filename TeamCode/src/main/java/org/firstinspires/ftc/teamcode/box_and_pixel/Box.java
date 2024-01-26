package org.firstinspires.ftc.teamcode.box_and_pixel;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Box {
    public static boolean isOpen;
    Servo servo;
    double openPosition = 0;
    double closedPosition = 0;
    double dropPosition = 0;

    public void init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "box");

        servo.setDirection(Servo.Direction.FORWARD); // need to check if the direction is correct.
        servo.setPosition(closedPosition);
    }

    /*public void changeMode(Telemetry telemetry) {
        if (gamepad.x && !isOpen) {
            servo.setPosition(openPosition);
        } else if (gamepad.x && isOpen) {
            servo.setPosition(closedPosition);
        }
    }*/

    public void open() {
        if (!isOpen) {
            servo.setPosition(openPosition);
            isOpen = true;
        }
    }

    public void drop() {
        if (!isOpen) {
            servo.setPosition(closedPosition);
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
