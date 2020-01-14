package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name ="Concept: GrabTray", group = "Concept")
public class ServoGrabPlateTeleOp extends OpMode {


    public Servo TrayServo;
    public Servo TrayServo2;


    @Override
    public void init() {
    telemetry.update();
    telemetry.addData("Status", "Initialized");

        TrayServo = hardwareMap.servo.get("ServoTray");
        TrayServo2 = hardwareMap.servo.get("ServoTray2");
    }

    @Override
    public void loop() {

        boolean TrayServoDown = gamepad1.a;
        boolean TrayServoUp = gamepad1.b;


        if (TrayServoDown) {
           TrayServo.setPosition(1);
           TrayServo2.setPosition(1);
        }
        if (TrayServoUp)
            TrayServo.setPosition(0);
            TrayServo2.setPosition(0);
        if (TrayServoDown && TrayServoUp) {
            TrayServo.setPosition(.5);
        }
    }
}
