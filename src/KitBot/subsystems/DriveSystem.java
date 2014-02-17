
package KitBot.subsystems;

import KitBot.RobotMap;
import KitBot.commands.CommandBase;
import KitBot.commands.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem; 
/**
 *
 */
public class DriveSystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveCommand());
    }
    public void drive(double left, double right) {
        left = modifyInputs(left);
        right = modifyInputs(right);
        
        //RobotMap.drive.tankDrive(right,left, true);
        RobotMap.leftTopMotor.set(-left);
        RobotMap.leftBackMotor.set(-left);
        RobotMap.leftFrontMotor.set(-left);
        RobotMap.rightFrontMotor.set(right);
        RobotMap.rightBackMotor.set(right);
        RobotMap.rightTopMotor.set(right);
    }
    
    private double modifyInputs(double input){
        if((input < 0.1) && (input > -0.1)){ // deadband
            input = 0;
        }
        
        if(input > 0.95){
            input = 1;
        }
        if(input < -0.95){
            input = -1;
        }
        
        if(input < 0){ // sign squareing values
            input = -input*input;
        }
        else{
            input = input*input;
        }
        return input;
    }
    
    //public void runMiniSims(double left, double right){
        
    //}
}

