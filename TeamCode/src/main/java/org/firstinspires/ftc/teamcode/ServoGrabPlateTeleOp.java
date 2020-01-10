package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name ="Concept: GrabTray", group = "Concept")
public class ServoGrabPlateTeleOp extends OpMode {


    public Servo TrayServo;


    @Override
    public void init() {
    telemetry.update();
    telemetry.addData("Status", "Initialized");

        TrayServo = hardwareMap.servo.get("ServoTray");
    }

    @Override
    public void loop() {

        float TrayServoDown = gamepad1.left_trigger;
        float TrayServoUp = gamepad1.right_trigger;


        if (TrayServoDown > 0.1) {
           TrayServo.setPosition(1);
        }
        if (TrayServoUp > 0.1)
            TrayServo.setPosition(0);
        if (TrayServoDown > 0.1 && TrayServoUp > 0.1) {
            TrayServo.setPosition(.5);
        }
    }
}
