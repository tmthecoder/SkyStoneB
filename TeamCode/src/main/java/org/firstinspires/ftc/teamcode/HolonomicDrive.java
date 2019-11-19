package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Concept: HolonomicDrive", group = "Concept")

public class HolonomicDrive extends OpMode{

    /**
     *
     * Created by Maddie, FTC Team 4962, The Rockettes
     * version 1.0 Aug 11, 2016
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
        public DcMotor lifterMotor;
        CRServo intakeServo;
        Servo pincherServo;
        RevTouchSensor digitalTouch;

        /**
         * Constructor
         */


        @Override
        public void init() {


            /*
             * Use the hardwareMap to get the dc motors and servos by name. Note
             * that the names of the devices must match the names used when you
             * configured your robot and created the configuration file.
             */


            motorFrontRight = hardwareMap.dcMotor.get("frontRightDrive");
            motorFrontLeft = hardwareMap.dcMotor.get("frontLeftDrive");
            motorBackLeft = hardwareMap.dcMotor.get("backLeftDrive");
            motorBackRight = hardwareMap.dcMotor.get("backRightDrive");
            lifterMotor = hardwareMap.dcMotor.get("lifterMotor");
            intakeServo = hardwareMap.crservo.get("intakeServo");
            pincherServo = hardwareMap.servo.get("pincherServo");
            digitalTouch = hardwareMap.get(RevTouchSensor.class, "digitalTouch");

            //These work without reversing (Tetrix motors).
            //AndyMark motors may be opposite, in which case uncomment these lines:
            //motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
            //motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
            //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
            //motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        }

        @Override
        public void loop() {


            // left stick controls direction
            // right stick X controls rotation

            float gamepad1LeftY = -gamepad1.left_stick_y;
            float gamepad1LeftX = gamepad1.left_stick_x;
            float gamepad1RightX = gamepad1.right_stick_x;

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

            /*
            servo variable initialization
             */
            float RightTrigger = gamepad2.right_trigger;
            float LeftTrigger = gamepad2.left_trigger;
            boolean RightBumper = gamepad2.right_bumper;
            boolean LeftBumper = gamepad2.left_bumper;
            boolean ButtonA = gamepad2.a;
            boolean ButtonB = gamepad2.b;
            boolean grabberRetract = false;
            boolean grabberExtend = false;
            boolean grabberRelease = false;
            boolean grabberPinch = false;

           // Defining the booleans lifterUp and lifterDown
            boolean lifterUp = false;
            boolean lifterDown = false;

            //digitalTouch.setMode(DigitalChannel.Mode.INPUT);

            /*
            make sure variables are in bound
             */
            RightTrigger = Range.clip(RightTrigger, -1, 1);
            LeftTrigger = Range.clip(LeftTrigger, -1, 1);



            /*
            check if triggers are pressed, and if so,
            set grabberRetract or grabberExtend
             */
            if (RightTrigger > 0.1) grabberRetract = true;
            if (LeftTrigger > 0.1) grabberExtend = true;
            if (RightBumper) grabberRelease = true;
            if (LeftBumper) grabberPinch = true;
            if (grabberRetract && grabberExtend) // if both are pressed down deactive both of them
            {
                grabberRetract = false;
                grabberExtend = false;
            }
            if (grabberRelease && grabberPinch)
            {
                grabberRelease = false;
                grabberPinch = false;
            }
            /*
            If neither of the triggers are pressed down
            set the power to 0, stopping the servo
             */
            if (!grabberRetract && !grabberExtend)
            {
                intakeServo.setPower(0.0);
            }


            /*
            If the right trigger is pressed (grabberRetract),
            then the servo should move with a power
            factor of 1.
             */
            if (grabberRetract)
            {
                // intakeServo.setDirection(CRServo.Direction.FORWARD);
                intakeServo.setPower(1.0);
            }

            /*
            If the left trigger is pressed (grabberExtend),
            then the servo should move with a power
            factor of -1.
             */
            if (grabberExtend)
            {
                // intakeServo.setDirection(CRServo.Direction.REVERSE);
                intakeServo.setPower(-1.0);
            }
            if (grabberRelease)
            {
                pincherServo.setPosition(1);
            }
            if (grabberPinch)
            {
                pincherServo.setPosition(0);
            }
            if (digitalTouch.isPressed() && gamepad2.left_trigger > 0) {
                intakeServo.setPower(0.0);
            }
            // LifterMotor Button A Button B if statements
            if (ButtonA) lifterUp = true;
            if (ButtonB) lifterDown = true;;
            if (lifterDown && lifterUp) {
                lifterDown = false;
                lifterUp = false;
            }
            if (lifterUp = true) {
                lifterMotor.setPower(0.5);
            }
            if (lifterDown = true) {
                lifterMotor.setPower(-0.5);
            }

            telemetry.addData("SENSOR: ", digitalTouch.isPressed());
            telemetry.update();
            // write the values to the motors
            motorFrontRight.setPower(FrontRight);
            motorFrontLeft.setPower(FrontLeft);
            motorBackLeft.setPower(BackLeft);
            motorBackRight.setPower(BackRight);




            /*
             * Telemetry for debugging
             */
            telemetry.addData("Text", "*** Robot Data***");
            telemetry.addData("grabberRetract", grabberRetract);
            telemetry.addData("grabberExtend", grabberExtend);
            telemetry.addData("ServoPower", intakeServo.getPower());
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
