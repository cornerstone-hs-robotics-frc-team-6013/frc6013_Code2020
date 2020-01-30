/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

/**
 * An example command that uses an example subsystem.
 */
public class TeleopDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_DriveTrain;
  private Joystick controller;
  private final double turnP = 0.75;

  /**
   * Creates a new TeleopDrive.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDrive(DriveTrain subsystem) {
    m_DriveTrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    controller = RobotContainer.getJoystick();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = -controller.getRawAxis(1); // inverts
    double turn = controller.getRawAxis(2)*turnP; 
    double topSpeed = .5*(1-controller.getRawAxis(3)); //lowers max speed based on slider position
    m_DriveTrain.setArcadeDrive(speed*topSpeed, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
