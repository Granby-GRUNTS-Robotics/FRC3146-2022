// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;

public class LimeTurnAndForward extends LimeTurn {
  /** Creates a new LimeTurnAndForward. */
  Joystick joy;
  public LimeTurnAndForward(LimeLight limeLight, Drivetrain drivetrain, Joystick joy) {
    super(drivetrain, limeLight);
    this.joy = joy;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double throttle = - RobotMap.Buttons.getWithDeadZone(-joy.getY(), 0.08);
    double throttleMult = SmartDashboard.getNumber("throttle", ControlConstants.kTHROTTLE_MULTIPLIER);
    double left = throttle*throttleMult ;
    double right = throttle*throttleMult ;
    if(left !=0 && right !=0){
    drivetrain.setSpeeds(left, right);
    }else super.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
