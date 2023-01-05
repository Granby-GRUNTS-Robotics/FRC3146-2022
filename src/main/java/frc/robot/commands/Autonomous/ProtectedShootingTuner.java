// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Constants.SetpointConstants;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToAngleShuffleboard;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Drivetrain.DriveToShuffleboard;
import frc.robot.commands.Shooter.RevUpSet;
import frc.robot.commands.Shooter.RevUpShuffleboard;
import frc.robot.commands.Shooter.ShootShuffleBoard;
import frc.robot.commands.Shooter.ShootWithSetSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ProtectedShootingTuner extends SequentialCommandGroup {

  /** Creates a new ProtectedShootingTuner. */
  public ProtectedShootingTuner(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter) {
    addCommands(new RevUpShuffleboard(shooter,  true), new DriveToLocation(drivetrain, SetpointConstants.PROTECTED_SHOOTING_DISTANCE), new DriveToAngle(drivetrain, SetpointConstants.PROTECTED_SHOOTING_ANGLE), new ShootShuffleBoard(magazine, shooter, intake));
    //Drive to location allows the robot to move a given distance
    
  }
}
