package KBot.commands;

/**
 *
 * @author KBotics
 */
public class ActivateRightFlippers extends CommandBase 
{
    boolean lastAState;
    boolean driveState;
    public ActivateRightFlippers()
    {
        // Use requires() here to declare subsystem dependencies
        //requires(CommandBase.flippers);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        lastAState = true;
        driveState = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        boolean button = CommandBase.oi.driver.getA();
        
        if(!lastAState && button)
        {
            driveState = !driveState;
        }
        lastAState = button;
        
        if(driveState)
        {
            flippers.switchRightFlippers();
        }
        else
        {
            flippers.switchLeftFlippers();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}