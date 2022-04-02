// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.Intake.IntakeFeed;
import frc.robot.commands.Magazine.Feed;
import frc.robot.commands.Magazine.MagMoveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MagazineAndIntakeFeed extends ParallelRaceGroup {
  /** Creates a new MagazineAndIntakeFeed. */
  public MagazineAndIntakeFeed(Intake intake, MagMoveBase base) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(base, new IntakeFeed(intake));
  }
}
