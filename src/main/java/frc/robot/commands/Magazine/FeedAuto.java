// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Magazine;
/**feed for shooting balls. stil need to decrease feed distance */
public class FeedAuto extends MagMoveBase {
  /** Creates a new BackSpace. */
  public FeedAuto(Magazine magazine) {
    super(magazine);
    movement_time = 3;
    speed = 0.5;
    // Use addRequirements() here to declare subsystem dependencies.
  }
}
