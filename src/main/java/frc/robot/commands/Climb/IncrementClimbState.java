// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Climb;

/**increment climb state */
public class IncrementClimbState extends InstantCommand {
  Climb climb;
  public IncrementClimbState(Climb climb) {
    this.climb = climb;
    addRequirements(climb);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    MoveToClimbState.climb_state++;  }
}
