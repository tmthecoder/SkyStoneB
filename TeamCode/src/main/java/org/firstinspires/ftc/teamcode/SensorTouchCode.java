package org.firstinspires.ftc.teamcode;

public class SensorTouchCode {
// set digital channel to input mode.
digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status","Initialized");
        telemetry.update();

    // Wait for the game to start (driver presses PLAY)
    waitForStart();

    // is button pressed?
if (digitalTouch.getState() == false) {
        // button is pressed.
        telemetry.addData("Button", "PRESSED");
    } else {
        // button is not pressed.
        telemetry.addData("Button", "NOT PRESSED");
    }

telemetry.addData("Status", "Running");
telemetry.update();
}