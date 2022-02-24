// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SetpointConstants;
import frc.robot.Constants.PneumaticConstants.INTAKE_ENUM;
import frc.robot.subsystems.Intake;

public class IntakeButtonCommand extends CommandBase {
  /** Creates a new IntakeButtonCommand. */
  private Intake intake;
  private Timer timer = new Timer();
  private double timegoal = 0;
  private INTAKE_ENUM start_state;
  public IntakeButtonCommand(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.setIntakePercent(SetpointConstants.INTAKE_SPEED);
    timer.start();
    start_state = intake.getIntakePos();
    if (start_state == INTAKE_ENUM.UP){
      intake.setIntakeSolenoids(INTAKE_ENUM.DOWN);
      timegoal = 1;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() > timegoal && start_state == INTAKE_ENUM.UP){
      if (intake.getIntakePos() != INTAKE_ENUM.SOFT)
      intake.setIntakeSolenoids(INTAKE_ENUM.SOFT);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakePercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
