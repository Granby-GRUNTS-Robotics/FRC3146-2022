// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbSetSpeed extends CommandBase {
  private Climb climb;
  public ClimbSetSpeed(Climb climb) {
    this.climb = climb;
    addRequirements(climb);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  double velocity;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Climb Set Speed Value", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Climb Velocity", climb.getVelocity());
    velocity = SmartDashboard.getNumber("Climb Set Speed Value", 0);
    try {
      climb.setClimbVelocity(velocity);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    try {
      climb.setClimbPercent(0);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
