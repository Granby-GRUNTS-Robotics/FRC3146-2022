// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Magazine;
/**base class for manually moving the magazine
 * implemented by the feed and backspace commands
 */
public class MagMoveBase extends CommandBase {
  private final Magazine magazine;
  protected double movement_time;
  private final Timer timer;
  
  /** Creates a new MagBase. */
  public MagMoveBase(Magazine magazine) {
    timer  = new Timer();
    this.magazine = magazine;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magazine);
  }

  /** Creates a new MagBase. */
  public MagMoveBase(Magazine magazine, double movement_time) {
    this.movement_time = movement_time;
    timer = new Timer();
    this.magazine = magazine;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    magazine.setMagazinePercent((movement_time<=0)?-SetpointConstants.MAGAZINE_SPEED:SetpointConstants.MAGAZINE_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    magazine.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > Math.abs(movement_time);
  }
}
