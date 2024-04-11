package org.firstinspires.ftc.teamcode.sensor;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class CHGyro {
    public static BNO055IMU imu;
    public static double lastAngle;

    public static void init(HardwareMap hardwareMap) {
        // Initialize the gyro in the controlhub.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        resetGyro();
    }

    public static void resetGyro() {
        lastAngle = imu.getAngularOrientation(AxesReference.INTRINSIC , AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }
    public static double getAngle() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC , AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - lastAngle;
    }

}
