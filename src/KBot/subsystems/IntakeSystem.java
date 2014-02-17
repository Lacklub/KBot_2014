package KBot.subsystems;

import KBot.RobotMap;
import KBot.commands.IntakeCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author KBotics
 */
public class IntakeSystem extends Subsystem 
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeCommand());
    }
    
    public void runIntake(double speed) 
    {
        RobotMap.leftIntakeMotor.set(speed);
        RobotMap.rightIntakeMotor.set(-speed);
    }
    public void reverseIntake() 
    {
        RobotMap.leftIntakeMotor.set(-1);
        RobotMap.rightIntakeMotor.set(1);
    }
    
    public void stop()
    {
        RobotMap.leftIntakeMotor.set(0);
        RobotMap.rightIntakeMotor.set(0);
    }
    
    public void swithIntake()
    {
        if(RobotMap.peter.get() == DoubleSolenoid.Value.kForward)
        {
            RobotMap.peter.set(DoubleSolenoid.Value.kReverse);
        }
        else if(RobotMap.peter.get() == DoubleSolenoid.Value.kReverse)
        {
            RobotMap.peter.set(DoubleSolenoid.Value.kForward);
        }
        else
        {
            RobotMap.peter.set(DoubleSolenoid.Value.kForward);
        }
    }
}