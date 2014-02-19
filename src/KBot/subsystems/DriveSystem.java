package KBot.subsystems;

import KBot.RobotMap;
import KBot.commands.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem; 
/**
 * @author KBot
 */
public class DriveSystem extends Subsystem 
{
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
    public void drive(double left, double right) 
    {
        left = modifyInputs(left);
        right = modifyInputs(right);
        
        RobotMap.leftTopMotor.set(-left);
        RobotMap.leftBackMotor.set(-left);
        RobotMap.leftFrontMotor.set(-left);
        RobotMap.rightFrontMotor.set(right);
        RobotMap.rightBackMotor.set(right);
        RobotMap.rightTopMotor.set(right);
    }
    
    private double modifyInputs(double input)
    {
        if((input < 0.1) && (input > -0.1))
        {
            input = 0;
        }
        
        if(input > 0.95)
        {
            input = 1;
        }
        if(input < -0.95)
        {
            input = -1;
        }
        
        if(input < 0)
        {
            input = -input*input;
        }
        else
        {
            input = input*input;
        }
        return input;
    }
}

