package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="SamhithIdea", group="OpMode")
public class SamhithIdea extends LinearOpMode {

    private DcMotor intakeMotor, frontLeft, frontRight, backLeft, backRight, shooter;
    private CRServo intakeServo;

    public void runOpMode() {

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                while (gamepad1.left_trigger != 0 && opModeIsActive()) {
                    intakeServo.setPower(-1);
                    intakeMotor.setPower(gamepad1.left_trigger);
                    shooter.setPower(gamepad1.right_trigger);
                    robotMovement(0.5);
                }

                while (gamepad1.left_trigger == 0 && opModeIsActive()) {
                    intakeServo.setPower(0);
                    intakeMotor.setPower(0);
                }
                shooter.setPower(gamepad1.right_trigger);
                robotMovement(0.5);
            }
        }
    }
    public void robotMovement(double speedPower){
        float vertical = -gamepad1.left_stick_y;
        float horizontal = gamepad1.left_stick_x;
        float pivot = gamepad1.right_stick_x;

        backLeft.setPower(speedPower * (pivot + vertical - horizontal));
        backRight.setPower(speedPower * (-pivot + vertical + horizontal));
        frontLeft.setPower(speedPower * (pivot + vertical + horizontal));
        frontRight.setPower(speedPower * (-pivot + vertical - horizontal));
    }
}