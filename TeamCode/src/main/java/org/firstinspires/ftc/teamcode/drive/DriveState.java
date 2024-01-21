package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.utils.Vector;

public class DriveState {

    public enum State {
        normal,
        slow,
        slower
    }

    public static Vector setDrivingState(Vector vector, DriveState.State state) {
        switch (state) {
            case normal:
                return vector;
            case slow:
                return new Vector(vector.x / 2, vector.y / 2);
            case slower:
                return new Vector(vector.x / 3, vector.y / 3);
        }
        return vector;
    }
}
