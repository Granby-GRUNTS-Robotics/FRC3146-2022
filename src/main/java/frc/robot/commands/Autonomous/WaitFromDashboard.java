// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class WaitFromDashboard extends WaitCommand {
  /** Creates a new WaitFromDashboard. */
  public WaitFromDashboard() {
    super(0.0);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  private double time;
  @Override
  public void initialize() {
    SmartDashboard.putString("YES!", "NO");
    time = SmartDashboard.getNumber("Auto Wait Time", 0);
    super.initialize();
  }
  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(time);
  }
}
