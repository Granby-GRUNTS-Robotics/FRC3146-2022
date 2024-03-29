// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/**intake for autonomous */
public class IntakeSpeedBase extends CommandBase {
  private Intake intake;
  boolean is_instantaneous;
  protected double percent;
  public IntakeSpeedBase(Intake intake, boolean is_instantaneous) {
    this.is_instantaneous = is_instantaneous;
    this.intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.setIntakePercent(percent);
  }
  @Override
  public void end(boolean interrupted) {
      if(!is_instantaneous)intake.setIntakePercent(0);
  }
  @Override
  public boolean isFinished() {
      return is_instantaneous;
  }
}
