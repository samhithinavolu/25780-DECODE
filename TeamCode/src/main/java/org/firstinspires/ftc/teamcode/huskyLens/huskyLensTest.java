package org.firstinspires.ftc.teamcode.huskyLens;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="huskyLensTest", group="OpMode")
public class huskyLensTest extends LinearOpMode {

    private HuskyLens huskyLens;


    @Override
    public void runOpMode() {

        huskyLens = hardwareMap.get(HuskyLens.class, "huskyLens");
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.TAG_RECOGNITION);

        telemetry.addData("Status", "huskyLens Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            while (opModeIsActive()) {



                telemetry.addData("Obelisk AprilTagID", AprilTagDetection(true,"AprilTagID"));
                telemetry.addData("Artifact Pattern", AprilTagDetection(true,"ArtifactPattern"));
                telemetry.addData("Field AprilTagID", AprilTagDetection(false, "AprilTagID"));
                telemetry.addLine("");
                telemetry.addData("Coordinates", AprilTagPosition());
                telemetry.update();
            }
        }
    }

    private String AprilTagDetection(Boolean Obelisk, String Results) {
        HuskyLens.Block[] aprilTagsDetected = huskyLens.blocks();
        String AprilTagID = "0";
        String Pattern = "";

        if (aprilTagsDetected.length > 0) {
            for (HuskyLens.Block blocks : aprilTagsDetected) {
                if (blocks.id == 1 && Obelisk == true) {
                    AprilTagID = "21";
                    Pattern = "Green, Purple, Purple";
                    if (Results.equals("AprilTagID")) {
                        return AprilTagID;
                    } else if (Results.equals("ArtifactPattern")) {
                        return Pattern;
                    } else {
                        return "Invalid Results Requested";
                    }
                } else if (blocks.id == 2 && Obelisk == true) {
                    AprilTagID = "22";
                    Pattern = "Purple, Green, Purple";
                    if (Results.equals("AprilTagID")) {
                        return AprilTagID;
                    } else if (Results.equals("ArtifactPattern")) {
                        return Pattern;
                    } else {
                        return "Invalid Results Requested";
                    }
                } else if (blocks.id == 3 && Obelisk == true) {
                    AprilTagID = "23";
                    Pattern = "Purple, Purple, Green";
                    if (Results.equals("AprilTagID")) {
                        return AprilTagID;
                    } else if (Results.equals("ArtifactPattern")) {
                        return Pattern;
                    } else {
                        return "Invalid Results Requested";
                    }
                } else if (blocks.id == 4 && Obelisk == false) {
                    AprilTagID = "20";
                    if (Results.equals("AprilTagID")) {
                        return AprilTagID;
                    } else if (Results.equals("ArtifactPattern")) {
                        return "No pattern for this AprilTag";
                    } else {
                        return "Invalid Results Requested";
                    }
                } else if (blocks.id == 5 && Obelisk == false) {
                    AprilTagID = "24";
                    if (Results.equals("AprilTagID")) {
                        return AprilTagID;
                    } else if (Results.equals("ArtifactPattern")) {
                        return "No pattern for this AprilTag";
                    } else {
                        return "Invalid Results Requested";
                    }
                }
            }
        } else {
            return "No AprilTags Detected";
        }
        if (Results.equals("AprilTagID")) {
            return AprilTagID;
        } else if (Results.equals("ArtifactPattern")) {
            return Pattern;
        } else {
            return "Invalid Results Requested";
        }
    }

    private List<Double> AprilTagPosition() {
        HuskyLens.Block[] aprilTagsDetected = huskyLens.blocks();
        List<Double> coorSize = new ArrayList<>();
        if (aprilTagsDetected.length > 0) {
            for (HuskyLens.Block blocks : aprilTagsDetected) {
                if (blocks.id == 4) {
                    double x = blocks.x;
                    double y = blocks.y;
                    double width = blocks.width;
                    double height = blocks.height;
                    double distance = distanceCalc(width, height);
                    coorSize.add(x);
                    coorSize.add(y);
                    coorSize.add(width);
                    coorSize.add(height);
                    coorSize.add(distance);
                    return coorSize;
                } else if (blocks.id == 5) {
                    double x = blocks.x;
                    double y = blocks.y;
                    double width = blocks.width;
                    double height = blocks.height;
                    double distance = distanceCalc(width, height);
                    coorSize.add(x);
                    coorSize.add(y);
                    coorSize.add(width);
                    coorSize.add(height);
                    coorSize.add(distance);
                    return coorSize;
                } else {
                    return coorSize;
                }
            }
        } else {
            return coorSize;
        }
        return coorSize;
    }

    private double distanceCalc(double width, double height) {
        int tagWidth = 4; //inches
        int focalLength = 480;

        double avgPixSize = (width + height) / 2;
        return ((tagWidth * focalLength) / avgPixSize);
    }
}
