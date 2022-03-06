// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.ControlConstants.HOOK_ENUM;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.PneumaticConstants.ARM_ENUM;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.Constants.SetpointConstants;
import frc.robot.RobotMap;

public class Climb extends SubsystemBase {

  private NetworkTableEntry currentEntry;
  private NetworkTableEntry hookedEntry;
  private NetworkTableEntry positionEntry;

  private static final Solenoid ARM_VERT_SOLENOID = RobotMap.ARM_VERT_SOLENOID;
  private static final Solenoid ARM_HORI_SOLENOID = RobotMap.ARM_HORI_SOLENOID;
  private ARM_ENUM arm_state = ARM_ENUM.VERTICAL;
  private static final Solenoid WINCH_SOLENOID = RobotMap.RATCHET_SOLENOID;
  private RATCHET_ENUM ratchet_state = RATCHET_ENUM.RATCHETING;
  private static final Solenoid CLAW_SOLENOID = RobotMap.CLAW_SOLENOID;
  private CLAW_ENUM claw_state = CLAW_ENUM.CLOSED;
  private HOOK_ENUM hook_state = HOOK_ENUM.RETRACTED;

  private double goal_height = 0;

  private static final TalonSRX CLIMB_TALON = RobotMap.CLIMB_DRIVE_TALON;
  private static final VictorSPX FOLLOW_VICTOR = RobotMap.CLIMB_FOLLOW_VICTOR;
  private static final AnalogInput POTENTIOMETER = RobotMap.CLIMB_POTENTIOMETER;

  /** Creates a new Climb. */
  public Climb() {
    FOLLOW_VICTOR.follow(CLIMB_TALON);
    FOLLOW_VICTOR.setInverted(false);
    CLIMB_TALON.setInverted(false);
    CLIMB_TALON.setSelectedSensorPosition(0);
    CLIMB_TALON.setSensorPhase(true); // clockwise looking at the encoder from the outside moves the hook out. Reversed Talon to make positive outwards. Reversed Sensor to match Talon
    
    //limits current draw to 35 amps
    CLIMB_TALON.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 0, 0.5));
    
    CLIMB_TALON.config_kP(0, ControlConstants.HOOK_UP_kP);
    CLIMB_TALON.config_kD(0, ControlConstants.HOOK_UP_kD);
    CLIMB_TALON.config_kF(0, ControlConstants.HOOK_UP_kF);

    CLIMB_TALON.config_kP(1, ControlConstants.HOOK_DOWN_kP);
    CLIMB_TALON.config_kD(1, ControlConstants.HOOK_DOWN_kD);
    CLIMB_TALON.config_kF(1, ControlConstants.HOOK_DOWN_kF);

    CLIMB_TALON.selectProfileSlot(0, 0);
    setSmartMotionValues(ControlConstants.CLIMB_MAX_ACC, ControlConstants.CLIMB_CRUISE);

    addChild("claw", CLAW_SOLENOID);
    addChild("arm horizontal", ARM_HORI_SOLENOID);
    addChild("arm vertical", ARM_VERT_SOLENOID);
    addChild("ratchet", WINCH_SOLENOID);

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable climbTable = inst.getTable("climb");
    currentEntry = climbTable.getEntry("current");
    positionEntry = climbTable.getEntry("position");
    hookedEntry = climbTable.getEntry("is_hooked");
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
      case FIRST:
        try {
          setHook(SetpointConstants.HOOK_FIRST_BAR);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e);
        }
        break;
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
      case CAPTURING:
        try {
          setHook(SetpointConstants.HOOK_CAPTURING);
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
  public void setClimbPercent(double percent) throws Exception{
    if ((percent > 0) && (getRatchetState() == RATCHET_ENUM.RATCHETING)){
      throw new Exception("Ratcheting should be enabled only for pulling upwards!");
    }else{
    CLIMB_TALON.set(TalonSRXControlMode.PercentOutput, percent);}
  }

  /**
   * 
   * @param velocity the speed of the arm in inches/sec
   */
  public void setClimbVelocity(double velocity) throws Exception{
    velocity = distanceToEncoderUnits(velocity) / 10.0;
    CLIMB_TALON.selectProfileSlot(0, 0);
    if ((velocity > 0) && (getRatchetState() == RATCHET_ENUM.RATCHETING)){
      throw new Exception("Ratcheting should be enabled only for pulling upwards!");
    }else{ CLIMB_TALON.set(TalonSRXControlMode.Velocity, velocity); }
  }
  
  /**
   *
   * 
   * @return the velocity of the climb motor in inches/second of the hook
   */
  public double getVelocity(){
    return encoderUnitsToDistanct(CLIMB_TALON.getSelectedSensorVelocity()) * 10;
  }

  /**
   * 
   * @return the value of the analog potentiometer
   */
  private double getPotentiometer(){
    return POTENTIOMETER.getValue();
  }

  /**
   * 
   * @return the closed loop error of the climb TalonSRX, in encoder units
   */
  private double getError(){
    return CLIMB_TALON.getClosedLoopError();
  }

  /**
   * Switches the PID slot of the lead controller based on whether or not we are pulling ourselves up or not. Meant to be called continuously
   * @throws Exception if trying to work against the ratchet
   */
  public void switchPID() throws Exception{
    if(isHooked()){
      if (!isAtPosition()) {
        CLIMB_TALON.selectProfileSlot(1, 0);
        CLIMB_TALON.set(ControlMode.MotionMagic, getError());
      }else setClimbPercent(0);
    }
  }
  
  /**
   * exception if distance is not within reasonable bounds or winch is not in correct state
   * @param distance the extended distance in inches
   * @throws Exception if distance is not within range or ratcheting does not match up with movement direction
   */
  public void setHook(double distance) throws Exception{
    goal_height = distance;
    if (distance < -5 || distance > 30){
      throw new Exception("invalid distance");
    } else if ((getPosition() - distance < 0) && (getRatchetState() == RATCHET_ENUM.RATCHETING)){
      throw new Exception("Ratcheting should be enabled only for pulling upwards!");
    }else{ 
      if (isHooked()) {
        if (!isAtPosition()) {
          CLIMB_TALON.selectProfileSlot(1, 0);
          CLIMB_TALON.set(ControlMode.MotionMagic, distanceToEncoderUnits(distance));
        }else setClimbPercent(0);
        
      } else {
        CLIMB_TALON.selectProfileSlot(0, 0);
        CLIMB_TALON.set(ControlMode.MotionMagic, distanceToEncoderUnits(distance));
      }
    }
  }

  /**
   * test to see if hook is pulling on the climb bar
   * @return true if current of lead motor exceeds climb threshold
   */
  public boolean isHooked(){
    return (getStatorCurrent() < MotorConstants.CLIMB_AMPS); 
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
   * @return the current position of the bar, in inches above starting position
   */
  public double getPosition(){
    return (double)CLIMB_TALON.getSelectedSensorPosition() * ControlConstants.CLIMB_ENCODER_TO_DISTANCE;
  }
  /**
   * 
   * @param distance, the distance in inches above zero position
   * @return the position in encoder units
   */
  public double distanceToEncoderUnits(double distance){
    return distance/ControlConstants.CLIMB_ENCODER_TO_DISTANCE;
  }

  /**
   * 
   * @param encoderUnits, the distance to be converted in encoder units
   * @return the distance in inches above the zero position
   */
  public double encoderUnitsToDistanct(double encoderUnits){
    return encoderUnits * ControlConstants.CLIMB_ENCODER_TO_DISTANCE;
  }

  /**
   * 
   * @return true if the climb is within HOOK_PRECISION of its target destination
   */
  public boolean isAtPosition(){
    return Math.abs(getPosition() - goal_height) < SetpointConstants.HOOK_PRECISON;
  }

  /**
   * clears the motion profile of the climb Talon
   */
  public void clearMotionProfile(){
    CLIMB_TALON.clearMotionProfileTrajectories();
  }

  /**
   * 
   * @return true if the trapezoidal profile is finished
   */
  public boolean isTrapezoidOver(){
    MotionProfileStatus status = new MotionProfileStatus();
    CLIMB_TALON.getMotionProfileStatus(status);
    return status.isLast;
  }

  /**
   * sets values on the smart dashboard for drivers to read
   */
  @Override
  public void periodic() {
    currentEntry.setNumber(getStatorCurrent());
    positionEntry.setNumber(getPosition());
    hookedEntry.setBoolean(isHooked());
    SmartDashboard.putNumber("potentiometer", getPotentiometer());
    SmartDashboard.putNumber("Goal height", goal_height);
    // This method will be called once per scheduler run
  }

  /**
   * 
   * @param max_acceleration the max acceleration of the motion profile, in in/sec^2
   * @param cruise_speed the cruising speed of the arm, in in/sec
   */
  public void setSmartMotionValues(double max_acceleration, double cruise_speed){
    cruise_speed = cruise_speed / Constants.ControlConstants.CLIMB_ENCODER_TO_DISTANCE / 10;
    max_acceleration = max_acceleration / ControlConstants.CLIMB_ENCODER_TO_DISTANCE / 10;
    CLIMB_TALON.configMotionAcceleration(max_acceleration);
    CLIMB_TALON.configMotionCruiseVelocity(cruise_speed);
  }

  /**FOR TESTING USE ONLY */
  public void setPDF(double p, double d, double f){
    CLIMB_TALON.config_kP(0, p);
    CLIMB_TALON.config_kD(0, d);
    CLIMB_TALON.config_kF(0, f);
  }

  public void setPDFSlot(double p, double d, double f, int slot){
    CLIMB_TALON.config_kP(slot, p);
    CLIMB_TALON.config_kD(slot, d);
    CLIMB_TALON.config_kF(slot, f);
  }
/**
 * 
 * @param voltage the voltage output, with 1023 being full out
 */
public void setVoltage(double voltage) throws Exception{
  if ((voltage > 0) && (getRatchetState() == RATCHET_ENUM.RATCHETING)){
    throw new Exception("Ratcheting should be enabled only for pulling upwards!");
  }else CLIMB_TALON.set(TalonSRXControlMode.PercentOutput, voltage/1023.0*12);
}

}
