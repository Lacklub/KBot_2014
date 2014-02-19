/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KBot.commands;

/**
 *
 * @author KBotics
 */
public class PrintValues extends CommandBase {
    
    public PrintValues() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.catapult);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.catapult.print();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}