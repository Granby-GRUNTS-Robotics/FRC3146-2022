// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.PneumaticConstants.INTAKE_ENUM;

public class Intake extends SubsystemBase {
  
  private static final VictorSPX INTAKE_VICTOR = RobotMap.INTAKE_VICTOR;
  private static final Solenoid INTAKE_MAIN_SOLENOID = RobotMap.INTAKE_MAIN_SOLENOID;
  private static final Solenoid INTAKE_SECONDARY_SOLENOID = RobotMap.INTAKE_SECONDARY_SOLENOID;
  private static INTAKE_ENUM intake_pos = INTAKE_ENUM.FULL_UP;

  
  /** Creates a new Intake and sets inversion */
  public Intake() {
    //positive percent brings motors toward the shooter
    INTAKE_VICTOR.setInverted(false);
  }

  
  /**actuates intake piston
   * @param pos INTAKE_ENUM using either UP or DOWN
   **/ 
  public void setIntakeSolenoids(INTAKE_ENUM pos){
    intake_pos = pos;
    switch (pos) {
      case FULL_UP:
        INTAKE_MAIN_SOLENOID.set(false);
        INTAKE_SECONDARY_SOLENOID.set(false);
        break;
      case FULL_DOWN:
        INTAKE_MAIN_SOLENOID.set(true);
        INTAKE_SECONDARY_SOLENOID.set(true);
        break;
      case PARTIAL_DOWN:
        INTAKE_SECONDARY_SOLENOID.set(true);
        INTAKE_MAIN_SOLENOID.set(false);
        break;
      case PARTIAL_UP:
        INTAKE_SECONDARY_SOLENOID.set(false);
        INTAKE_MAIN_SOLENOID.set(true);
        break;  
      default:
        break;
    }
  }

  /**
   * 
   * @return INTAKE_ENUM position
   */
  public INTAKE_ENUM getIntakePos() {
      return intake_pos;
  }

  /**sets the % output of the intake motor.
   * @param percent the % output from -1.0 to 1.0
  */
  public void setIntakePercent(double percent) {
    INTAKE_VICTOR.set(ControlMode.PercentOutput, percent);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
