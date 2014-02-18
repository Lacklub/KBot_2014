package KBot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
    //Declare motors
    public static SpeedController rightBackMotor;
    public static SpeedController leftCatapult;
    public static SpeedController leftIntakeMotor;
    public static SpeedController rightFrontMotor;
    public static SpeedController rightTopMotor;
    public static SpeedController rightCatapult;
    public static SpeedController rightIntakeMotor;
    public static SpeedController leftFrontMotor;
    public static SpeedController leftTopMotor;
    public static SpeedController leftBackMotor;

    //Declare compressor
    public static Compressor compressor;
    
    //Declare brakes
    public static DoubleSolenoid leftBrake;
    public static DoubleSolenoid rightBrake;
    
    //Declare other solenoid
    public static DoubleSolenoid leftFlipper;
    public static DoubleSolenoid rightFlipper;
    public static DoubleSolenoid peter;
    
    //Declare input sensors
    public static DigitalInput shooterLimit;
    public static AnalogPotentiometer pot;
    
    //Declare network table for vision system
    public static NetworkTable visionTable;
    
    //Declare autonomous Timer
    public static Timer autonomousTimer;
    
    //THESE NEED TO BE MOVED TO WHERE POTENTIOMETER IS USED
    public static boolean isPotentiometerSet;
    public static double potentiometerOffset;
    public static double potentiometerScaling;

    public static void init() 
    {
        //Initializing motors
        rightBackMotor = new Talon(1);
        leftCatapult = new Talon(2);
        leftIntakeMotor = new Talon(3);
        rightFrontMotor = new Talon(4);
        rightTopMotor = new Talon(5);
        rightCatapult = new Talon(6);
        rightIntakeMotor = new Talon(7);
        leftFrontMotor = new Talon(8);
        leftTopMotor = new Talon(9);
        leftBackMotor = new Talon(10);
        
        //Initializing compressor  
        compressor = new Compressor(2,1);
        compressor.start();
        
        //Initializing Brake solenoids
        leftBrake = new DoubleSolenoid(2,3,4);
        leftBrake.set(DoubleSolenoid.Value.kReverse);
        rightBrake = new DoubleSolenoid(2,5,6);
        rightBrake.set(DoubleSolenoid.Value.kReverse);
        
        //Initializing all DoubleSolenoids
        leftFlipper = new DoubleSolenoid(1, 1, 2);
        leftFlipper.set(DoubleSolenoid.Value.kReverse);
        rightFlipper = new DoubleSolenoid(1, 3, 4);
        rightFlipper.set(DoubleSolenoid.Value.kReverse);
        peter = new DoubleSolenoid(2, 1, 2);
        peter.set(DoubleSolenoid.Value.kForward);
        
        //Initialize Input Sensors
        shooterLimit = new DigitalInput(1);
        pot = new AnalogPotentiometer(2);
        
        //Make sure all motors are stopped
        rightTopMotor.set(0.0);
        rightBackMotor.set(0.0);
        rightFrontMotor.set(0.0);
        leftBackMotor.set(0.0);
        leftTopMotor.set(0.0);
        leftFrontMotor.set(0.0);
        leftIntakeMotor.set(0.0);
        rightIntakeMotor.set(0.0);
        leftCatapult.set(0.0);
        rightCatapult.set(0.0);
        
        //initialize NetworkTable
        visionTable = NetworkTable.getTable("SmartDashboard");
        
        //initialize autonomous Timer
        autonomousTimer = new Timer();
    }
}
