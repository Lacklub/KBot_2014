package KBot.autonomous;

import KBot.RobotMap;
import KBot.commands.CommandBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author KBotics
 */
public class VisionCommand extends CommandBase {
    boolean isHot;
    public VisionCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        setTimeout(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isHot = CommandBase.vision.getHot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isHot== true || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}