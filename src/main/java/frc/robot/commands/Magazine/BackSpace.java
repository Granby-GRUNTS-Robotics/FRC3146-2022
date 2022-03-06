// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Magazine;
/**moves the magazine backwards to prevent interference with the shooter */
public class BackSpace extends MagMoveBase {
  /** Creates a new BackSpace. */
  public BackSpace(Magazine magazine) {
    super(magazine);
    movement_time = SetpointConstants.MAGAZINE_BACKSPACE_DISTANCE;
    // Use addRequirements() here to declare subsystem dependencies.
  }
}
