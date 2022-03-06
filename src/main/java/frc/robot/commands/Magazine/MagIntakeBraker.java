// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

/**runs when MagIntakeButton ends. will either run for a half second more, or until the limit switch is triggered, whichever comes first
 * added to ensure balls make it safely into the magazine
 */
public class MagIntakeBraker extends CommandBase {
  /** Creates a new MagIntakeBraker. */
  Magazine magazine;
  Timer timer;
  public MagIntakeBraker(Magazine magazine) {
    this.magazine = magazine;
    addRequirements(magazine);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get() > 0.5 || magazine.getLimitSwitch()){
      magazine.brake();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > 0.5;
  }
}
