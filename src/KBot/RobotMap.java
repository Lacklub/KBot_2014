package KBot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;

    public static SpeedController leftFrontMotor;
    public static SpeedController leftBackMotor;
    public static SpeedController leftTopMotor;
    public static SpeedController rightFrontMotor;
    public static SpeedController rightBackMotor;
    public static SpeedController rightTopMotor;
    
    public static SpeedController leftCatapult;
    public static SpeedController rightCatapult;
    //public static RobotDrive drive;
    //public static RobotDrive miniDrive;
    public static SpeedController leftIntakeMotor;
    public static SpeedController rightIntakeMotor;
    
    public static Compressor compressor;
    
    public static Solenoid leftBrake;
    public static Solenoid rightBrake;
    
    public static DoubleSolenoid leftFlipper;
    public static DoubleSolenoid rightFlipper;
    
    public static DoubleSolenoid peter;
    
    public static DigitalInput shooterLimit;
    
    public static AnalogPotentiometer pot;
    public static boolean isPotentiometerSet;
    public static double potentiometerOffset;
    public static double potentiometerScaling;

    public static void init() {
        leftFrontMotor = new Talon(8);
        leftBackMotor = new Talon(10);
        leftTopMotor = new Talon(9);
        rightFrontMotor = new Talon(4);
        rightBackMotor = new Talon(1);
        rightTopMotor = new Talon(5);
        
        leftCatapult = new Talon(2);
        rightCatapult = new Talon(6);
        
        compressor = new Compressor(2,1);
        compressor.start();
        
        leftBrake = new Solenoid(2,3);
        rightBrake = new Solenoid(2,5);
        leftBrake.set(false);
        rightBrake.set(false);
        
        leftFlipper = new DoubleSolenoid(1, 1, 2);
        rightFlipper = new DoubleSolenoid(1, 3, 4);
        leftFlipper.set(DoubleSolenoid.Value.kReverse);
        rightFlipper.set(DoubleSolenoid.Value.kReverse);
        
        peter = new DoubleSolenoid(2, 1, 2);
        peter.set(DoubleSolenoid.Value.kForward);
        
        shooterLimit = new DigitalInput(1);
        pot = new AnalogPotentiometer(2);
        //drive = new RobotDrive(leftFrontMotor, leftBackMotor,rightFrontMotor, rightBackMotor);
        //drive = new RobotDrive(leftFrontMotor, rightFrontMotor);
        //miniDrive = new RobotDrive(leftTopMotor, rightTopMotor);
        //drive.setSafetyEnabled(false);

        leftIntakeMotor = new Talon(3);
        rightIntakeMotor = new Talon(7);
        rightTopMotor.set(0.0);
        rightBackMotor.set(0.0);
        rightFrontMotor.set(0.0);
        leftBackMotor.set(0.0);
        leftTopMotor.set(0.0);
        leftFrontMotor.set(0.0);

    }
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
