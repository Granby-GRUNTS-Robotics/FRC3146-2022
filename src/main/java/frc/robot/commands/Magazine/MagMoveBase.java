// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Magazine;

public class MagMoveBase extends CommandBase {
  private final Magazine magazine;
  protected double movement = 0;
  
  /** Creates a new MagBase. */
  public MagMoveBase(Magazine magazine) {
    this.magazine = magazine;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    magazine.setMagazinePercent((movement<=0)?-SetpointConstants.MAGAZINE_SPEED:SetpointConstants.MAGAZINE_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    magazine.setMagazinePercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(magazine.getMagPos()-movement) < SetpointConstants.MAGAZINE_PRECISION;
  }
}
