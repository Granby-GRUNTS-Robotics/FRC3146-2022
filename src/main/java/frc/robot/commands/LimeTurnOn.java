// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;
/**turns on limelight LEDs. Only finishes when a good target is found
 * Might want to add a mode that lets you turn while searching for a good target, then lock on.
 */
public class LimeTurnOn extends CommandBase {
  /** Creates a new TurnOnLimeLight. */
  private LimeLight limeLight;
  public LimeTurnOn(LimeLight limeLight) {
    this.limeLight = limeLight;
    addRequirements(limeLight);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  boolean instant = false;
  public LimeTurnOn(LimeLight limeLight, boolean instant) {
    this.instant = instant;
    this.limeLight = limeLight;
    addRequirements(limeLight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limeLight.setLEDS(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return 
    limeLight.hasTarget() || instant;
  }
}
