package org.firstinspires.ftc.teamcode.helperfunctions;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AS5600 {
    HardwareMap hardwareMap;
    AnalogInput angleOut; // analog output
    private double angle; // stores current angle
    private double correction; // voltage that corresponds to 0 rad
    private final double maxVoltage = 3.286;
    private final double minVoltage = 0.001;

    public AS5600(HardwareMap hardwareMap, String deviceName, double correction) {
        this.hardwareMap = hardwareMap;
        angleOut = hardwareMap.get(AnalogInput.class, deviceName);
        this.correction = correction;
    }

    // updates returns angle in pi to -pi format
    public double getAngle() {
        update();
        return MathFunctions.angleWrap(angle);
    }

    // gets output voltage, adjusts the voltage range, and linearizes the angle
    public void update() {
        angle = angleOut.getVoltage() - correction;
        angle -= minVoltage;
        angle /= (maxVoltage - minVoltage);
        angle *= (Math.PI * 2);
        applyLinearity(angle);
    }

    // applies function linearize the output
    public double applyLinearity(double angle) {
//        angle = MathFunctions.angleWrap(angle);
//        if(-Math.PI <= angle && angle <= -1.092)
//            angle = (angle + 0.04) / 0.99;
//        else if(-1.092 < angle && angle <= -0.291)
//            angle = angle / 1.04;
//        else if(-0.291 < angle && angle <= 0.816)
//            angle = angle / 1.02;
//        else if(0.816 < angle && angle <= 1.465)
//            angle = angle / 1.01;
//        else if(1.465 < angle && angle <= 1.475)
//            angle = (angle - 1.32) / 0.1;
//        else if(1.465 < angle && angle <= Math.PI)
//            angle = (angle + 0.1) / 1.021;
//        if(-Math.PI <= angle && angle <= -1.1)
//            angle = (angle + 0.03) / 1.005;
//        else if(-1.1 < angle && angle <= 0)
//            angle = angle / 1.05;
//        else if(0 < angle && angle < Math.PI)
//            angle = angle / 0.98;
        return angle;
    }

    // returns raw output voltage
    public double getVoltage() {
        return angleOut.getVoltage();
    }
}
