// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.Constants.ControlConstants;
import frc.robot.subsystems.Shooter;

public class ShooterPIDTune extends InstantCommand {
  /** Creates a new ShooterPIDTune. */
  private Shooter shooter;
  public ShooterPIDTune(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Shooter P", ControlConstants.SHOOTER_kP);
    SmartDashboard.setDefaultNumber("Shooter I", ControlConstants.SHOOTER_kI);
    SmartDashboard.setDefaultNumber("Shooter D", ControlConstants.SHOOTER_kD);
    SmartDashboard.setDefaultNumber("Shooter FF", ControlConstants.SHOOTER_kV);
    SmartDashboard.setDefaultNumber("Shooter I Max", 0);
    double p = SmartDashboard.getNumber("Shooter P", 0);
    double i = SmartDashboard.getNumber("Shooter I", 0);
    double d = SmartDashboard.getNumber("Shooter D", 0);
    double f = SmartDashboard.getNumber("Shooter FF", 0);
    //double imax = SmartDashboard.getNumber("Shooter I Max", 0);
    shooter.setPDF(p, i, d, f, 0);
  }
}
