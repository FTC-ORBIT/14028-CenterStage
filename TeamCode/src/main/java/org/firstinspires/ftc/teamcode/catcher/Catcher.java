package org.firstinspires.ftc.teamcode.catcher;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Catcher {

    static boolean isBoxOpen;
    public static boolean shouldOpenBox;

    public static void init(HardwareMap hardwareMap) {
        Box.init(hardwareMap);
        Pixel.init(hardwareMap);

        isBoxOpen = false;
    }

    public static void openCatcher() {
        if (isBoxOpen) return;
        Pixel.open();
    }

    public static void closeCatcher() {
        Pixel.close();
    }

    public static void openBox() {
        Box.open();
        Pixel.close();
        isBoxOpen = true;
    }

    public static void closeBox() {
        Box.close();
        Pixel.boxClose();
        isBoxOpen = false;
    }
}
