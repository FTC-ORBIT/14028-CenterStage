package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;

public class ElevatorState {

    public enum State {
        down,
        middle,
        up
    }

    public static State state;
    static double currentHeight = 0;

    public static void setState(DcMotor[] motors, State state) {
        double upMaxPower = 0;
        double upMinPower = 0;
        double middleMaxPower = 0;
        double middleMinPower = 0;
        double downMaxPower = 0;
        double downMinPower = 0;

        double wantedHeight = 0;

        for (DcMotor motor : motors) {
            switch (state) {
                case up:
                    state = State.up;
                    continue;
                case middle:
                    state = State.middle;
                    continue;
                case down:
                    state = State.down;
                    continue;
            }
        }
    }
}
