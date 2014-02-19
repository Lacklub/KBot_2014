package KBot.subsystems;

import KBot.RobotMap;
import KBot.commands.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem; 
/**
 * @author KBot
 */
public class DriveSystem extends Subsystem 
{
    private final double kP = 0.1;
    private final double kI = 0;
    private final double kD = 0.0;
    private final double maxSpeed = 3; //meters/sec
    private double lastLeftCount = 0, lastRightCount = 0;
    private double leftCount = 0, rightCount = 0;
    private double leftSpeed = 0, rightSpeed = 0;
    
    
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
    
    public void driveWithPID(double left, double right)
    {
        left = modifyInputs(left); right = modifyInputs(right);
        left = maxSpeed * left; right = maxSpeed * right;
        double lRate, rRate;
        
        leftCount = RobotMap.leftEncoder.get();
        rightCount = RobotMap.rightEncoder.get();
        
        /*Unit calculation:
         * speed = [tick/cycle - tick/cycle] * rev(encoder)/tick * 
         * rev(wheel)/rev(encoder)* m/rev(wheel) * cycles/sec
         */
        lRate = (leftCount - lastLeftCount) * (1.0/360) * 2*Math.PI*0.088 * 50;
        rRate = (rightCount - lastRightCount) * (1.0/360) * (2*Math.PI*(0.088)) * 50;
        double pLeft = (left - lRate)*kP, pRight = (right - rRate)*kP;
        
        leftSpeed += pLeft;// + iTerm + dTerm;
        rightSpeed += pRight;
        System.out.println("right speed: " + rightSpeed);
        System.out.println("left speed: " + leftSpeed);
        
        lastLeftCount = leftCount; lastRightCount = rightCount; 
        
        setLeftMotors(-leftSpeed); setRightMotors(rightSpeed);
    }
    
    private void setLeftMotors(double value)
    {
        RobotMap.leftBackMotor.set(value);
        RobotMap.leftFrontMotor.set(value);
        RobotMap.leftTopMotor.set(value);
    }
    
    private void setRightMotors(double value)
    {
        RobotMap.rightBackMotor.set(value);
        RobotMap.rightFrontMotor.set(value);
        RobotMap.rightTopMotor.set(value);
    }
}

