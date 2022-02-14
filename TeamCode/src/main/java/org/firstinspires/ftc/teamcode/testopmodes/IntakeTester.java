package org.firstinspires.ftc.teamcode.testopmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp(name = "Intake Test", group = "TestOpModes")
public class IntakeTester extends LinearOpMode {
    public static double power = 0.0;
    CRServo l1, l2, r1, r2;

    @Override
    public void runOpMode() throws InterruptedException {
        l1 = hardwareMap.get(CRServo.class, "l1");
        l2 = hardwareMap.get(CRServo.class, "l2");
        r1 = hardwareMap.get(CRServo.class, "r1");
        r2 = hardwareMap.get(CRServo.class, "r2");
        waitForStart();
        while(opModeIsActive()) {
            l1.setPower(power);
            l2.setPower(-power);
            r1.setPower(-power);
            r2.setPower(power);
        }
    }
}
