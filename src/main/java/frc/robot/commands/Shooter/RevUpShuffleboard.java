// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;

/**FOR TESTING PURPOSES ONLY
 * 
 */
public class RevUpShuffleboard extends RevUpBase {
  /** Creates a new RevUpShuffleboard. */
  public RevUpShuffleboard(Shooter shooter) {
    super(shooter);
    speed = shooter.getSetSpeedEntryValue();
    // Use addRequirements() here to declare subsystem dependencies.
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
