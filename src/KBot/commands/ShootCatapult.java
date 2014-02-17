package KBot.commands;

/**
 *
 * @author bradmiller
 */
public class ShootCatapult extends CommandBase 
{
    public ShootCatapult() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.catapult);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        CommandBase.catapult.initializeShootRoutine();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        CommandBase.catapult.calibratePotentiometer();
        if(CommandBase.catapult.isCalibrated() && !CommandBase.catapult.hasPotFailed())
        {
            CommandBase.catapult.shootRoutineByPotentiometer();
        }
        else
        {
            if(CommandBase.catapult.hasPotFailed())
            {
                System.out.println("Potentiometer has failed!");
            }
            CommandBase.catapult.shootRoutineByTiming();
        }       
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return CommandBase.catapult.finished;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        CommandBase.catapult.endShootRoutine();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        end();
    }
}
