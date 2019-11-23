package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name="Encoder", group="Pushbot")
public class Encoder extends LinearOpMode {
    /* Declare OpMode members. */

    public DcMotor  motorFL;
    public DcMotor  motorFrontRight;
    public DcMotor  motorBL;
    public DcMotor  motorBR;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        motorFL = hardwareMap.get(DcMotor.class, "FL");
        motorFrontRight = hardwareMap.get(DcMotor.class, "FRFR");
        motorBL = hardwareMap.get(DcMotor.class, "BL");
        motorBR = hardwareMap.get(DcMotor.class, "BR");
        // lift = hwMap.get(DcMotor.class,"Lift");
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        //motorBR.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");  //
        telemetry.update();

        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                motorFrontRight.getCurrentPosition(),
                motorFL.getCurrentPosition(),

                telemetry.update());

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoder(.2,1120);
        motorFrontRight.setPower(0);
        motorFL.setPower(0);
        sleep(500);
        turnleft(.3,10000);
        motorFrontRight.setPower(0);
        motorFL.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public void encoder(double speed, int distance){

        motorFrontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFL.setMode(DcMotor.RunMode.RESET_ENCODERS);

        motorFrontRight.setTargetPosition(-distance);
        motorFL.setTargetPosition(-distance);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFrontRight.setPower(Math.abs(speed));
        motorFL.setPower(Math.abs(speed));
        motorBR.setPower(Math.abs(speed));
        motorBL.setPower(Math.abs(speed));

        while (motorFL.getCurrentPosition()< distance){
            telemetry.addData("Path0",  "Target at %7d :%7d",
                    motorFrontRight.getTargetPosition(),
                    motorFL.getTargetPosition(),

                    telemetry.update());
            telemetry.addData("Path0",  "Starting at %7d :%7d",
                    motorFrontRight.getCurrentPosition(),
                    motorFL.getCurrentPosition(),

                    telemetry.update());

        }
        motorFrontRight.setPower(0);
        motorFL.setPower(0);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void turnleft (double speed, int distance){
        motorFrontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFL.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFrontRight.setTargetPosition(-distance);
        motorFL.setTargetPosition(distance);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFrontRight.setPower(speed);
        motorFL.setPower(-speed);
        // motorBR.setPower(speed);
        //motorBL.setPower(Math.abs(speed));

        while (motorFrontRight.getCurrentPosition()< distance){
            telemetry.addData("Path0",  "Target at %7d :%7d",
                    motorFrontRight.getTargetPosition(),
                    motorFL.getTargetPosition(),

                    telemetry.update());
            telemetry.addData("Path0",  "Starting at %7d :%7d",
                    motorFrontRight.getCurrentPosition(),
                    motorFL.getCurrentPosition(),

                    telemetry.update());

        }
        motorFrontRight.setPower(0);
        motorFL.setPower(0);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void turnright(double speed, int distance){
        motorFrontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFL.setMode(DcMotor.RunMode.RESET_ENCODERS);

        motorFrontRight.setTargetPosition(distance);
        motorFL.setTargetPosition(-distance);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFrontRight.setPower(Math.abs(speed));
        motorFL.setPower(Math.abs(speed));
        motorBR.setPower(Math.abs(-speed));
        motorBL.setPower(Math.abs(-speed));

        while (motorFL.getCurrentPosition()< distance){
            telemetry.addData("Path0",  "Target at %7d :%7d",
                    motorFrontRight.getTargetPosition(),
                    motorFL.getTargetPosition(),

                    telemetry.update());
            telemetry.addData("Path0",  "Starting at %7d :%7d",
                    motorFrontRight.getCurrentPosition(),
                    motorFL.getCurrentPosition(),

                    telemetry.update());

        }
        motorFrontRight.setPower(0);
        motorFL.setPower(0);

        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}