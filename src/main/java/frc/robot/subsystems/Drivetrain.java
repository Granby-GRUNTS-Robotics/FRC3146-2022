// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants;

public class Drivetrain extends SubsystemBase {
  
  private static final PigeonIMU PIGEON = RobotMap.PIGEON;

  //for auto movement and error detection. Because APPARENTLY there is no reason for us to ever need to get the closed-loop error of our systems. That could never happen
  private double goal_position;
  private double goal_angle;
  
  private static final CANSparkMax LEFT_DRIVE_SPARK_MAX = RobotMap.LEFT_DRIVE_SPARK;
  private static final CANSparkMax LEFT_FOLLOW_SPARK_MAX = RobotMap.LEFT_FOLLOW_SPARK;
  private static final CANSparkMax RIGHT_DRIVE_SPARK_MAX = RobotMap.RIGHT_DRIVE_SPARK;
  private static final CANSparkMax RIGHT_FOLLOW_SPARK_MAX = RobotMap.RIGHT_FOLLOW_SPARK;

  private static final RelativeEncoder LEFT_DRIVE_ENCODER = LEFT_DRIVE_SPARK_MAX.getEncoder();
  private static final RelativeEncoder LEFT_FOLLOW_ENCODER = LEFT_FOLLOW_SPARK_MAX.getEncoder();
  private static final RelativeEncoder RIGHT_DRIVE_ENCODER = RIGHT_DRIVE_SPARK_MAX.getEncoder();
  private static final RelativeEncoder RIGHT_FOLLOW_ENCODER = RIGHT_FOLLOW_SPARK_MAX.getEncoder();

  private static final SparkMaxPIDController LEFT_CONTROLLER = LEFT_DRIVE_SPARK_MAX.getPIDController();
  private static final SparkMaxPIDController RIGHT_CONTROLLER = RIGHT_DRIVE_SPARK_MAX.getPIDController();

  /** Creates a new Drivetrain. */
  public Drivetrain() {

    //gear ratio and wheel diameter to go from motor rotations or RPM to inches or inches per minute. Divide by 60 to get inches per second for velocity
    LEFT_DRIVE_ENCODER.setVelocityConversionFactor(1/10.71 * Math.PI * ControlConstants.WHEEL_DIAMETER  / 60.0);
    RIGHT_DRIVE_ENCODER.setVelocityConversionFactor(1/10.71 * Math.PI * ControlConstants.WHEEL_DIAMETER / 60.0);
    LEFT_DRIVE_ENCODER.setPositionConversionFactor(1/10.71 * Math.PI * ControlConstants.WHEEL_DIAMETER); 
    RIGHT_DRIVE_ENCODER.setPositionConversionFactor(1/10.71 * Math.PI * ControlConstants.WHEEL_DIAMETER);

    //only have to invert drive spark maxes, you set the followers' inversions relative to their leaders
    LEFT_DRIVE_SPARK_MAX.setInverted(false);
    RIGHT_DRIVE_SPARK_MAX.setInverted(true);
    //see, I told you sos
    LEFT_FOLLOW_SPARK_MAX.follow(LEFT_DRIVE_SPARK_MAX, false);
    RIGHT_FOLLOW_SPARK_MAX.follow(RIGHT_DRIVE_SPARK_MAX, false);

    //Spark Maxes are put on 40 amp breakers, but lets all be honest, if we end up drawing 40 amps on them then something is definitely wrong
    RIGHT_DRIVE_SPARK_MAX.setSmartCurrentLimit(40);
    LEFT_DRIVE_SPARK_MAX.setSmartCurrentLimit(40);
    RIGHT_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);
    LEFT_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);

    //not really necessary, because all encoder work should be done relatively, but it's just a nice peace of mind thing
    LEFT_DRIVE_ENCODER.setPosition(0);
    RIGHT_DRIVE_ENCODER.setPosition(0);
    LEFT_FOLLOW_ENCODER.setPosition(0);
    RIGHT_FOLLOW_ENCODER.setPosition(0);

    //different PID slots for velocity-based and position-based control. Ironically, the trapezoidal motion control for linear movement uses velocity-based control, so we really only need position-based PID for turning
    LEFT_CONTROLLER.setP(ControlConstants.DRIVE_VELOCITY_kP, 0);
    LEFT_CONTROLLER.setD(ControlConstants.DRIVE_VELOCITY_kD, 0);
    LEFT_CONTROLLER.setFF(ControlConstants.DRIVE_VELOCITY_kV, 0);
    RIGHT_CONTROLLER.setP(ControlConstants.DRIVE_VELOCITY_kP, 0);
    RIGHT_CONTROLLER.setD(ControlConstants.DRIVE_VELOCITY_kD, 0);
    RIGHT_CONTROLLER.setFF(ControlConstants.DRIVE_VELOCITY_kV, 0);
    
    LEFT_CONTROLLER.setP(ControlConstants.DRIVE_POSITION_kP, 1);
    LEFT_CONTROLLER.setD(ControlConstants.DRIVE_POSITION_kD, 1);
    LEFT_CONTROLLER.setFF(ControlConstants.DRIVE_POSITION_kV, 1);
    RIGHT_CONTROLLER.setP(ControlConstants.DRIVE_POSITION_kP, 1);
    RIGHT_CONTROLLER.setD(ControlConstants.DRIVE_POSITION_kD, 1);
    RIGHT_CONTROLLER.setFF(ControlConstants.DRIVE_POSITION_kV, 1);

    //for trapezoidal motion following
    LEFT_CONTROLLER.setSmartMotionMaxAccel(ControlConstants.DRIVE_MAX_ACC, 0);
    LEFT_CONTROLLER.setSmartMotionMaxVelocity(ControlConstants.DRIVE_CRUISE, 0);
    RIGHT_CONTROLLER.setSmartMotionMaxAccel(ControlConstants.DRIVE_MAX_ACC, 0);
    RIGHT_CONTROLLER.setSmartMotionMaxVelocity(ControlConstants.DRIVE_CRUISE, 0);

    //same as resetting encoders. Doesn't do anything, everything is done relatively, but it's still nice to have.
    PIGEON.setFusedHeading(0);

    //default to brake for autonomous
    setBrakeMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Drive Fused Heading", getFusedHeading());
    SmartDashboard.putNumber("Goal Angle", goal_angle);
    // This method will be called once per scheduler run
  }

  /**
   * 
   * @param left the left goal speed, in inches per second, of the robot
   * @param right the right goal speed, in inches per second, of the robot
   */
  public void setSpeeds(double left, double right) {
    LEFT_CONTROLLER.setReference(left, ControlType.kVelocity);
    RIGHT_CONTROLLER.setReference(right, ControlType.kVelocity);
  }

  /**
   * only sets mode if different from current mode.
   * Sets brake mode of all 4 drive motors
   * @param mode the brake mode, either coast or 
   */
  public void setBrakeMode(IdleMode mode){
    if(LEFT_DRIVE_SPARK_MAX.getIdleMode() != mode){
      LEFT_DRIVE_SPARK_MAX.setIdleMode(mode);
      LEFT_FOLLOW_SPARK_MAX.setIdleMode(mode);
      RIGHT_DRIVE_SPARK_MAX.setIdleMode(mode);
      RIGHT_FOLLOW_SPARK_MAX.setIdleMode(mode);
    }
  }

  /**
   * 
   * @return the velocity of the right side of the robot, in inches per second
   */
  public double getRightSpeed(){
    return RIGHT_DRIVE_ENCODER.getVelocity();
  }

  /**
   * 
   * @param angle the goal angle
   * @return the distance, in inches, that each side of the robot will have to turn to reach the desired angle
   */
  private double angleToDistance(double angle){
    return angle / 360 * ControlConstants.DRIVE_WIDTH * Math.PI;
  }

  public void brake(){
    LEFT_DRIVE_SPARK_MAX.set(0);
    RIGHT_DRIVE_SPARK_MAX.set(0);
  }

  /**
   * Moves the robot to a set position using a trapezoidal motion curve
   * relative, not absolute
   * @param left the goal position of the left side of the robot, in inches
   * @param right the goal position of the right side of the robot, in inches
   */
  public void setGoalPositionsTrapezoid(double left, double right){
    LEFT_CONTROLLER.setReference(left + getLeftPosition(), ControlType.kSmartMotion, 0);
    RIGHT_CONTROLLER.setReference(right + getRightPosition(), ControlType.kSmartMotion, 0);
    goal_position = left + getLeftPosition();
  }

  public void setGoalPositionsRelative(double left, double right){
    left = LEFT_DRIVE_ENCODER.getPosition() + left;
    right = RIGHT_DRIVE_ENCODER.getPosition() + right;
    LEFT_CONTROLLER.setReference(left, ControlType.kPosition, 1);
    RIGHT_CONTROLLER.setReference(right, ControlType.kPosition, 1);
    goal_position = left;
  }

  /**
   * Turns the robot to a set angle using trapezoidal motion
   * @param angle, the angle, in degrees, to turn the robot. Positive turns the robot left, negative turns it right
   */
  public void setGoalAngle(double angle){
    goal_angle = PIGEON.getFusedHeading() + angle;
    goal_position = angleToDistance(angle);
    setGoalPositionsRelative(-goal_position, +goal_position);
  }

  /**
   * resets drive encoders to zero
   */
  public void resetEncoders(){
    LEFT_DRIVE_ENCODER.setPosition(0);
    RIGHT_DRIVE_ENCODER.setPosition(0);
  }

  /**
   * 
   * @return the fused heading of the PigeonIMU, in degrees
   */
  private double getFusedHeading(){
    return PIGEON.getFusedHeading();
  }

  /**
   * 
   * @return the position of the left side of the robot, in inches
   */
  public double getLeftPosition(){
    return LEFT_DRIVE_ENCODER.getPosition();
  }

  /**
   * 
   * @return the position of the right side of the robot, in inches
   */
  public double getRightPosition(){
    return RIGHT_DRIVE_ENCODER.getPosition();
  }

  /**
   * 
   * @return the position error of the left side of the robot, in inches
   */
  public double getLinearError(){
    return goal_position -getLeftPosition();
  }

  /**
   * 
   * @return the angular error of the robot, in degrees
   */
  public double getAngularError(){
    return goal_angle - getFusedHeading();
  }

  /**FOR TESTING USE ONLY */
  public void setPIDF(double p, double d, double f, int port){
    LEFT_CONTROLLER.setP(p, port);
    LEFT_CONTROLLER.setD(d, port);
    LEFT_CONTROLLER.setFF(f, port);
    RIGHT_CONTROLLER.setP(p, port);
    RIGHT_CONTROLLER.setD(d, port);
    RIGHT_CONTROLLER.setFF(f, port);
  }

public void setSmartMotionValues(double cruise_speed, double max_acceleration) {
    LEFT_CONTROLLER.setSmartMotionMaxAccel(max_acceleration, 0);
    LEFT_CONTROLLER.setSmartMotionMaxVelocity(cruise_speed, 0);
    RIGHT_CONTROLLER.setSmartMotionMaxAccel(max_acceleration, 0);
    RIGHT_CONTROLLER.setSmartMotionMaxVelocity(cruise_speed, 0);
}

/**
 * 
 * @param voltage the voltage, in volts, to set to the left side of the robot
 */
public void setVoltage(double voltage) {
  LEFT_CONTROLLER.setReference(voltage, ControlType.kVoltage);
}
}
