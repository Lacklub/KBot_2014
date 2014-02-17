
package KBot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class Catapult extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Timer timer;
    public double initialPositionTime = 1;
    public double springBackTime = 0.05;
    public double shootTime = 0.6;
    public double zeroingTime = 0.3;
    public double stabalizingTime = 0.2;
    public double loadToBottom = 1.5;
    public boolean sensorReset;
    public boolean finished;
    private double lastCalibratedValue = 0;
    private double timeSinceLastState = 0;
    private boolean potentiometerFailed = false;
    
    public int shootingState = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void initializeShootRoutine(){
        timer = new Timer();
        finished = false;
        timer.reset();
        timer.start();
        initialPositionTime = 2;
        sensorReset = false;
        disengageBrake();
        shootingState = 0;
        RobotMap.peter.set(DoubleSolenoid.Value.kForward);
        timeSinceLastState = 0;
    }
    
    public void shootRoutineByTiming(){
        //System.out.println("shooting by timing");
        if(timer.get() < initialPositionTime && !isLimitPressed()){
            shoot(-0.8);
            disengageBrake();
            shootingState = 0;
        }
        else if(timer.get() < initialPositionTime + springBackTime){// && !CommandBase.catapult.isLimitPressed()){
            shoot(-1.0);
            disengageBrake();
            shootingState = 1;
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime){
            shoot(1);
            disengageBrake();
            shootingState = 2;
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime + zeroingTime){
            shoot(0.0);
            engageBrake();
            shootingState = 3;
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime + zeroingTime + stabalizingTime){
            shoot(-0.3);
            disengageBrake();
            shootingState = 4;
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime + zeroingTime + stabalizingTime + loadToBottom && !isLimitPressed())
        {
            shoot(-0.5);
            shootingState = 5;
        }
        else{
            engageBrake();
            shoot(0.0);
            finished = true;
            shootingState = 6;
        }
        
        if(timer.get() < initialPositionTime && isLimitPressed() && !sensorReset){
            initialPositionTime = timer.get();
            sensorReset = true;
            shootingState = 1;
        }
    }
    
    public void shootRoutineByPotentiometer(){
        double calibratedValue = (RobotMap.pot.get()-RobotMap.potentiometerOffset)/RobotMap.potentiometerScaling;
        if(calibratedValue != 0.0){
            calibratedValue = 1 - calibratedValue;
        }
        else{
            System.out.println("This isn't working");
            calibratedValue = lastCalibratedValue;
        }
        System.out.println("corrected value: " + calibratedValue);
        if(shootingState == 0 &&  calibratedValue > 0.05 && timer.get() - timeSinceLastState < 0.5){
            shoot(-0.5);
            disengageBrake();
            System.out.println("in state 0");
        }
        else if(shootingState == 0){
            shootingState = 1;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 1 && calibratedValue > 0 && !isLimitPressed() && timer.get() - timeSinceLastState < 0.5){
            shoot(-0.7);
            disengageBrake();
            System.out.println("in state 1");
        }
        else if(shootingState == 1){
            if (isLimitPressed() && calibratedValue>0.1) {
                potentiometerFailed=true;
            }
            shootingState = -2;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == -2 && calibratedValue < 0.2 && timer.get() - timeSinceLastState < 0.5){ //changing this should change shooting angle 
            shoot(0.8); // changing this should change shooting speed
            disengageBrake();
            System.out.println("in state -2");
        }
        else if(shootingState == -2){
            shootingState = 2;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 2 && calibratedValue < 1.1 && timer.get() - timeSinceLastState < 0.5){ //changing this should change shooting angle 
            shoot(1); // changing this should change shooting speed
            disengageBrake();
            System.out.println("in state 2");
        }
        else if(shootingState == 2){
            if (calibratedValue < 0.9) {
                potentiometerFailed = true;
            }
            shootingState = 3;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 3 && calibratedValue > 0.93 && timer.get() - timeSinceLastState < 0.5){
            shoot(-0.6);
            engageBrake();
            System.out.println("in state 3");
        }
        else if(shootingState == 3){
            shootingState = 4;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 4 && calibratedValue > 0.5 && timer.get() - timeSinceLastState < 0.5){
            shoot(-0.4);
            disengageBrake();
            System.out.println("in state 4");
        }
        else if(shootingState == 4){
            shootingState = 5;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 5 && !isLimitPressed() && calibratedValue > 0.1 && timer.get() - timeSinceLastState < 0.5){
            shoot(-0.6);
            disengageBrake();
            System.out.println("in state 5");
        }
        else if(shootingState == 5){
            shootingState = 6;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 6){
            engageBrake();
            shoot(0.0);
            finished = true;
            System.out.println("in state 6");
        }
        
        lastCalibratedValue = calibratedValue;
    }
    
    public void passRoutine(){
        double calibratedValue = (RobotMap.pot.get()-RobotMap.potentiometerOffset)/RobotMap.potentiometerScaling;
        if(calibratedValue != 0.0){
            calibratedValue = 1 - calibratedValue;
        }
        else{
            calibratedValue = lastCalibratedValue;
        }
        System.out.println("corrected value: " + calibratedValue);
        if(shootingState == 0){
            shootingState = 2;
        }
        
        if(shootingState == 1){
            shootingState = 2;
        }
        
        if(shootingState == 2 && calibratedValue < 0.8){ //changing this should change shooting angle 
            shoot(0.4); // changing this should change shooting speed
            disengageBrake();
            System.out.println("in state 2");
        }
        else if(shootingState == 2){
            shootingState = 3;
            timeSinceLastState = timer.get();
        }
        
        if(shootingState == 3 && calibratedValue > 0.93 && timer.get() - timeSinceLastState > 1){
            shoot(-0.6);
            engageBrake();
            System.out.println("in state 3");
        }
        else if(shootingState == 3 && timer.get() - timeSinceLastState > 1){
            shootingState = 4;
        }
        
        if(shootingState == 4 && calibratedValue > 0.5){
            shoot(-0.4);
            disengageBrake();
            System.out.println("in state 4");
        }
        else if(shootingState == 4){
            shootingState = 5;
        }
        
        if(shootingState == 5 && !isLimitPressed() && calibratedValue > 0.1){
            shoot(-0.6);
            disengageBrake();
            System.out.println("in state 5");
        }
        else if(shootingState == 5){
            shootingState = 6;
        }
        
        if(shootingState == 6){
            engageBrake();
            shoot(0.0);
            finished = true;
            System.out.println("in state 6");
        }
        
        lastCalibratedValue = calibratedValue;
    }
    
    public void loadRoutine(){
        double calibratedValue = (RobotMap.pot.get()-RobotMap.potentiometerOffset)/RobotMap.potentiometerScaling;
        if(calibratedValue != 0.0){
            calibratedValue = 1 - calibratedValue;
        }
        else{
            System.out.println("recieving 0");
            calibratedValue = lastCalibratedValue;
        }
        System.out.println("corrected value: " + calibratedValue);
        if(shootingState == 0 &&  calibratedValue > 0.1 && !isLimitPressed()){
            shoot(-0.7);
            disengageBrake();
            System.out.println("in state 0");
        }
        else{
            engageBrake();
            shoot(0.0);
            finished = true;
        }
        
        lastCalibratedValue = calibratedValue;
    }
    
    public boolean isCalibrated(){
        return RobotMap.isPotentiometerSet;
    }
    
    public void endShootRoutine(){
        finished = false;
        sensorReset = false;
        shootingState = 0;
        shoot(0.0);
        timeSinceLastState = 0;
    }
    
    public void shoot(double speed){
        speed = signSquared(speed);
        RobotMap.leftCatapult.set(speed);
        RobotMap.rightCatapult.set(-speed);
    }
    
    public void engageBrake(){
        //RobotMap.leftBrake.set(DoubleSolenoid.Value.kReverse);
        //RobotMap.rightBrake.set(DoubleSolenoid.Value.kReverse);
        RobotMap.leftBrake.set(false);
        RobotMap.rightBrake.set(false);
    }
    
    public void disengageBrake(){
        RobotMap.leftBrake.set(true);
        RobotMap.rightBrake.set(true);
        //RobotMap.leftBrake.set(DoubleSolenoid.Value.kOff);
        //RobotMap.rightBrake.set(DoubleSolenoid.Value.kOff);
    }
    
    public boolean isLimitPressed(){
        return !RobotMap.shooterLimit.get();
    }
    
    private double signSquared(double input){
        if(input > 0){
            input = -input*input;
        }
        else{
            input = input*input;
        }
        return input;
    }
    
    public boolean hasPotFailed(){
        return potentiometerFailed;
    }
    
    public void calibratePotentiometer(){
        if(isLimitPressed()){
            RobotMap.isPotentiometerSet = true;
            double potValue = RobotMap.pot.get();
            RobotMap.potentiometerOffset = (1.1515*potValue - 3.314);
            RobotMap.potentiometerScaling = potValue - (1.1515*potValue - 3.314); 
            // derived from measurements of max/min on both robots and approximate scaling with linear equation
        }
        if(RobotMap.isPotentiometerSet){
            //System.out.println((RobotMap.potentiometerOffset - RobotMap.pot.get())/RobotMap.potentiometerScaling);
        }
    }
}

