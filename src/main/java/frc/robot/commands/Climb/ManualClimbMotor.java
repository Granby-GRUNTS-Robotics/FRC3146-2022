// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ManualClimbMotor extends CommandBase {
  /** Creates a new ManualClimbMotor. */
  private NetworkTableEntry voltageEntry;
  Climb climb;
  public ManualClimbMotor(Climb climb) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climb = climb;
    addRequirements(climb);

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable climbTable = inst.getTable("climb");
    voltageEntry = climbTable.getEntry("climb user set voltage");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    voltageEntry.setNumber(0);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climb.setVoltage(voltageEntry.getDouble(0));
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
