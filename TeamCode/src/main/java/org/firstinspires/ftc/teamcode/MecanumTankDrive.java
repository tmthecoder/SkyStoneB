package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MecanumTank")
public class MecanumTankDrive extends LinearOpMode {


    public DcMotor frontLeftDrive;
    public DcMotor backLeftDrive;

    public DcMotor frontRightDrive;
    public DcMotor backRightDrive;

    public void runOpMode()


    {
        backRightDrive = hardwareMap.dcMotor.get("backRightDrive");
        backLeftDrive = hardwareMap.dcMotor.get("backLeftDrive");

        frontRightDrive = hardwareMap.dcMotor.get("frontRightDrive");
        frontLeftDrive = hardwareMap.dcMotor.get("frontLeftDrive");

        waitForStart();


        while(opModeIsActive())
        {

            backLeftDrive.setPower(gamepad1.left_stick_y);
            frontLeftDrive.setPower(gamepad1.left_stick_y);

            backRightDrive.setPower(gamepad1.right_stick_y);
            frontRightDrive.setPower(gamepad1.right_stick_y);

        }


    }





}