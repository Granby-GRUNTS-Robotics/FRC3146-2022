// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Shooter.ShootWithSetSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ProtectedShooting extends SequentialCommandGroup {


  /** Creates a new ProtectedShooting. */
  public ProtectedShooting(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter) {
    
    //Drive to location allows the robot to move a given distance
    addCommands(new DriveToLocation(drivetrain, 58.75), new DriveToAngle(drivetrain, Constants.SetpointConstants.PROTECTED_SHOOTING_ANGLE), new ShootWithSetSpeed(magazine, shooter, Constants.SetpointConstants.PROTECTED_SHOOTING_SPEED, intake));
  }
}
