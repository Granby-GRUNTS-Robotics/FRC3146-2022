// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControlConstants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  
  private NetworkTableEntry shooterSpeedEntry;

  private static final CANSparkMax SHOOTER_LEAD_SPARK_MAX = RobotMap.SHOOTER_LEAD_SPARK;//left side
  private static final CANSparkMax SHOOTER_FOLLOW_SPARK_MAX = RobotMap.SHOOTER_FOLLOW_SPARK;

  private static final RelativeEncoder SHOOTER_LEAD_ENCODER = SHOOTER_LEAD_SPARK_MAX.getEncoder();
  private static final RelativeEncoder SHOOTER_FOLLOW_ENCODER = SHOOTER_FOLLOW_SPARK_MAX.getEncoder();

  private static SparkMaxPIDController SHOOTER_LEAD_CONTROLLER = SHOOTER_LEAD_SPARK_MAX.getPIDController();

  private static double goal_speed;
  
  /** Creates a new Shooter. */
  public Shooter() {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable shootTable = inst.getTable("shoot");
    shooterSpeedEntry = shootTable.getEntry("Shooter Speed");
    
    SHOOTER_LEAD_ENCODER.setVelocityConversionFactor(1);

    SHOOTER_LEAD_SPARK_MAX.setInverted(false);
    SHOOTER_FOLLOW_SPARK_MAX.setInverted(false);

    SHOOTER_FOLLOW_SPARK_MAX.follow(SHOOTER_LEAD_SPARK_MAX, true);

    SHOOTER_LEAD_SPARK_MAX.setSmartCurrentLimit(40);
    SHOOTER_FOLLOW_SPARK_MAX.setSmartCurrentLimit(40);

    SHOOTER_LEAD_ENCODER.setPosition(0);
    SHOOTER_FOLLOW_ENCODER.setPosition(0);
    
    SHOOTER_LEAD_CONTROLLER.setP(ControlConstants.SHOOTER_kP, 0);
    SHOOTER_LEAD_CONTROLLER.setI(0 , 0);
    SHOOTER_LEAD_CONTROLLER.setD(ControlConstants.SHOOTER_kD , 0);
    SHOOTER_LEAD_CONTROLLER.setFF(ControlConstants.SHOOTER_kV, 0);
  }
  /**
   * 
   * @param speed the speed in RPM of the flywheel
   */
  public void setSpeed(double speed){
    goal_speed = speed;
    SHOOTER_LEAD_CONTROLLER.setReference(goal_speed, ControlType.kVelocity, 0);
  }
  /**
   * 
   * @return the speed in RPM of the flywheel
   */
  private double getSpeed(){
    return SHOOTER_LEAD_ENCODER.getVelocity();
  }

  /**
   * 
   * @return the error between the target and current speed of the flywheel
   */
  public double getError(){
    return getSpeed() - goal_speed;
  }

  /**
   * sets the lead spark max to zero volts. Will take time to spin down, as the wheel is in coast mode
   */
  public void brake(){
    SHOOTER_LEAD_SPARK_MAX.setVoltage(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    shooterSpeedEntry.setDouble(getSpeed());
  }

  /**FOR TESTING USE ONLY */
  public void setPDF(double p, double i, double d, double f, double iMaxAccum){
    SHOOTER_LEAD_CONTROLLER.setP(p, 0);
    SHOOTER_LEAD_CONTROLLER.setI(i, 0);
    SHOOTER_LEAD_CONTROLLER.setSmartMotionMaxVelocity(iMaxAccum, 0);
    SHOOTER_LEAD_CONTROLLER.setSmartMotionAccelStrategy(AccelStrategy.kSCurve, 0);
    SHOOTER_LEAD_CONTROLLER.setD(d, 0);
    SHOOTER_LEAD_CONTROLLER.setFF(f, 0);
  }
}
