// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.LimeTurn;
import frc.robot.commands.Shooter.ShootLime;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LimeTurnAndShoot extends SequentialCommandGroup {
  /** Creates a new LimeTurnAndShoot. */
  public LimeTurnAndShoot(Drivetrain drivetrain, LimeLight limeLight, Shooter shooter, Magazine magazine) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new LimeTurnOn(limeLight), new LimeTurn(drivetrain, limeLight), new ShootLime(magazine, shooter, limeLight));
  }
}
