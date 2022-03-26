// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MagazineAndIntakeFeed;
import frc.robot.commands.Intake.MoveIntakePartialUp;
import frc.robot.commands.Magazine.BackSpace;
import frc.robot.commands.Magazine.Feed;
import frc.robot.commands.Magazine.FeedAuto;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/**base for shooting. Backspaces, revs up(the thing that will change), feeds, and then brakes the shooter (except it doesn't really do that, because the shooter motor is in coast mode) */
public class ShootBase extends SequentialCommandGroup {
  /** Creates a new ShootBase. */
  public ShootBase(Magazine magazine, Shooter shooter, RevUpBase speedBase) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new BackSpace(magazine), new MoveIntakePartialUp(intake),speedBase, new MagazineAndIntakeFeed(intake, magazine), new ShooterBrake(shooter));
  }
  public ShootBase(Magazine magazine, Shooter shooter, RevUpBase speedBase, boolean auto) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new BackSpace(magazine), new MoveIntakePartialUp(intake), speedBase, auto ? new FeedAuto(magazine): new MagazineAndIntakeFeed(intake, magazine), new ShooterBrake(shooter));
  }
}
