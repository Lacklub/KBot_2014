
package KBot;

import KBot.commands.PassCatapult;
import KBot.commands.ShootCatapult;
import KBot.commands.ActivateLeftFlippers;
import KBot.commands.ActivateRightFlippers;
import KBot.commands.LoadCatapult;
import KBot.commands.IntakeDown;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public static XboxController driver;
    public static XboxController operator;
    Button leftFlipperButton;
    Button rightFlipperButton;
    Button shoot;
    Button intakeDown;
    Button pass;
    Button load;
    public OI()
    {
        driver  = new XboxController(1);
        operator  = new XboxController(2);
        leftFlipperButton = new JoystickButton(driver.m_joy, driver.XBOX_LB);
        rightFlipperButton = new JoystickButton(driver.m_joy, driver.XBOX_RB);
        shoot = new JoystickButton(driver.m_joy, driver.XBOX_Y);
        intakeDown = new JoystickButton(driver.m_joy, driver.XBOX_B);
        pass = new JoystickButton(driver.m_joy, driver.XBOX_X);
        load = new JoystickButton(driver.m_joy, driver.XBOX_START);
        
        intakeDown.whenPressed(new IntakeDown());
        leftFlipperButton.whenPressed(new ActivateLeftFlippers());
        rightFlipperButton.whenPressed(new ActivateRightFlippers());
        shoot.whenPressed(new ShootCatapult());
        pass.whenPressed(new PassCatapult());
        load.whenPressed(new LoadCatapult());
        
        
        //intake = new JoystickButton(driver.m_joy, driver.XBOX_RB);
        //intake.whileHeld(new IntakeCommand());
    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

