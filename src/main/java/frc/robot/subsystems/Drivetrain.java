// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

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
    LEFT_DRIVE_SPARK_MAX.setInverted(false);
    RIGHT_DRIVE_SPARK_MAX.setInverted(true);

    LEFT_FOLLOW_SPARK_MAX.follow(LEFT_DRIVE_SPARK_MAX);
    RIGHT_FOLLOW_SPARK_MAX.follow(RIGHT_DRIVE_SPARK_MAX);

    RIGHT_DRIVE_SPARK_MAX.setSmartCurrentLimit(40);
    LEFT_DRIVE_SPARK_MAX.setSmartCurrentLimit(40);
    RIGHT_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);
    LEFT_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);

    LEFT_DRIVE_ENCODER.setPosition(0);
    RIGHT_DRIVE_ENCODER.setPosition(0);
    LEFT_FOLLOW_ENCODER.setPosition(0);
    RIGHT_FOLLOW_ENCODER.setPosition(0);

    LEFT_CONTROLLER.setP(ControlConstants.DRIVE_POSITION_kP, 0);
    LEFT_CONTROLLER.setD(ControlConstants.DRIVE_POSITION_kD, 0);
    LEFT_CONTROLLER.setFF(ControlConstants.DRIVE_POSITION_kV, 0);
    RIGHT_CONTROLLER.setP(ControlConstants.DRIVE_POSITION_kP, 0);
    RIGHT_CONTROLLER.setD(ControlConstants.DRIVE_POSITION_kD, 0);
    RIGHT_CONTROLLER.setFF(ControlConstants.DRIVE_POSITION_kV, 0);
    LEFT_CONTROLLER.setP(ControlConstants.DRIVE_VELOCITY_kP, 1);
    LEFT_CONTROLLER.setD(ControlConstants.DRIVE_VELOCITY_kD, 1);
    LEFT_CONTROLLER.setFF(ControlConstants.DRIVE_VELOCITY_kV, 1);
    RIGHT_CONTROLLER.setP(ControlConstants.DRIVE_VELOCITY_kP, 1);
    RIGHT_CONTROLLER.setD(ControlConstants.DRIVE_VELOCITY_kD, 1);
    RIGHT_CONTROLLER.setFF(ControlConstants.DRIVE_VELOCITY_kV, 1);

    PIGEON.setFusedHeading(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeeds(double left, double right) {
    LEFT_CONTROLLER.setReference(left, ControlType.kVelocity, 1);
    RIGHT_CONTROLLER.setReference(right, ControlType.kVelocity, 1);
  }

  private double angleToDistance(double angle){
    return angle / 360 * ControlConstants.DRIVE_WIDTH * Math.PI;
  }

  public double distanceToEncoder(double distance){
    return distance / ControlConstants.DRIVE_ENCODER_TO_DISTANCE;
  }

  public double angleToEncoder(double angle){
    return distanceToEncoder(angleToDistance(angle));
  }

  public void setGoalPositions(double left, double right){
    LEFT_CONTROLLER.setReference(left, ControlType.kPosition, 0);
    LEFT_CONTROLLER.setReference(right, ControlType.kPosition, 0);
    goal_position = left;
  }

  public void setGoalAngle(double angle){
    goal_angle = getFusedHeading() + angle;
    goal_position = angleToEncoder(goal_angle);
    setGoalPositions(goal_position, -goal_position);
  }

  private double getFusedHeading(){
    return PIGEON.getFusedHeading();
  }

  public double[] getPositions(){
    return new double[]{LEFT_DRIVE_ENCODER.getPosition(), RIGHT_DRIVE_ENCODER.getPosition()};
  }

  public double getLinearError(){
    return goal_position -getPositions()[0];
  }

  public double getAngularError(){
    return goal_angle - getFusedHeading();
  }

  /**FOR TESTING USE ONLY */
  public void setPIDF(double p, double d, double f){
    LEFT_CONTROLLER.setP(p);
    LEFT_CONTROLLER.setD(d);
    LEFT_CONTROLLER.setFF(f);
    RIGHT_CONTROLLER.setP(p);
    RIGHT_CONTROLLER.setD(d);
    RIGHT_CONTROLLER.setFF(f);
  }
}
