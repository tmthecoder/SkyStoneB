package org.firstinspires.ftc.teamcode;
import com.qualcomm.ftccommon;
import com.qualcomm.robotcore.eventloop;
import com.qualcomm.robotcore.eventloop.opmode;
import com.qualcomm.robotcore.exception;
import com.qualcomm.robotcore.hardware;
import com.qualcomm.robotcore.util;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Scanner;

public class PlateGrabber {
    // run until the end of the match (driver presses STOP)
    // run until the end of the match (driver presses STOP)
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