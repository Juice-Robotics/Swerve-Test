package org.firstinspires.ftc.teamcode.helperfunctions.PID;

import com.qualcomm.robotcore.util.ElapsedTime;

public class BasicPID {
    public double desiredState, currentState; // setState, currentState
    public double kP, kI, kD; // PID coefficients
    public double kS; // static coefficient
    public double integral; // current integral
    public boolean firstSetStateLoop, firstGetOutputLoop;
    public double prevTime, prevError, startTime; // last loop + time
    public ElapsedTime time;

    public BasicPID(double kP, double kI, double kD, double kS, ElapsedTime time){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kS = kS;
        this.time = time;
        integral = 0;
        firstGetOutputLoop = true;
        firstSetStateLoop = true;
    }

    public double updatePID(double currentState){
        double currentTime = time.milliseconds();
        if(firstGetOutputLoop){
            firstGetOutputLoop = false;
            prevTime = currentTime;
            startTime = prevTime;
            prevError = 0;
        }

        double deltaTime = (currentTime - prevTime)/1000;
        double error = desiredState - currentState;
        double deltaError = error - prevError;
        prevTime = currentTime;
        prevError = error;
        integral += error * deltaTime;

        double derivative = 0;
        if(deltaTime != 0) {
            derivative = deltaError / deltaTime;
        }
        this.currentState = currentState;
        double power = kP * error + kI * integral + kD * derivative;
        if(shouldIntegralBeZeroed(error, power))
            clearIntegral();
        if (power != 0)
            return power + (Math.signum(power) * kS);
        return power;
    }

    // sets target state of PID
    public void setState(double desiredState){
        this.desiredState = desiredState;
    }

    // to be overridden in child classes (anti-windup method)
    public boolean shouldIntegralBeZeroed(double error, double power){
        return false;
    }

    // clears integral to avoid windup
    public void clearIntegral(){
        integral = 0;
    }
}