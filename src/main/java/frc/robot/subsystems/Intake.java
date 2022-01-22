// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.PneumaticConstants.INTAKE_ENUM;

public class Intake extends SubsystemBase {
  
  private static final TalonSRX M_INTAKE_TALON = RobotMap.INTAKE_TALON;
  private static final Solenoid M_INTAKE_SOLENOID = RobotMap.INTAKE_SOLENOID;
  
  /** Creates a new Intake and sets inversion */
  public Intake() {
    M_INTAKE_TALON.setInverted(false);
  }

  //moves intake using enum UP or DOWN
  public void setIntakeSolenoid(INTAKE_ENUM pos){
    switch (pos) {
      case UP:
        M_INTAKE_SOLENOID.set(false);
        break;
      case DOWN:
        M_INTAKE_SOLENOID.set(true);
        break;
      default:
        break;
    }
  }

  //sets the % output of the intake motor.
  public void setIntakePercent(double percent) {
    M_INTAKE_TALON.set(ControlMode.PercentOutput, percent);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
