// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;

public class LimelightTune extends CommandBase {
  /** Creates a new LimelightTune. */
  LimeLight limeLight;
  public LimelightTune(LimeLight limeLight) {
    this.limeLight = limeLight;
    addRequirements(limeLight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Testing Angle", 45);
  }
  double angle = 45;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    angle = SmartDashboard.getNumber("Testing Angle", 45);
    SmartDashboard.putNumber("Testing Limelight Distance", limeLight.getDistanceFromAngle(angle));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
