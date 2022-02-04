// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants.CLIMB_STATE;
import frc.robot.Constants.PneumaticConstants.ARM_ENUM;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.Constants.PneumaticConstants.HOOK_ENUM;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;

public class Climb extends SubsystemBase {
  private static final Solenoid ARM_SOLENOID = RobotMap.ARM_SOLENOID;
  private static final Solenoid WINCH_SOLENOID = RobotMap.RATCHET_SOLENOID;
  private static final Solenoid CLAW_SOLENOID = RobotMap.CLAW_SOLENOID;

  private static final VictorSPX CLIMB_VICTOR = RobotMap.CLIMB_DRIVE_VICTOR;
  private static final VictorSPX FOLLOW_VICTOR = RobotMap.CLIMB_FOLLOW_VICTOR;

  private static final Encoder CLIMB_ENCODER = RobotMap.CLIMB_ENCODER;

  private CLIMB_STATE state;
  /** Creates a new Climb. */
  public Climb() {
    FOLLOW_VICTOR.follow(CLIMB_VICTOR);
  }

  //sets arm position using VERTICAL HORIZONTAL ENUM
  public void setArm(ARM_ENUM pos){
    switch (pos) {
      case VERTICAL:
        ARM_SOLENOID.set(false);
        break;
      case HORIZONTAL:
        ARM_SOLENOID.set(true);
        break;
      default:
        break;
    }
  }

  //sets claw position with OPEN CLOSED enum
  public void setClaw(CLAW_ENUM pos){
    switch (pos) {
      case CLOSED:
        CLAW_SOLENOID.set(false);
        break;
      case OPEN:
        CLAW_SOLENOID.set(true);
        break;
      default:
        break;
    }
  }

  //sets winch position with WINCHING FREE enum
  public void setRatchet(RATCHET_ENUM pos){
    switch (pos) {
      case FREE:
        WINCH_SOLENOID.set(false);
        break;
      case RATCHETING:
        WINCH_SOLENOID.set(true);
        break;
      default:
        break;
    }
  }
  
  //sets HOOK position with HOOK enum.
  public void setHook(HOOK_ENUM pos){
    switch (pos) {
      case EXTENDED:
        
        break;
      case RETRACTED:
        
        break;  
      case CAPTURING:

        break;
      default:
        break;
      
    }
  }
  //sets climb victor percent
  public void setClimbPercent(double percent){
    CLIMB_VICTOR.set(ControlMode.PercentOutput, percent);
  }
  
  //sets climb state to either climbing or positioning
  public void setClimbState(CLIMB_STATE state){
    this.state = state;
  }

  //@return the state of the robot
  public CLIMB_STATE getState() {
      return state;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
