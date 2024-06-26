package org.firstinspires.ftc.teamcode.catcher;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Catcher {

    public static boolean isBoxOpen;

    public static void init(HardwareMap hardwareMap) {
        Box.init(hardwareMap);
        Pixel.init(hardwareMap);

        isBoxOpen = false;
    }

    public static void intakeCatcher() {
        if (!isBoxOpen) return;
        Pixel.open();
    }

    public static void deplateCatcher() {
        Pixel.close();
    }

    public static void intakeBox() {
        Box.intake();
        Pixel.close();
        isBoxOpen = true;
    }

    public static void deplateBox() {
        Box.deplate();
        Pixel.boxClose();
        isBoxOpen = false;
    }

    public static void dropPixel() {
        Pixel.setPosition(0.6);
    }
}
