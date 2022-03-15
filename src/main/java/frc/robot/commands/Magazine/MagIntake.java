// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Magazine;

/**moves magazine in, command ends when switch is triggered */
public class MagIntake extends CommandBase {

  Magazine magazine;

  /** Creates a new MagManualBase. */
  public MagIntake(Magazine magazine) {
    
    this.magazine = magazine;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    if (!magazine.getLimitSwitch()) 
      magazine.setMagazinePercent(SetpointConstants.MAGAZINE_INTAKE_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(magazine.getLimitSwitch()){
      magazine.brake();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    magazine.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return magazine.getLimitSwitch();
  }
}
