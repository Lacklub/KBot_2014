/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KBot.subsystems;

import KBot.RobotMap;
import KBot.autonomous.VisionCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author Sawyer
 */
public class Vision extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Command
    private NetworkTable visionTable = RobotMap.visionTable;
    
    
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getHot() 
    {
        if(visionTable.containsKey("BLOB_COUNT"))
        {
            if(visionTable.getNumber("BLOB_COUNT")==2)
            {
                return true;
            }
        }else{
            return false;
        }
        return false;
    }
}