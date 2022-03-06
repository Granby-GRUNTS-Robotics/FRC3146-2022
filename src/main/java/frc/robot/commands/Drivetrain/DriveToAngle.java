// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**drives to set angle. Uses Pigeon for feedback */
public class DriveToAngle extends CommandBase {
  private Drivetrain drivetrain;
  protected double angle;
  /** Creates a new DriveToAngle. */
  public DriveToAngle(Drivetrain drivetrain, double angle) {
    this.drivetrain = drivetrain;
    this.angle = angle;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.setGoalAngle(angle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.setGoalAngle(drivetrain.getAngularError());
    SmartDashboard.putNumber("angular error", drivetrain.getAngularError());
    SmartDashboard.putNumber("counter", counter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  int counter = 0;
  @Override
  public boolean isFinished() {
    if (Math.abs(drivetrain.getAngularError()) < 5){
      counter++;
    }else counter = 0;
    return counter > 200;
  }
}
