// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Magazine;
/**feed for shooting balls. stil need to decrease feed distance */
public class Feed extends MagMoveBase {
  /** Creates a new BackSpace. */
  public Feed(Magazine magazine) {
    super(magazine);
    movement_time = SetpointConstants.MAGAZINE_FEED_DISTANCE;
    // Use addRequirements() here to declare subsystem dependencies.
  }
}
