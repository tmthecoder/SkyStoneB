package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Concept: HolonomicDrive", group = "Concept")
//@Disabled


public class HolonomicDrive extends OpMode{

    /**
     *
     * Created by Akash and Rohan, FTC Team 17117, Tactical Terror
     * version 1.0 October-November 2019
     *
     */



    /*
        Holonomic concepts from:
        http://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
       Robot wheel mapping:
              X FRONT X
            X           X
          X  FL       FR  X
                  X
                 XXX
                  X
          X  BL       BR  X
            X           X
              X       X
    */
//@Disabled

        public DcMotor motorFrontRight;
        public DcMotor motorFrontLeft;
        public DcMotor motorBackRight;
        public DcMotor motorBackLeft;
        public DcMotor intakeleft;
        public DcMotor intakeright;
        public DcMotor IntakeDropper1;
        public  DcMotor IntakeDropper2;

        /**
         * Constructor
         */


        @Override

        public void init() {
        telemetry.addData("Status", "Initialized");

            /*
             * Use the hardwareMap to get the dc motors and servos by name. Note
             * that the names of the devices must match the names used when you
             * configured your robot and created the configuration file.
             */


            motorFrontRight = hardwareMap.dcMotor.get("frontRightDrive");
            motorFrontLeft = hardwareMap.dcMotor.get("frontLeftDrive");
            motorBackLeft = hardwareMap.dcMotor.get("backLeftDrive");
            motorBackRight = hardwareMap.dcMotor.get("backRightDrive");
            intakeleft = hardwareMap.dcMotor.get("LeftIntake");
            intakeright = hardwareMap.dcMotor.get("RightIntake");
            IntakeDropper1 = hardwareMap.dcMotor.get("IntakeDrop1");
            IntakeDropper2 = hardwareMap.dcMotor.get("IntakeDrop2");


            //These work without reversing (Tetrix motors).
            //AndyMark motors may be opposite, in which case uncomment these lines:
            //motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
            //motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
            //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
            //motorBackRight.setDirection(DcMotor.Direction.REVERSE);
            //leftintake.setDirection(CRServo.Direction.REVERSE);
        }

        @Override
        public void loop() {


            // left stick controls direction
            // right stick X controls rotation - DRIVER 1 CONTROLS - DRIVING ONLY

            float gamepad1LeftY = -gamepad1.left_stick_y;
            float gamepad1LeftX = gamepad1.left_stick_x;

            float gamepad1RightX = gamepad1.right_stick_x;

            float IntakeMotorin = gamepad2.left_trigger;
            float IntakeMotorout = gamepad2.right_trigger;

            // Motor Dropping
             boolean IntakeDroppingdown = gamepad2.left_bumper;
             boolean IntakeDropingup = gamepad2.right_bumper;

             if (IntakeDroppingdown && IntakeDropingup) {
                 IntakeDropper2.setPower(0);
                 IntakeDropper1.setPower(0);
             }

             if (IntakeDroppingdown) {
                 IntakeDropper2.setPower(-1);
                 IntakeDropper1.setPower(1);
             }

             if (IntakeDropingup) {
                 IntakeDropper2.setPower(1);
                 IntakeDropper1.setPower(-1);
             }

             if (IntakeDropingup && IntakeDroppingdown) {
                 telemetry.update();
                 telemetry.addData("You", " can not press both at the same time");
                 IntakeDropper1.setPower(0);
                 IntakeDropper2.setPower(0);
             }
             if (IntakeDropingup == false) {
                 IntakeDropper1.setPower(0);
                 IntakeDropper2.setPower(0);
             }
             if (IntakeDroppingdown == false) {
                 IntakeDropper2.setPower(0);
                 IntakeDropper1.setPower(0);
             }



            // Not driving setpower

            if (IntakeMotorin > 0.1 && IntakeMotorout > 0.1) {
                intakeleft.setPower(0);
                intakeright.setPower(0);

            } else if (IntakeMotorin > 0.1) {
                intakeleft.setPower(1);
                intakeright.setPower(-1);
            } else if (IntakeMotorout > 0.1) {
                intakeright.setPower(1);
            intakeleft.setPower(-1);
        }
            if (IntakeMotorin > 0 || IntakeMotorout > 0) {
                intakeright.setPower(0);
                intakeleft.setPower(0);
            }

            // holonomic formulas

            float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

            // clip the right/left values so that the values never exceed +/- 1
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

           // telemetry.update();
            // write the values to the motors
            motorFrontRight.setPower(FrontRight);
            motorFrontLeft.setPower(FrontLeft);
            motorBackLeft.setPower(BackLeft);
            motorBackRight.setPower(BackRight);





             //Telemetry for debugging
            telemetry.addData("Text", "*** Robot Data***");
            telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " +
            String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightX));
            telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
            telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
            telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
            telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));
        }

        @Override
        public void stop() {

        }

        /*
         * This method scales the joystick input so for low joystick values, the
         * scaled value is less than linear.  This is to make it easier to drive
         * the robot more precisely at slower speeds.
         */
        double scaleInput(double dVal)  {
            double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                    0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

            // get the corresponding index for the scaleInput array.
            int index = (int) (dVal * 16.0);

            // index should be positive.
            if (index < 0) {
                index = -index;
            }

            // index cannot exceed size of array minus 1.
            if (index > 16) {
                index = 16;
            }

            // get value from the array.
            double dScale = 0.0;
            if (dVal < 0) {
                dScale = -scaleArray[index];
            } else {
                dScale = scaleArray[index];
            }

            // return scaled value.
            return dScale;
        }

    }
