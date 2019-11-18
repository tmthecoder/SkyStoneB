package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name="Encodertesting", group="Pushbot")
public class Encoder extends LinearOpMode {
    /* Declare OpMode members. */

    public DcMotor  motorFL;
    public DcMotor  motorFR;
    public DcMotor  motorBL;
    public DcMotor  motorBR;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        motorFL = hardwareMap.get(DcMotor.class, "FL");
        motorFR = hardwareMap.get(DcMotor.class, "FR");
        motorBL = hardwareMap.get(DcMotor.class, "BL");
        motorBR = hardwareMap.get(DcMotor.class, "BR");
        // lift = hwMap.get(DcMotor.class,"Lift");
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        //motorBR.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");  //
        telemetry.update();

        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                motorFR.getCurrentPosition(),
                motorFL.getCurrentPosition(),

                telemetry.update());

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoder(.2,1120);
        motorFR.setPower(0);
        motorFL.setPower(0);
        sleep(500);
        turnleft(.3,10000);
        motorFR.setPower(0);
        motorFL.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public void encoder(double speed, int distance){

        motorFR.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFL.setMode(DcMotor.RunMode.RESET_ENCODERS);

        motorFR.setTargetPosition(-distance);
        motorFL.setTargetPosition(-distance);

        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFR.setPower(Math.abs(speed));
        motorFL.setPower(Math.abs(speed));
        motorBR.setPower(Math.abs(speed));
        motorBL.setPower(Math.abs(speed));

        while (motorFL.getCurrentPosition()< distance){
            telemetry.addData("Path0",  "Target at %7d :%7d",
                    motorFR.getTargetPosition(),
                    motorFL.getTargetPosition(),

                    telemetry.update());
            telemetry.addData("Path0",  "Starting at %7d :%7d",
                    motorFR.getCurrentPosition(),
                    motorFL.getCurrentPosition(),

                    telemetry.update());

        }
        motorFR.setPower(0);
        motorFL.setPower(0);

        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void turnleft (double speed, int distance){
        motorFR.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFL.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFR.setTargetPosition(-distance);
        motorFL.setTargetPosition(distance);

        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        // motorBR.setPower(speed);
        //motorBL.setPower(Math.abs(speed));

        while (motorFR.getCurrentPosition()< distance){
            telemetry.addData("Path0",  "Target at %7d :%7d",
                    motorFR.getTargetPosition(),
                    motorFL.getTargetPosition(),

                    telemetry.update());
            telemetry.addData("Path0",  "Starting at %7d :%7d",
                    motorFR.getCurrentPosition(),
                    motorFL.getCurrentPosition(),

                    telemetry.update());

        }
        motorFR.setPower(0);
        motorFL.setPower(0);

        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void turnright(double speed, int distance){
        motorFR.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorFL.setMode(DcMotor.RunMode.RESET_ENCODERS);

        motorFR.setTargetPosition(distance);
        motorFL.setTargetPosition(-distance);

        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFR.setPower(Math.abs(speed));
        motorFL.setPower(Math.abs(speed));
        motorBR.setPower(Math.abs(-speed));
        motorBL.setPower(Math.abs(-speed));

        while (motorFL.getCurrentPosition()< distance){
            telemetry.addData("Path0",  "Target at %7d :%7d",
                    motorFR.getTargetPosition(),
                    motorFL.getTargetPosition(),

                    telemetry.update());
            telemetry.addData("Path0",  "Starting at %7d :%7d",
                    motorFR.getCurrentPosition(),
                    motorFL.getCurrentPosition(),

                    telemetry.update());

        }
        motorFR.setPower(0);
        motorFL.setPower(0);

        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}