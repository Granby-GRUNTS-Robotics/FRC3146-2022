// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Magazine;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
/**moves the magazine backwards to prevent interference with the shooter */
public class BackSpaceAuto extends MagMoveBase {
  /** Creates a new BackSpace. */
  Intake intake;
  public BackSpaceAuto(Magazine magazine) {
    super(magazine);
   
    speed = 0.6;
    movement_time = SetpointConstants.MAGAZINE_BACKSPACE_DISTANCE;

    // Use addRequirements() here to declare subsystem dependencies.
  }
  @Override
  public void initialize() {
    SmartDashboard.putString(Constants.SHOOT_STRING, "Backspacing");

    super.initialize();
  }

}
