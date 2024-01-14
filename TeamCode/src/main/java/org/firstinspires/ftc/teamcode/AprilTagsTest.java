package org.firstinspires.ftc.teamcode;

import android.graphics.Camera;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
        webcam = hardwareMap.get(WebcamName.class, "Webcam1");

        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        visionPortal = new VisionPortal.Builder()
                .addProcessor(aprilTagProcessor)
                .setCamera(webcam)
                .setCameraResolution(new Size(640, 480))
                .build();

        List<AprilTagDetection> currentTags = new ArrayList<>();

        if (currentTags.size() > 0) {
            telemetry.addData("id", currentTags.get(0).id);
            telemetry.addData("x", currentTags.get(0).ftcPose.x);
            telemetry.addData("y", currentTags.get(0).ftcPose.y);
            telemetry.addData("z", currentTags.get(0).ftcPose.z);
            telemetry.addData("", currentTags.get(0).ftcPose.range);
            telemetry.update();
        }
    }
}
