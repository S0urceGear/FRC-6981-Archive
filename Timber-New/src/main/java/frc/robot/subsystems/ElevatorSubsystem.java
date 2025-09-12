      
package frc.robot.subsystems;

/*import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.PneumaticsControlModule;*/
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ControlType;
/*import static edu.wpi.first.units.Units.Value;
import com.revrobotics.spark.SparkClosedLoopController;*/

public class ElevatorSubsystem extends SubsystemBase {

  boolean aPressed;

  //SparkMax addition motor capacity for elevator.
  private final SparkMax m_extendingspark1 = new SparkMax(DriveConstants.kElevatorLeftCanId,MotorType.kBrushless);
  private final SparkMax m_extendingspark2 = new SparkMax(DriveConstants.kElevatorRightCanId,MotorType.kBrushless);
  // private final SparkMax m_extendingspark3 = new SparkMax(DriveConstants.kIntakeTopCanId,MotorType.kBrushless);
  // private final SparkMax m_extendingspark4 = new SparkMax(DriveConstants.kIntakeBottomCanId,MotorType.kBrushless);

// private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, 25);


  public void Intake() {
    aPressed = !aPressed;
  }

  public void Up() {
    // m_extendingspark1.getClosedLoopController().setReference(-60, ControlType.kVelocity);
    // m_extendingspark2.getClosedLoopController().setReference(60, ControlType.kVelocity);
    m_extendingspark1.getClosedLoopController().setReference(-0.2, ControlType.kDutyCycle);
    m_extendingspark2.getClosedLoopController().setReference(0.2, ControlType.kDutyCycle);
    System.out.println("Going Up");
  }

  public void Down() {
    // m_extendingspark1.getClosedLoopController().setReference(60, ControlType.kVelocity);
    // m_extendingspark2.getClosedLoopController().setReference(-60, ControlType.kVelocity);
    m_extendingspark1.getClosedLoopController().setReference(0.2, ControlType.kDutyCycle);
    m_extendingspark2.getClosedLoopController().setReference(-0.2, ControlType.kDutyCycle);
    System.out.println("Going Down");
  }

  // public void UpToggle() {
  //   if (aPressed) {
  //     m_extendingspark3.getClosedLoopController().setReference(60, ControlType.kVelocity);
  //     System.out.println("Up Toggled");
  //   } else {
  //     m_extendingspark4.getClosedLoopController().setReference(60, ControlType.kVelocity);
  //     System.out.println("Up Toggled");
  //   }
  // }

  // public void DownToggle() {
  //   if (aPressed) {
  //     m_extendingspark3.getClosedLoopController().setReference(-60, ControlType.kVelocity);
  //     System.out.println("Down Toggled");
  //   } else {
  //     m_extendingspark4.getClosedLoopController().setReference(-60, ControlType.kVelocity);
  //     System.out.println("Down Toggled");
  //   }
  // }
}