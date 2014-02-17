package KBot.commands;

import KBot.subsystems.Catapult;
import KBot.subsystems.FlipperSubsystem;
import KBot.subsystems.DriveSystem;
import KBot.subsystems.IntakeSystem;
import edu.wpi.first.wpilibj.command.Command;
import KBot.OI;
import KBot.subsystems.Vision;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command 
{
    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static DriveSystem DriveTrain = new DriveSystem();
    public static IntakeSystem Intake = new IntakeSystem();
    public static FlipperSubsystem flippers = new FlipperSubsystem();
    public static Catapult catapult = new Catapult();
    public static Vision vision;

    public static void init() 
    {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        vision = new Vision();
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
    }

    public CommandBase(String name) 
    {
        super(name);
    }

    public CommandBase() 
    {
        super();
    }
}
