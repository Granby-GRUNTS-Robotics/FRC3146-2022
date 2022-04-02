// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.LimeTurnAndShoot;
import frc.robot.commands.LimeTurnOn;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOff;
import frc.robot.commands.Intake.MoveIntakePartialDown;
import frc.robot.commands.Intake.MoveIntakeUp;
import frc.robot.commands.Magazine.BackSpace;
import frc.robot.commands.Shooter.RevUpSet;
import frc.robot.commands.Shooter.ShootWithSetSpeed;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallAuto extends SequentialCommandGroup {
  /** Creates a new FourBallAuto. */
  public FourBallAuto(Magazine magazine, Intake intake, Drivetrain drivetrain, Shooter shooter, LimeLight limeLight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new LimeTurnOn(limeLight),new MoveIntakePartialDown(intake), new IntakeIn(intake), new DriveToLocation(drivetrain, 36), new DriveToLocation(drivetrain, -12), new BackSpace(magazine, intake), new RevUpSet(shooter, 3100, true), new DriveToAngle(drivetrain, 19), new ShootWithSetSpeed(magazine, shooter, 3170, intake),  new ParallelCommandGroup( new MoveIntakePartialDown(intake), new DriveToAngle(drivetrain, -23)),new IntakeIn(intake), new DriveToLocation(drivetrain, 165), new WaitCommand(1.0), new DriveToLocation(drivetrain, -140),  new BackSpace(magazine, intake), new RevUpSet(shooter, 3000, true), new LimeTurnAndShoot(drivetrain, limeLight, shooter, magazine, intake));
  }
}
