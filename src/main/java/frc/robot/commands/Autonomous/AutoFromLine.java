// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOff;
import frc.robot.commands.Intake.MoveIntakeDown;
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
public class AutoFromLine extends SequentialCommandGroup {
  /** Creates a new AutoFromLine. 
   * very basic autonomous routine starting from initiation zone, moving forwards to collect ball and shooting towards target
  */
  public AutoFromLine(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveIntakePartialDown(intake), new IntakeIn(intake), new DriveToLocation(drivetrain, 36), new DriveToLocation(drivetrain, -12), new ShootWithSetSpeed(magazine, shooter, 3400, intake), new IntakeOff(intake), new MoveIntakeUp(intake), new DriveToLocation(drivetrain, 25));
  }

  /** Creates a new AutoFromLine. 
   *  very basic autonomous routine starting from initiation zone, moving forwards to collect ball and shooting towards target, with set distance and flywheel speed values
  */
  public AutoFromLine(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter, double distance, double flywheel_speed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveIntakePartialDown(intake), new IntakeIn(intake), new DriveToLocation(drivetrain, distance), new ShootWithSetSpeed(magazine, shooter, flywheel_speed, intake));
  }
}
