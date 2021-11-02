package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
    public void runOpMode(){
        Robot robot = new Robot(hardwareMap, gamepad1, gamepad2); // creates new robot

        // teleop managers
    

        waitForStart();
        while(opModeIsActive()){
            robot.update();
        }
    }
}