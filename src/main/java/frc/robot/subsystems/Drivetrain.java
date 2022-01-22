// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  
  private static final CANSparkMax LEFT_DRIVE_SPARK_MAX = RobotMap.LEFT_DRIVE_SPARK;
  private static final CANSparkMax LEFT_FOLLOW_SPARK_MAX = RobotMap.LEFT_FOLLOW_SPARK;
  private static final CANSparkMax RIGHT_DRIVE_SPARK_MAX = RobotMap.RIGHT_DRIVE_SPARK;
  private static final CANSparkMax RIGHT_FOLLOW_SPARK_MAX = RobotMap.RIGHT_FOLLOW_SPARK;

  private static final RelativeEncoder LEFT_DRIVE_ENCODER = LEFT_DRIVE_SPARK_MAX.getEncoder();
  private static final RelativeEncoder LEFT_FOLLOW_ENCODER = LEFT_FOLLOW_SPARK_MAX.getEncoder();
  private static final RelativeEncoder RIGHT_DRIVE_ENCODER = RIGHT_DRIVE_SPARK_MAX.getEncoder();
  private static final RelativeEncoder RIGHT_FOLLOW_ENCODER = RIGHT_FOLLOW_SPARK_MAX.getEncoder();

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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
