/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KBot.autonomous;

import KBot.commands.*;

/**
 *
 * @author KBotics
 */
public class AutonomousDrive extends CommandBase {
    
    public AutonomousDrive(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.DriveTrain);
        setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.DriveTrain.drive(-0.6, -0.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.DriveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}