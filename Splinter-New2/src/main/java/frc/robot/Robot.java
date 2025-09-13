package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
//import com.revrobotics.CANSparkLowLevel.MotorType;


public class Robot extends TimedRobot {

  private GenericHID ControlHandlerD;

  private final MotorController m_leftMotor1 = new WPI_VictorSPX(2);
  private final MotorController m_rightMotor1 = new WPI_VictorSPX(3);
  private final MotorController m_leftMotor2 = new WPI_VictorSPX(0);
  private final MotorController m_rightMotor2 = new WPI_VictorSPX(1);
  private DifferentialDrive m_myRobot1 = new DifferentialDrive(m_leftMotor1,m_rightMotor1);
  private DifferentialDrive m_myRobot2 = new DifferentialDrive(m_leftMotor2,m_rightMotor2);
  private Timer timer = new Timer();
  private Timer delay = new Timer();

  SparkMax m_launchWheel;
  SparkMax m_feedWheel;
  SparkMax m_GrabberL;
  SparkMax m_GrabberR;
 // SparkMax AmpArm;

  SparkMaxConfig c_launchWheel;
  SparkMaxConfig c_feedWheel; 
  //SparkMaxConfig c_GrabberL;
  //SparkMaxConfig c_GrabberR;
  //SparkMaxConfig c_AmpArm;


  @Override
  public void robotInit() {
    m_rightMotor1.setInverted(true);
    m_rightMotor2.setInverted(true);
    ControlHandlerD = new GenericHID(0);

    c_launchWheel.smartCurrentLimit(80);
    c_feedWheel.smartCurrentLimit(80);

    m_launchWheel = new SparkMax(5, MotorType.kBrushed);
    m_feedWheel = new SparkMax(6, MotorType.kBrushed);

    m_GrabberL = new SparkMax(7, MotorType.kBrushless);
    m_GrabberR = new SparkMax(8, MotorType.kBrushless);
    
   // AmpArm = new SparkMax(9, MotorType.kBrushed);
  }

  @Override
  public void teleopPeriodic() {
    double turbo;


    //Shooter Ring Command
    if(ControlHandlerD.getRawButton(1)){
      m_feedWheel.set(1); 
      if(ControlHandlerD.getRawButtonPressed(1)){
      delay.reset();
      delay.start();
      }
      if(delay.get() >= 1.5 && (ControlHandlerD.getRawButton(1))) {m_launchWheel.set(1);}
    }
    else if(ControlHandlerD.getRawButton(2)){m_feedWheel.set(-0.5); m_launchWheel.set(-1);}
    else {m_feedWheel.set(0); m_launchWheel.set(0);}


    //Amp Arm Commands
   /* if(ControlHandlerD.getRawAxis(2) > 0.5) {AmpArm.set(0.5);}
  else if(ControlHandlerD.getRawAxis(3) > 0.5){AmpArm.set(-0.5);}
    else{AmpArm.set(0);}*/


    //Speed control Commands
    if(ControlHandlerD.getRawButton(6)){turbo = 0.5   ;}
    else{
        if(ControlHandlerD.getRawButton(5)){turbo = 1;}
        else{turbo = 0.75;}
        }

    //Climber Commands
    if(ControlHandlerD.getRawButton(3)){m_GrabberL.set(0.2); m_GrabberR.set(-0.2); }
    else if(ControlHandlerD.getRawButton(4)){m_GrabberL.set(-0.2); m_GrabberR.set(0.2);}
    else {m_GrabberL.set(0);m_GrabberR.set(0);}

    //Movement Commands
    int Dpad = ControlHandlerD.getPOV();
    if(Dpad == 90){m_myRobot1.tankDrive(turbo,-turbo); m_myRobot2.tankDrive(turbo,-turbo);}
    else if(Dpad == 270){m_myRobot1.tankDrive(-turbo,turbo); m_myRobot2.tankDrive(-turbo,turbo);}
    else if(Dpad == 0) {m_myRobot1.tankDrive(turbo,turbo); m_myRobot2.tankDrive(turbo,turbo);}
    else if(Dpad == 180) {m_myRobot1.tankDrive(-turbo,-turbo); m_myRobot2.tankDrive(-turbo,-turbo);}
    else{
          double speed1 = ControlHandlerD.getRawAxis(1)*turbo;
          double speed2 = ControlHandlerD.getRawAxis(5)*turbo;
          m_myRobot1.tankDrive(speed1, speed2); m_myRobot2.tankDrive(speed1, speed2);
        }
      }
   /* testing */
    // System.out.println(-m_leftStick.getRawAxis(1));
   ///System.out.println(ControlHandlerD.getPOV());
   // System.out.println(m_myRobot.toString());

  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();

    if(timer.get() == 15){timer.reset();}
  }
  @Override
  public void autonomousPeriodic()
  {
        /* AutoCode AMP */
    /*if(timer.get() < 1) {m_myRobot1.tankDrive(-0.8,-0.8); m_myRobot2.tankDrive(-0.8,-0.8); }
    else if(timer.get() < 3) {m_myRobot1.tankDrive(0,0); m_myRobot2.tankDrive(0,0); AmpArm.set(0.5);}*/

    /* AutoCode SPEAK */
    if(timer.get() < 3) {
      m_feedWheel.set(1); 
      if (timer.get() > 1) {m_launchWheel.set(1);}}
    else if (timer.get() < 7) {m_myRobot1.tankDrive(-0.5, -0.5); m_myRobot2.tankDrive(-0.5, -0.5);}
    else if (timer.get() < 8) {m_myRobot1.tankDrive(0, 0); m_myRobot2.tankDrive(0, 0);m_launchWheel.set(0);m_feedWheel.set(0); }


    /* Testing */
    /*if(timer.get() < 0.5) {m_myRobot1.tankDrive(-0.6,-0.8); m_myRobot2.tankDrive(-0.8,-0.8); }
    else if(timer.get() < 1) {m_myRobot1.tankDrive(0.8,0.8); m_myRobot2.tankDrive(0.8,0.8); }
    else if(timer.get() < 1.5) {m_myRobot1.tankDrive(0.8,-0.8); m_myRobot2.tankDrive(0.8,-0.8); }
    else if(timer.get() < 2) {m_myRobot1.tankDrive(-0.8,0.8); m_myRobot2.tankDrive(-0.8,0.8); }*/
  }
}

