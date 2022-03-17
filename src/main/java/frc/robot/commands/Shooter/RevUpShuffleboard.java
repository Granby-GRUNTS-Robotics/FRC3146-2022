// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Shooter;
/**for testing only. Revs up based on values on the shuffleboard */
public class RevUpShuffleboard extends RevUpBase {
  /** Creates a new ShooterSpeedBase. */

  
  public RevUpShuffleboard(Shooter shooter) {
    super(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  public boolean part_of_chain = false;
  public RevUpShuffleboard(Shooter shooter, boolean part_of_chain) {
    super(shooter);
    this.part_of_chain = part_of_chain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("Target Shooter Speed", 0);
    speed = SmartDashboard.getNumber("Target Shooter Speed", 0);
    super.initialize();
  }
}