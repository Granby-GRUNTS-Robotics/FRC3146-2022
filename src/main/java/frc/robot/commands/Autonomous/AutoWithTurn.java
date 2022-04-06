// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOff;
import frc.robot.commands.Intake.MoveIntakePartialDown;
import frc.robot.commands.Intake.MoveIntakeUp;
import frc.robot.commands.Shooter.ShootWithSetSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/**
 * very basic autonomous routine starting from initiation zone, moving forwards to collect ball and shooting towards target
 */
public class AutoWithTurn extends SequentialCommandGroup {
  /** Creates a new AutoFromLine. 
   * very basic autonomous routine starting from initiation zone, moving forwards to collect ball and shooting towards target
  */
  public AutoWithTurn(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveIntakePartialDown(intake), new IntakeIn(intake), new DriveToLocation(drivetrain, 36), new DriveToLocation(drivetrain, -12), new DriveToAngle(drivetrain, 15), new ShootWithSetSpeed(magazine, shooter, 3300, intake), new IntakeOff(intake), new MoveIntakeUp(intake), new DriveToLocation(drivetrain, 25));
  }
}
