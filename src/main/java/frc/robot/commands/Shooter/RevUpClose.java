// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Shooter;

public class RevUpClose extends RevUpBase {

  /** Creates a new SetShooterSpeed. */
  public RevUpClose(Shooter shooter) {
    super(shooter);
    speed = SetpointConstants.ShooterSpeedClose;
  }
}
