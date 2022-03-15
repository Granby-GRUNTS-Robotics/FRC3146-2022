// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

public class DrivePIDTune extends InstantCommand {
  /** Creates a new DrivePIDTune. */
  private Drivetrain drivetrain;
  public DrivePIDTune(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Drivetrain P", 0);
    SmartDashboard.setDefaultNumber("Drivetrain D", 0);
    SmartDashboard.setDefaultNumber("Drivetrain FF", 0);
    SmartDashboard.setDefaultNumber("Drivetrain Max Acc", 0);
    SmartDashboard.setDefaultNumber("Drivetrain Cruise Speed", 0);
    double p = SmartDashboard.getNumber("Drivetrain P", 0);
    double d = SmartDashboard.getNumber("Drivetrain D", 0);
    double f = SmartDashboard.getNumber("Drivetrain FF", 0);
    double a = SmartDashboard.getNumber("Drivetrain Max Acc", 0);
    double v = SmartDashboard.getNumber("Drivetrain Cruise Speed", 0);
    //drivetrain.setSmartMotionValues(a, v);
    drivetrain.setPIDF(p, d, f, 1);
  }
}
