package org.firstinspires.ftc.teamcode.box_and_pixel;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Catcher {

    static boolean isBoxOpen;

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
        Pixel.boxOpen();
        isBoxOpen = true;
    }

    public static void closeBox() {
        Box.close();
        Pixel.close();
        isBoxOpen = false;
    }
}
