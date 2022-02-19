// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RevUpShuffleboard extends CommandBase {
  /** Creates a new ShooterSpeedBase. */

  protected final Shooter shooter; 
  protected double speed;
  
  public RevUpShuffleboard(Shooter shooter) {
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Target Speed", 0);
  }


  @Override
  public void execute() {
    speed = SmartDashboard.getNumber("Target Speed", 0);
    shooter.setSpeed(speed);

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.brake();
    
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;//Math.abs(shooter.getError()) < SetpointConstants.SHOOTER_PRECISISON;
  }
}
