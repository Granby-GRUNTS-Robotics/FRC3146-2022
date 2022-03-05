// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants;

public class Drivetrain extends SubsystemBase {
  
  private static final PigeonIMU PIGEON = RobotMap.PIGEON;
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

    LEFT_DRIVE_SPARK_MAX.setInverted(false);
    RIGHT_DRIVE_SPARK_MAX.setInverted(true);

    LEFT_FOLLOW_SPARK_MAX.follow(LEFT_DRIVE_SPARK_MAX, false);
    RIGHT_FOLLOW_SPARK_MAX.follow(RIGHT_DRIVE_SPARK_MAX, false);

    RIGHT_DRIVE_SPARK_MAX.setSmartCurrentLimit(40);
    LEFT_DRIVE_SPARK_MAX.setSmartCurrentLimit(40);
    RIGHT_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);
    LEFT_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);

    LEFT_DRIVE_ENCODER.setPosition(0);
    RIGHT_DRIVE_ENCODER.setPosition(0);
    LEFT_FOLLOW_ENCODER.setPosition(0);
    RIGHT_FOLLOW_ENCODER.setPosition(0);

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
    LEFT_CONTROLLER.setSmartMotionMaxAccel(ControlConstants.DRIVE_MAX_ACC, 0);
    LEFT_CONTROLLER.setSmartMotionMaxVelocity(ControlConstants.DRIVE_CRUISE, 0);
    RIGHT_CONTROLLER.setSmartMotionMaxAccel(ControlConstants.DRIVE_MAX_ACC, 0);
    RIGHT_CONTROLLER.setSmartMotionMaxVelocity(ControlConstants.DRIVE_CRUISE, 0);

    PIGEON.setFusedHeading(0);
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
    LEFT_CONTROLLER.setReference(left, ControlType.kVelocity, 0, (left==0)? 0 : Math.signum(left) * ControlConstants.DRIVE_ARB_FF);
    RIGHT_CONTROLLER.setReference(right, ControlType.kVelocity, 0, (right==0)? 0 : Math.signum(right) * ControlConstants.DRIVE_ARB_FF);
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

  /**
   * Moves the robot to a set position using a trapezoidal motion curve
   * @param left the goal position of the left side of the robot, in inches
   * @param right the goal position of the right side of the robot, in inches
   */
  public void setGoalPositionsTrapezoid(double left, double right){
    LEFT_CONTROLLER.setReference(left, ControlType.kSmartMotion, 0, (left==0)? 0 : Math.signum(left) * ControlConstants.DRIVE_ARB_FF);
    RIGHT_CONTROLLER.setReference(right, ControlType.kSmartMotion, 0, (right==0)? 0 : Math.signum(right) * ControlConstants.DRIVE_ARB_FF);
    goal_position = left;
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
    //if (goal_position > 20) goal_position = 20;
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
  public void setPIDF(double p, double d, double f){
    LEFT_CONTROLLER.setP(p, 0);
    LEFT_CONTROLLER.setD(d, 0);
    LEFT_CONTROLLER.setFF(f, 0);
    RIGHT_CONTROLLER.setP(p, 0);
    RIGHT_CONTROLLER.setD(d, 0);
    RIGHT_CONTROLLER.setFF(f, 0);
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
