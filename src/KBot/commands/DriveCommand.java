package KBot.commands;

/**
 *
 * @author bradmiller
 */
public class DriveCommand extends CommandBase 
{
    private boolean driveState;
    private boolean lastAState;
    
    private double persistentLeftValue;
    private double persistentRightValue;
    //private XboxController driver;
    public DriveCommand() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        driveState = false;
        lastAState = true;
        persistentLeftValue = 0;
        persistentRightValue = 0;
        //driver = CommandBase.oi.driver;
        requires(CommandBase.DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        CommandBase.DriveTrain.drive(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        double left = CommandBase.oi.driver.getJoyLeftY();
        double right = CommandBase.oi.driver.getJoyRightY();
        boolean button = CommandBase.oi.driver.getA();
        
        if(!lastAState && button)
        {
            driveState = !driveState;
        }
        lastAState = button;
        
        persistentLeftValue = 1*left + 0*persistentLeftValue;
        persistentRightValue = 1*right + 0*persistentRightValue;
        
        if(driveState)
        {
            CommandBase.DriveTrain.drive(persistentLeftValue, persistentRightValue);
        }
        else{
            CommandBase.DriveTrain.drive(-persistentRightValue, -persistentLeftValue);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        CommandBase.DriveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        end();
    }
}
