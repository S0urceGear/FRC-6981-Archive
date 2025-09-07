package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;

public class Robot extends TimedRobot {

  private GenericHID ControlHandler;

  private final MotorController m_leftMotor = new WPI_VictorSPX(0);
  private final MotorController m_rightMotor = new WPI_VictorSPX(1);

  private DifferentialDrive m_myRobot = new DifferentialDrive(m_leftMotor,m_rightMotor);
  private Timer timer = new Timer();

  @Override
  public void robotInit() {
    m_rightMotor.setInverted(true);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    ControlHandler = new GenericHID(0);
    double feedforward = 0.07;
    m_leftMotor.set(ControlMode.Position, targetPos, DemandType.ArbitraryFeedForward, feedforward);
  }

  @Override
  public void teleopPeriodic() {
    double turbo;
    //Speed control
    if(ControlHandler.getRawButton(6)){turbo = 0.1;}
    else{
        if(ControlHandler.getRawButton(5)){turbo = 1;}
        else{turbo = 0.6;}
        }

    int Dpad = ControlHandler.getPOV();
    if(Dpad == 90){m_myRobot.tankDrive(-turbo,-turbo);}
    else if(Dpad == 270){m_myRobot.tankDrive(turbo,turbo);}
    else if(Dpad == 0) {m_myRobot.tankDrive(turbo,-turbo);}
    else if(Dpad == 180) {m_myRobot.tankDrive(-turbo,turbo);}
    else{
          double speed1 = -ControlHandler.getRawAxis(1)*turbo;
          double speed2 = ControlHandler.getRawAxis(5)*turbo;
          m_myRobot.tankDrive(speed1, speed2);
        }
   /* testing */
    // System.out.println(-m_leftStick.getRawAxis(1));
   System.out.println(ControlHandler.getPOV());
   // System.out.println(m_myRobot.toString());
  }

  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
    if(timer.get() == 15){timer.reset();}
  }
  @Override
  public void autonomousPeriodic()
  {
    
    
    
    /* Test Program: Decreasing Speed Turning */
    /* if(timer.get() < 2) {m_myRobot.tankDrive(0.8,0.8);}
    else if(timer.get() < 2) {m_myRobot.tankDrive(0.7,0.7);}
    else if(timer.get() < 3) {m_myRobot.tankDrive(0.6,0.6);}*/
    
  }
}
