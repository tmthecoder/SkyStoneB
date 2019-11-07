package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class intake extends OpMode {

    DcMotor intakeServo;

    intakeServo = hardwareMap.dcMotor.get("intakeDrive");

    float gamepadR = gamepad1.right_trigger;
    float gamepadL = gamepad1.left_trigger;
    float gamepadRB = gamepad1.right_bumper;
    float gamepadLB = gamepad1.left_bumper;

    while(true) {

        if (gamepadR > 0) {
            intakeServo.setPower(1);
            if
        } else if (gamepadL > 0) {
            intakeServo.setPower(-1);
        } else {
            intakeServo.setPower(0);
        }

    }

}  