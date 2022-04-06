// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
/**moves the magazine backwards to prevent interference with the shooter */
public class BackSpace extends MagMoveBase {
  /** Creates a new BackSpace. */
  Intake intake;
  public BackSpace(Magazine magazine, Intake intake) {
    super(magazine);
    this.intake = intake;
    speed = 0.6;
    movement_time = SetpointConstants.MAGAZINE_BACKSPACE_DISTANCE;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  @Override
  public void initialize() {
    intake.setIntakePercent(-0.3);
    super.initialize();
  }
  @Override
  public void end(boolean interrupted) {
      intake.setIntakePercent(0);
      super.end(interrupted);
  }
}
