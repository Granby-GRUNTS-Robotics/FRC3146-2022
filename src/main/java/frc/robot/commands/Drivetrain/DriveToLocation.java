// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToLocation extends CommandBase {
  private Drivetrain drivetrain;
  private double distance;
  /** Creates a new DriveToLocation. 
   * @param distance the linear distance to move in inches
   * @param drivetrain the drivetrain subsystem to use
  */
  public DriveToLocation(Drivetrain drivetrain, double distance) {
    this.distance = distance;
    this.drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Distance", 0);
    //drivetrain.resetEncoders();
    distance = SmartDashboard.getNumber("Distance", 0);
    drivetrain.setGoalPositions(distance + drivetrain.getLeftPosition(), distance + drivetrain.getRightPosition());

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(drivetrain.getLinearError()) < 1;
  }
}
