// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class MagIntake extends CommandBase {

  Magazine magazine;
  private double mag_intake_speed;

  /** Creates a new MagManualBase. */
  public MagIntake(Magazine magazine) {
    
    this.magazine = magazine;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("intake button magazine speed", 0);
    SmartDashboard.setDefaultBoolean("reverse limit switch", false);
    boolean reverse = SmartDashboard.getBoolean("reverse limit switch", false);
    mag_intake_speed = SmartDashboard.getNumber("intake button magazine speed", 0);
    if (magazine.getLimitSwitch() == reverse) 
      magazine.setMagazinePercent(mag_intake_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(magazine.getLimitSwitch()){
      magazine.setMagazinePercent(0);
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
