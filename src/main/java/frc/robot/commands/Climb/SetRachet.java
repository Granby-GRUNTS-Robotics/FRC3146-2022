// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.subsystems.Climb;     
public class SetRachet extends CommandBase {
private Climb climb;
private RATCHET_ENUM state;
private Timer timer = new Timer();
private double timegoal;

/** Creates a new SetRachet. */
  public SetRachet(Climb climb, RATCHET_ENUM state) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climb = climb;
    addRequirements(climb);
    this.state = state;
    }
  
  // Called when the command is initially scheduled.

  @Override
  public void initialize() {
    timer.start();
    timegoal = climb.getRatchetState() == state ? 0 : 0.25;//TODO
    climb.setRatchet(state);
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