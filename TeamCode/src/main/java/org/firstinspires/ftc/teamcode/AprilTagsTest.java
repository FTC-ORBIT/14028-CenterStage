package org.firstinspires.ftc.teamcode;

import android.graphics.Camera;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "AprilTagsTest")
public class AprilTagsTest extends LinearOpMode {

    AprilTagProcessor aprilTagProcessor;
    VisionPortal visionPortal;

    WebcamName webcam;

    @Override
    public void runOpMode() throws InterruptedException {
        webcam = hardwareMap.get(WebcamName.class, "Webcam 1");

        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setLensIntrinsics(821.993f, 821.993f, 330.489f, 248.997f)
                .build();

        visionPortal = new VisionPortal.Builder()
                .addProcessor(aprilTagProcessor)
                .setCamera(webcam)
                .setCameraResolution(new Size(640, 480))
                .setAutoStopLiveView(true)
                .build();

        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();

        if (opModeInInit()) {
            while (opModeInInit()) {

                telemetryAprilTag();

                // Push telemetry to the Driver Station.
                telemetry.update();

            }
        }

        waitForStart();

        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

    }

    private void telemetryAprilTag() {

        List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addData("x", (int) detection.ftcPose.x);
                telemetry.addData("y", (int) detection.ftcPose.y);
                telemetry.addData("z", (int) detection.ftcPose.z);
                telemetry.addData("range", (int) detection.ftcPose.range);
                telemetry.addData("elevation", (int) detection.ftcPose.elevation);
                telemetry.addData("angle", (int) detection.ftcPose.bearing);
            }
        }

        // Add "key" information to telemetry
    }
}