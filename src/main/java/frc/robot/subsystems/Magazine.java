// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Magazine extends SubsystemBase {

  private static final VictorSPX MAGAZINE_VICTOR = RobotMap.MAGAZINE_VICTOR;
  private static final Encoder MAGAZINE_ENCODER = RobotMap.MAG_ENCODER;
  private static final DigitalInput MAGAZINE_LIMIT_SWITCH = RobotMap.MAGAZINE_LIMIT_SWITCH;

  /** Creates a new Magazine. */
  public Magazine() {
    MAGAZINE_ENCODER.setDistancePerPulse(1/4096);
    MAGAZINE_ENCODER.reset();
  }

  //sets the percent output of the magazine victor
  public void setMagazinePercent(double percent){
    MAGAZINE_VICTOR.set(ControlMode.PercentOutput, percent);
  }

  //resets magazine encoder to 0
  public void resetMagazineEncoder(){
    MAGAZINE_ENCODER.reset();
  }

  //gets magazine position
  public double getMagPos(){
    return MAGAZINE_ENCODER.getDistance();
  }

  //gets digital input
  public boolean getLimitSwitch(){
    return MAGAZINE_LIMIT_SWITCH.get();
  }

  //gets ball amount

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
}
