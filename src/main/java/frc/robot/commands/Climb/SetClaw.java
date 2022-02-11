// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.subsystems.Climb;


public class SetClaw extends CommandBase {
  /** Creates a new SetClaw. */
  private Climb climb;
  private CLAW_ENUM state;
  private Timer timer = new Timer();
  private double timegoal;

  public SetClaw(Climb climb, CLAW_ENUM state) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climb = climb;
    addRequirements(climb);
    this.state = state;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    timegoal = climb.getClawState() == state ? 0 : 0.25;//TODO
    climb.setClaw(state);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > timegoal;
  }
}