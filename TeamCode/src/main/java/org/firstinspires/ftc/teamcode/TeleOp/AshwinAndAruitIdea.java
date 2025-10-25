package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="AshwinAndAruitIdea", group="Linear OpMode")
public class AshwinAndAruitIdea extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private DcMotor launchingMotor, intakeMotor, launchingMotor1, pushingMotor;
    private Servo storageServo;
    private Servo storageServo1;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        launchingMotor = hardwareMap.get(DcMotor.class, "launchingMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        launchingMotor1 = hardwareMap.get(DcMotor.class, "launchingMotor1");
        pushingMotor = hardwareMap.get(DcMotor.class, "pushingMotor");
        storageServo = hardwareMap.get(Servo.class, "storageServo");
//        storageServo1 = hardwareMap.get(Servo.class, "storageServo1");


        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        launchingMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            float launchingMotorSpeed = 0.6f;
            int intakeMotorSpeed = 1;
            int pushingMotorSpeed = 1;

            launchingMotor.setPower(launchingMotorSpeed * gamepad1.right_trigger);
            intakeMotor.setPower(intakeMotorSpeed * gamepad1.left_trigger);
            launchingMotor1.setPower(launchingMotorSpeed * gamepad1.right_trigger);
            //pushingMotor.setPower(pushingMotorSpeed * gamepad1.right_bumper);

            if (gamepad1.a) {
                storageServo.setPosition(0.4);
            }
//            if (gamepad1.b) {
//                storageServo1.setPosition(0.4);
//            }

        }
    }
}

