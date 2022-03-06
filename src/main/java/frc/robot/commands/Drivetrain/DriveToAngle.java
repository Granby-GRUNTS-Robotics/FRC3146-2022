// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**drives to set angle. Uses Pigeon for feedback */
public class DriveToAngle extends CommandBase {
  protected Drivetrain drivetrain;
  protected double angle;
  protected double error;
  private int counter = 0;

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
    counter = 0;
    drivetrain.setGoalAngle(angle);
    drivetrain.setBrakeMode(IdleMode.kBrake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = drivetrain.getAngularError();
    drivetrain.setGoalAngle(error);
    SmartDashboard.putNumber("angular error", drivetrain.getAngularError());
    SmartDashboard.putNumber("counter", counter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(drivetrain.getAngularError()) < 2){
      counter++;
    }else counter = 0;
    return counter > 20;
  }
}
