package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Concept: HolonomicDrive", group = "Concept")

public class intalke2 extends OpMode{
    CRServo WheelThingy;

    @Override
    public void init ()
    {
        WheelThingy = hardwareMap.crservo.get("WheelThingy");
    }

    @Override
    public void loop ()
    {
        boolean ButtonA = gamepad2.a,
                ButtonB = gamepad2.b;
        if (ButtonA)
        {
            WheelThingy.setPower(-1.0);
        }
        else if (ButtonB)
        {
            WheelThingy.setPower(1.0);
        }

    }
}
