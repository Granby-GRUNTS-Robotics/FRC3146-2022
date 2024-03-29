// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.subsystems.Climb;
/**moves climb with joystick */
public class ManualClimbMotor extends CommandBase {
  /** Creates a new ManualClimbMotor. */
  private Climb climb;
  private DoubleSupplier Y_AXIS;
  public ManualClimbMotor(Climb climb, DoubleSupplier Y_AXIS) {
    this.Y_AXIS = Y_AXIS;
    // Use addRequirements() here to declare subsystem dependencies.
    this.climb = climb;
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Y_AXIS.getAsDouble() > 0){
      climb.setRatchet(RATCHET_ENUM.FREE);
    }else {climb.setRatchet(RATCHET_ENUM.RATCHETING);}
    
    try {
      climb.setClimbPercent(Y_AXIS.getAsDouble());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    try {
      climb.setClimbPercent(0);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
