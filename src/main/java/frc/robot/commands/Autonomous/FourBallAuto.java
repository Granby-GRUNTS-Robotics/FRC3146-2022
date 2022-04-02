// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOff;
import frc.robot.commands.Intake.MoveIntakeDown;
import frc.robot.commands.Intake.MoveIntakeUp;
import frc.robot.commands.Shooter.ShootWithSetSpeed;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallAuto extends SequentialCommandGroup {
  /** Creates a new FourBallAuto. */
  public FourBallAuto(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveIntakeDown(intake), new IntakeIn(intake), new DriveToLocation(drivetrain, 36), new DriveToLocation(drivetrain, -12), new DriveToAngle(drivetrain, 20), new ShootWithSetSpeed(magazine, shooter, 4200, intake), new IntakeOff(intake), new MoveIntakeUp(intake), new DriveToAngle(drivetrain, -24), new DriveToLocation(drivetrain, 212));
  }
}
