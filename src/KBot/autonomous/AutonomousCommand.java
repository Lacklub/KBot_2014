/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KBot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.commands.*;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author KBotics
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() 
    {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
            
            addSequential(new VisionCommand());
            addSequential(new AutonomousDrive(2.5));
            addParallel(new LoadCatapult());
            addSequential(new WaitCommand(1.5));
            addSequential(new ShootCatapult());
    }
        
        //addSequential(new TimedOutDriveCommand());
        
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
}