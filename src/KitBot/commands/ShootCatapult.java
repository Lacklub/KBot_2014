
package KitBot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author bradmiller
 */
public class ShootCatapult extends CommandBase {
    /*Timer timer;
    double initialPositionTime = 2;
    double springBackTime = 0.05;
    double shootTime = 0.6;
    double zeroingTime = 0.3;
    double stabalizingTime = 0.2;
    double loadToBottom = 1.5;
    boolean sensorReset;
    boolean finished;*/
    public ShootCatapult() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.catapult);
        //System.out.print("DriveCatapultInitialized");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.catapult.initializeShootRoutine();
        /*CommandBase.catapult.timer.reset();
        CommandBase.catapult.timer.start();
        CommandBase.catapult.initialPositionTime = 2;
        CommandBase.catapult.sensorReset = false;
        CommandBase.catapult.disengageBrake();*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.catapult.calibratePotentiometer();
        if(CommandBase.catapult.isCalibrated() && !CommandBase.catapult.hasPotFailed()){
            CommandBase.catapult.shootRoutineByPotentiometer();
        }
        else{
            if(CommandBase.catapult.hasPotFailed()){
                System.out.println("Potentiometer has failed!");
            }
            CommandBase.catapult.shootRoutineByTiming();
        }
        
        /*if(timer.get() < initialPositionTime && !CommandBase.catapult.isLimitPressed()){
            CommandBase.catapult.shoot(-0.45);
            CommandBase.catapult.disengageBrake();
        }
        else if(timer.get() < initialPositionTime + springBackTime){// && !CommandBase.catapult.isLimitPressed()){
            CommandBase.catapult.shoot(-0.8);
            CommandBase.catapult.disengageBrake();
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime){
            CommandBase.catapult.shoot(1);
            CommandBase.catapult.disengageBrake();
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime + zeroingTime){
            CommandBase.catapult.shoot(0.0);
            CommandBase.catapult.engageBrake();
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime + zeroingTime + stabalizingTime){
            CommandBase.catapult.shoot(-0.3);
            CommandBase.catapult.disengageBrake();
        }
        else if(timer.get() < initialPositionTime + springBackTime + shootTime + zeroingTime + stabalizingTime + loadToBottom && !CommandBase.catapult.isLimitPressed())
        {
            CommandBase.catapult.shoot(-0.5);
        }
        else{
            CommandBase.catapult.engageBrake();
            CommandBase.catapult.shoot(0.0);
            finished = true;
        }
        
        if(timer.get() < initialPositionTime && CommandBase.catapult.isLimitPressed() && !sensorReset){
            initialPositionTime = timer.get();
            sensorReset = true;
        }*/
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return CommandBase.catapult.finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.catapult.endShootRoutine();
        /*CommandBase.catapult.finished = false;
        CommandBase.catapult.sensorReset = false;
        CommandBase.catapult.shoot(0.0);*/
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
