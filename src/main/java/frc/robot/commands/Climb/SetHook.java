// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControlConstants.HOOK_ENUM;
import frc.robot.subsystems.Climb;

public class SetHook extends CommandBase {
  /** Creates a new SetHook. */
  private Climb climb;
  private HOOK_ENUM state;
  private boolean ends;
  public SetHook(Climb climb,HOOK_ENUM state) {
    this.climb = climb;
    this.state = state;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public SetHook(Climb climb,HOOK_ENUM state, boolean ends) {
    this.climb = climb;
    this.state = state;
    this.ends = ends;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climb.setHook(state);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(ends == true)
    climb.setClimbPercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return climb.isAtPosition();
  }
}
