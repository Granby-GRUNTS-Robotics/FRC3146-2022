// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Shooter;
/**low shot */
public class RevUpLow extends RevUpBase {

  /** Creates a new SetShooterSpeed. */
  public RevUpLow(Shooter shooter) {
    super(shooter);
    speed = SetpointConstants.SHOOTER_SPEED_LOW;
  }
}
