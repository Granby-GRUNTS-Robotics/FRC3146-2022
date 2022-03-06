// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimeLight;

/**turns of limelight LEDs */
public class LimeTurnOff extends InstantCommand {
  LimeLight limeLight;
  public LimeTurnOff(LimeLight limeLight) {
    this.limeLight = limeLight;
    addRequirements(limeLight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limeLight.setLEDS(false);
  }
}
