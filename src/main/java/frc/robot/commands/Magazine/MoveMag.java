// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import frc.robot.subsystems.Magazine;

public class MoveMag extends MagMoveBase {
  /** Creates a new BackSpace. */
  public MoveMag(Magazine magazine, double movement) {
    super(magazine);
    this.movement = movement;
    // Use addRequirements() here to declare subsystem dependencies.
  }
}
