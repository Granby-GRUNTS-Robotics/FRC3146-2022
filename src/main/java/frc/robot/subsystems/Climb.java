// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.SetpointConstants;
import frc.robot.Constants.PneumaticConstants.ARM_ENUM;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.Constants.ControlConstants.HOOK_ENUM;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;

public class Climb extends SubsystemBase {
  
  private NetworkTableEntry currentEntry;
  private NetworkTableEntry hookedEntry;
  private NetworkTableEntry positionEntry;

  private static final Solenoid ARM_VERT_SOLENOID = RobotMap.ARM_VERT_SOLENOID;
  private static final Solenoid ARM_HORI_SOLENOID = RobotMap.ARM_HORI_SOLENOID;
  private ARM_ENUM arm_state;
  private static final Solenoid WINCH_SOLENOID = RobotMap.RATCHET_SOLENOID;
  private RATCHET_ENUM ratchet_state;
  private static final Solenoid CLAW_SOLENOID = RobotMap.CLAW_SOLENOID;
  private CLAW_ENUM claw_state;
  private HOOK_ENUM hook_state;

  private static final TalonSRX CLIMB_TALON = RobotMap.CLIMB_DRIVE_TALON;
  private static final VictorSPX FOLLOW_VICTOR = RobotMap.CLIMB_FOLLOW_VICTOR;
  private static final AnalogInput POTENTIOMETER = RobotMap.CLIMB_POTENTIOMETER;

  private int climb_state = 0;
  /** Creates a new Climb. */
  public Climb() {
    FOLLOW_VICTOR.follow(CLIMB_TALON);
    FOLLOW_VICTOR.setInverted(false);
    CLIMB_TALON.setInverted(false);
    CLIMB_TALON.setSelectedSensorPosition(distanceToEncoderUnits(SetpointConstants.HOOK_RETRACTED));
    CLIMB_TALON.setSensorPhase(true); // clockwise looking at the encoder from the outside moves the hook out. Reversed Talon to make positive outwards. Reversed Sensor to match Talon
    
    //limits current draw to 35 amps
    CLIMB_TALON.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 0, 0.5));
    
    CLIMB_TALON.config_kP(0, ControlConstants.HOOK_UP_kP);
    CLIMB_TALON.config_kD(0, ControlConstants.HOOK_UP_kD);
    CLIMB_TALON.config_kF(0, ControlConstants.HOOK_UP_kF);

    addChild("claw", CLAW_SOLENOID);
    addChild("arm horizontal", ARM_HORI_SOLENOID);
    addChild("arm vertical", ARM_VERT_SOLENOID);
    addChild("ratchet", WINCH_SOLENOID);

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable climbTable = inst.getTable("climb");
    currentEntry = climbTable.getEntry("current");
    positionEntry = climbTable.getEntry("position");
    hookedEntry = climbTable.getEntry("is_hooked");
    SmartDashboard.setDefaultNumber("climb_state", climb_state);
  }

  /** sets arm position
   * @param pos ARM_ENUM either VERTICAL or HORIZONTAL
   * */
  public void setArm(ARM_ENUM pos){
    arm_state = pos;
    switch (pos) {
      case VERTICAL:
        ARM_VERT_SOLENOID.set(false);
        ARM_HORI_SOLENOID.set(false);
        break;
      case HORIZONTAL:
        ARM_VERT_SOLENOID.set(true);
        ARM_HORI_SOLENOID.set(true);
        break;
      case FLOAT:
        ARM_VERT_SOLENOID.set(true);
        ARM_HORI_SOLENOID.set(false);
        break;
      case  BOTH:
        ARM_VERT_SOLENOID.set(false);
        ARM_HORI_SOLENOID.set(true);
        break;
      default:
        break;
    }
  }

  /**
   * 
   * @return the ARM_ENUM state of the two climb pistons
   */
  public ARM_ENUM getArmState() {
      return arm_state;
  }

  /** sets claw position 
   * @param pos CLAW_ENUM either OPEN or CLOSED
   * */
  public void setClaw(CLAW_ENUM pos){
    claw_state = pos;
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

  /**
   * 
   * @return the state of the claw solenoid, either CLAW_ENUM.OPEN or CLOSED
   */
  public CLAW_ENUM getClawState() {
    return claw_state;
  }

  /**sets winch position 
   * @param RATCHET_ENUM either RATCHETING or FREE
   * */
  public void setRatchet(RATCHET_ENUM pos){
    ratchet_state = pos;
    switch (pos) {
      case RATCHETING:
        WINCH_SOLENOID.set(false);
        break;
      case FREE:
        WINCH_SOLENOID.set(true);
        break;
      default:
        break;
    }
  }

  /**
   * 
   * @return the state of the ratchet solenoid, either RATCHET_ENUM.FREE or RATCHETING
   */
  public RATCHET_ENUM getRatchetState() {
      return ratchet_state;
  }
  
  /**sets HOOK position
   * @param pos HOOK_ENUM either EXTENDED, RETRACTED, or CAPTURING
  */
  public void setHook(HOOK_ENUM pos){
    hook_state = pos;
    switch (pos) {
      case EXTENDED:
        try {
          setHook(SetpointConstants.HOOK_EXTENDED);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e);
        }
        break;
      case RETRACTED:
        try {
          setHook(SetpointConstants.HOOK_RETRACTED);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e);
        }
        break;  
      case RESTING:
        try {
          setHook(SetpointConstants.HOOK_RESTING);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e);
        }
        break;  
      case MIDDLE:
        try {
          setHook(SetpointConstants.HOOK_MIDDLE);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e);
        }
        break;
      default:
        break;
      
    }
  }

  /**
   * 
   * @return goal position of the hook mechanism
   */
  public HOOK_ENUM getHookState() {
      return hook_state;
  }

  /**
   * sets the percent output of the leading Talon SRX
   * @param percent the output percent, from -1.0 to 1.0
   */
  public void setClimbPercent(double percent){
    CLIMB_TALON.set(ControlMode.PercentOutput, percent);
  }

  private double getPotentiometer(){
    return POTENTIOMETER.getValue();
  }
  
  /**
   * exception if distance is not within reasonable bounds or winch is not in correct state
   * @param distance the extended distance in inches
   * @throws Exception if distance is not within range or ratcheting does not match up with movement direction
   */
  public void setHook(double distance) throws Exception{
    if (distance < -10 || distance > 40){
      throw new Exception("invalid distance");
    } else
    if ((getPosition() - distance > 0) ^ (getRatchetState() == RATCHET_ENUM.RATCHETING)){
      throw new Exception("Ratcheting should be enabled only for pulling upwards!");
    }else{ 
      if (isHooked() && !isAtPosition()) {
        setClimbPercent(SetpointConstants.CLIMB_PERCENT);
      } else {
        CLIMB_TALON.set(ControlMode.Position, distanceToEncoderUnits(distance));
      }
    }
  }

  /**
   * test to see if hook is pulling on the climb bar
   * @return true if current of lead motor exceeds climb threshold
   */
  public boolean isHooked(){
    return (getStatorCurrent() > MotorConstants.CLIMB_AMPS); 
  }

  public void incrementClimbState(){
    climb_state+=1;
  }

  public void decrementClimbState(){
    climb_state-=1;
  }

  /**
   * @return the CLIMB_ENUM state of the robot 
   * */
  public int getClimbState() {
      return climb_state;
  }

  /**
   * 
   * @return the current being drawn by the Lead Talon in amps
   */
  public double getStatorCurrent(){
    return CLIMB_TALON.getStatorCurrent();
  }

  /**
   * 
   * @return the current position of the bar, in inches above fully pulled down
   */
  private double getPosition(){
    return (double)CLIMB_TALON.getSelectedSensorPosition() * ControlConstants.CLIMB_ENCODER_TO_DISTANCE;
  }
  /**
   * 
   * @param distance, the distance in inches above fully pulled down
   * @return the position in encoder units
   */
  public double distanceToEncoderUnits(double distance){
    return distance/ControlConstants.CLIMB_ENCODER_TO_DISTANCE;
  }

  public boolean isAtPosition(){
    return Math.abs(CLIMB_TALON.getClosedLoopError()) < SetpointConstants.HOOK_PRECISON;
  }

  @Override
  public void periodic() {
    currentEntry.setNumber(getStatorCurrent());
    positionEntry.setNumber(getPosition());
    hookedEntry.setBoolean(isHooked());
    SmartDashboard.putNumber("climb_state", climb_state);
    SmartDashboard.putBoolean("isAtPosition", isAtPosition());
    SmartDashboard.putNumber("potentiometer", getPotentiometer());
    // This method will be called once per scheduler run
  }

  /**FOR TESTING USE ONLY */
  public void setPDF(double p, double d, double f){
    CLIMB_TALON.config_kP(0, p);
    CLIMB_TALON.config_kD(0, d);
    CLIMB_TALON.config_kF(0, f);
  }
}
