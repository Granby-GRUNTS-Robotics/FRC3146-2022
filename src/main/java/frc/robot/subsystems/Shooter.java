// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  
  private static final CANSparkMax SHOOTER_LEAD_SPARK_MAX = RobotMap.SHOOTER_LEAD_SPARK;
  private static final CANSparkMax SHOOTER_FOLLOW_SPARK_MAX = RobotMap.SHOOTER_FOLLOW_SPARK;

  private static final RelativeEncoder SHOOTER_LEAD_ENCODER = SHOOTER_LEAD_SPARK_MAX.getEncoder();
  private static final RelativeEncoder SHOOTER_FOLLOW_ENCODER = SHOOTER_FOLLOW_SPARK_MAX.getEncoder();

  private static SparkMaxPIDController leadController = SHOOTER_LEAD_SPARK_MAX.getPIDController();

  private static double goal_speed;
  /** Creates a new Shooter. */
  public Shooter() {
    SHOOTER_LEAD_SPARK_MAX.setInverted(false);
    SHOOTER_FOLLOW_SPARK_MAX.setInverted(false);

    SHOOTER_FOLLOW_SPARK_MAX.follow(SHOOTER_LEAD_SPARK_MAX);

    SHOOTER_LEAD_SPARK_MAX.setSmartCurrentLimit(40);
    SHOOTER_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);

    SHOOTER_LEAD_ENCODER.setPosition(0);
    SHOOTER_FOLLOW_ENCODER.setPosition(0);
    
    leadController.setP(0);
    leadController.setI(0);
    leadController.setD(0);
    leadController.setFF(0);
  }

  public void setSpeed(double speed){
    goal_speed = speed;
    leadController.setReference(speed, CANSparkMax.ControlType.kVelocity);
  }

  public double getError(){
    return SHOOTER_LEAD_ENCODER.getVelocity() - goal_speed;
  }

  public void brake(){
    SHOOTER_LEAD_SPARK_MAX.setVoltage(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
