// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
/**revs up with arbitrarily defined speed. Great for repeatable auto shots */
public class RevUpSet extends RevUpBase {
  /** Creates a new SetShooterSpeed. */
  public RevUpSet(Shooter shooter, double speed, boolean instant) {
    super(shooter, instant);
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }
  public RevUpSet(Shooter shooter, double speed) {
    super(shooter);
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }
}
