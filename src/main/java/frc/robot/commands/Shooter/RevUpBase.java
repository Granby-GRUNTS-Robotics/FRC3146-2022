// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Shooter;
/**base class for revving up the flywheel.*/
public class RevUpBase extends CommandBase {
  /** Creates a new ShooterSpeedBase. */

  protected final Shooter shooter; 
  protected double speed;
  protected double precision = SetpointConstants.SHOOTER_PRECISISON;
  boolean instant = false;
  
  public RevUpBase(Shooter shooter) {
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }
  public RevUpBase(Shooter shooter, boolean instant) {
    this.shooter = shooter;
    this.instant = instant;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Rev Up Speed", speed);
    SmartDashboard.putString(Constants.SHOOT_STRING, "Revving Up");
    shooter.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return instant ? true : Math.abs(shooter.getError()) < precision;
  }
}
