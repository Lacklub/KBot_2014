package KBot;

import KBot.commands.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    public XboxController driver;
    public XboxController operator;
    Button leftFlipperButton;
    Button rightFlipperButton;
    Button shoot;
    Button driverIntakeToggle;
    //Button operatorIntakeToggle;
    Button pass;
    Button load;
    
    
    public OI()
    {
        driver  = new XboxController(1);
        operator  = new XboxController(2);
        leftFlipperButton = new JoystickButton(driver.m_joy, XboxController.XBOX_LB);
        rightFlipperButton = new JoystickButton(driver.m_joy, XboxController.XBOX_RB);
        shoot = new JoystickButton(operator.m_joy, XboxController.XBOX_Y);
        driverIntakeToggle = new JoystickButton(driver.m_joy, XboxController.XBOX_B);
        //operatorIntakeToggle = new JoystickButton(operator.m_joy, XboxController.XBOX_LB);
        pass = new JoystickButton(operator.m_joy, XboxController.XBOX_A);
        load = new JoystickButton(operator.m_joy, XboxController.XBOX_START);
        
        driverIntakeToggle.whenPressed(new IntakeDown());
        //operatorIntakeToggle.whenPressed(new IntakeDown());
        leftFlipperButton.whenPressed(new ActivateLeftFlippers());
        rightFlipperButton.whenPressed(new ActivateRightFlippers());
        shoot.whenPressed(new ShootCatapult());
        pass.whenPressed(new PassCatapult());
        load.whenPressed(new LoadCatapult());
        
    }
}

