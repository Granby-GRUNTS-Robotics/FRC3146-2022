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
  
  private static final TalonSRX INTAKE_TALON = RobotMap.INTAKE_TALON;
  private static final Solenoid INTAKE_UP_SOLENOID = RobotMap.INTAKE_UP_SOLENOID;
  private static final Solenoid INTAKE_DOWN_SOLENOID = RobotMap.INTAKE_DOWN_SOLENOID;
  private static INTAKE_ENUM intake_pos = INTAKE_ENUM.UP;

  
  /** Creates a new Intake and sets inversion */
  public Intake() {
    INTAKE_TALON.setInverted(false);
  }

  
  /**actuates intake piston
   * @param pos INTAKE_ENUM using either UP or DOWN
   **/ 
  public void setIntakeSolenoids(INTAKE_ENUM pos){
    try {
      intake_pos = pos;
    switch (pos) {
      case UP:
        INTAKE_UP_SOLENOID.set(false);
        INTAKE_DOWN_SOLENOID.set(false);
        break;
      case DOWN:
        INTAKE_UP_SOLENOID.set(true);
        INTAKE_DOWN_SOLENOID.set(true);
        break;
      case SOFT:
        INTAKE_DOWN_SOLENOID.set(true);
        INTAKE_UP_SOLENOID.set(false);
        break;
      case FLOAT:
        INTAKE_DOWN_SOLENOID.set(false);
        INTAKE_UP_SOLENOID.set(true);
        break;  
      default:
        break;
    }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e);
    }
    
  }

  public INTAKE_ENUM getIntakePos() {
      return intake_pos;
  }

  /**sets the % output of the intake motor.
   * @param percent the % output from -1.0 to 1.0
  */
  public void setIntakePercent(double percent) {
    INTAKE_TALON.set(ControlMode.PercentOutput, percent);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
