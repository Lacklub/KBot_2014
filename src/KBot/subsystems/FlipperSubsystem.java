/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KBot.subsystems;

import KBot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author KBotics
 */
public class FlipperSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void switchLeftFlippers(){
        if(RobotMap.leftFlipper.get() == DoubleSolenoid.Value.kForward){
            RobotMap.leftFlipper.set(DoubleSolenoid.Value.kReverse);
        }
        else if(RobotMap.leftFlipper.get() == DoubleSolenoid.Value.kReverse){
            RobotMap.leftFlipper.set(DoubleSolenoid.Value.kForward);
        }
        else{
            RobotMap.leftFlipper.set(DoubleSolenoid.Value.kForward);
        }
    }
    public void switchRightFlippers(){
        if(RobotMap.rightFlipper.get() == DoubleSolenoid.Value.kForward){
            RobotMap.rightFlipper.set(DoubleSolenoid.Value.kReverse);
        }
        else if(RobotMap.rightFlipper.get() == DoubleSolenoid.Value.kReverse){
            RobotMap.rightFlipper.set(DoubleSolenoid.Value.kForward);
        }
        else{
            RobotMap.rightFlipper.set(DoubleSolenoid.Value.kForward);
        }
    }
}