package org.firstinspires.ftc.teamcode;

/*import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
@Disabled
public class PlateGrabber extends LinearOpMode {
    // run until the end of the match (driver presses STOP)
    private Servo servoTest;
    double tgtPower = 0;
while(

    opModeIsActive())

    {
        tgtPower = -this.gamepad1.left_stick_y;
        motorTest.setPower(tgtPower);
        // check to see if we need to move the servo.
        if (gamepad1.y) {
            // move to 0 degrees.
            servoTest.setPosition(0);
        } else if (gamepad1.x || gamepad1.b) {
            // move to 90 degrees.
            servoTest.setPosition(0.5);
        } else if (gamepad1.a) {
            // move to 180 degrees.
            servoTest.setPosition(1);
        }
        telemetry.addData("Servo Position", servoTest.getPosition());
        telemetry.addData("Target Power", tgtPower);
        telemetry.addData("Motor Power", motorTest.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();

    }
}
 */