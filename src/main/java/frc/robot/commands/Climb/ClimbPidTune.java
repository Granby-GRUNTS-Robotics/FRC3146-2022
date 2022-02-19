// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Climb;

public class ClimbPidTune extends InstantCommand {
  private Climb climb;
  /** Creates a new ClimbPidTune. */
  public ClimbPidTune(Climb climb) {
    this.climb = climb;
    addRequirements(climb);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Climb P", 0);
    SmartDashboard.setDefaultNumber("Climb D", 0);
    SmartDashboard.setDefaultNumber("Climb FF", 0);
    double p = SmartDashboard.getNumber("Climb P", 0);
    double d = SmartDashboard.getNumber("Climb D", 0);
    double f = SmartDashboard.getNumber("Climb FF", 0);

    climb.setPDF(p, d, f);
  }
}
