package org.firstinspires.ftc.teamcode.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
public class ElevatorState {

    public enum State {
        down,
        up
    }

    public void setState(DcMotor[] motors, State state, double power) {
        for (DcMotor motor : motors) {
            switch (state) {
                case up:
                    motor.setPower(power);
            }
        }
    }
}
