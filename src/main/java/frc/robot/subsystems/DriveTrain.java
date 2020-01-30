/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends SubsystemBase {
  private PWMVictorSPX leftMotor;
  private PWMVictorSPX rightMotor;
  private DifferentialDrive diffDrive;
  private double speed;
  private double turn;
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    leftMotor = new PWMVictorSPX(2);
    addChild("Left Motor",leftMotor);
    leftMotor.setInverted(false);
        
    rightMotor = new PWMVictorSPX(3);
    addChild("Right Motor",rightMotor);
    rightMotor.setInverted(false);

    diffDrive = new DifferentialDrive(leftMotor, rightMotor);
    addChild("Differential Drive",diffDrive);
    diffDrive.setSafetyEnabled(true);
    diffDrive.setExpiration(0.1);
    diffDrive.setMaxOutput(1.0);
    diffDrive.setDeadband(0.05);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    diffDrive.curvatureDrive(Math.pow(speed, 3), Math.pow(turn, 2) * Math.signum(turn), Math.abs(speed) < 0.5);
  }

  public void setArcadeDrive(double speedIn, double turnIn){
    speed = speedIn;
    turn = turnIn;
  }
}
