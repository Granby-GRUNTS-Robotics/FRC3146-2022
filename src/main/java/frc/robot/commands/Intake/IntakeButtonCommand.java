// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.PneumaticConstants.INTAKE_ENUM;
import frc.robot.subsystems.Intake;
/**turns intake on. When finished, turns intake off.
 * Also moves pneumatics down hard and then soft
 */
public class IntakeButtonCommand extends CommandBase {
  /** Creates a new IntakeButtonCommand. */
  private Intake intake;
  public IntakeButtonCommand(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.setIntakePercent(Constants.SetpointConstants.INTAKE_SPEED);
    intake.setIntakeSolenoids(INTAKE_ENUM.FULL_DOWN);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakePercent(0);
    intake.setIntakeSolenoids(INTAKE_ENUM.FULL_UP);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
