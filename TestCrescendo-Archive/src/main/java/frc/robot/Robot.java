//FRC Origin & Licensed
//This Program sets up & runs specific things under certain circumstances

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

//On start-up run:
  @Override
  public void robotInit() {
    //see RobotContainer. This creates it.
    m_robotContainer = new RobotContainer();
  }

//runs in all modes at 20ms, use for testing.
  @Override
  public void robotPeriodic() {
    //Schedules all commands and polls buttons. Used for Command-based framework
    CommandScheduler.getInstance().run();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. (????) */
  @Override
  public void autonomousInit() {
    //????
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command if it exists (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    
    //Kills auto on teleop!!!!!
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {

    // Kills all commands on test start
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}
}
