// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimeLightConstants;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimeLight extends SubsystemBase {
  /** Creates a new LimeLight. */
  public LimeLight() {
    ledMode.setNumber(1);
    SmartDashboard.putNumber("Flywheel Speed Multiplier", 0.0);
  }

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-granby");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");
  NetworkTableEntry ledMode = table.getEntry("ledMode");
  private double flyWheelFixer = 1.1;

  /**
   * 
   * @return the angle offset to the limelight target
   */
  public double getX(){
    return tx.getDouble(0.0);
  }

  /**
   * turns on or off Limelight LEDs
   * @param state true is on, false is off
   */
  public void setLEDS(boolean state){
    ledMode.setNumber(state?3:1);
  }

  /**
   * 
   * This section has a LOT of math that may or may not work very well
   */

  private double calculateDistanceWithAngle(){
    return LimeLightConstants.GOAL_RELATIVE_HEIGHT_M/(Math.tan(LimeLightConstants.LIMELIGHT_MOUNTING_ANGLE+Math.toRadians(ty.getDouble(0.0))));
  }


  private double calculateBallSpeed(){
    double x = calculateDistanceWithAngle();
    return Math.sqrt(
      (9.8 * Math.pow(x, 2) / 2)
      /
      (
        (Math.tan(LimeLightConstants.SHOOTER_ANGLE) - LimeLightConstants.GOAL_RELATIVE_HEIGHT_M + 1) 
        * 
        (Math.pow(Math.cos(LimeLightConstants.SHOOTER_ANGLE) , 2))
      )
    );
  }

  public double calculateFlyWheelSpeed(){
    double v = calculateBallSpeed();
    return 
      v
      * Math.sqrt(
        (
          (
            (LimeLightConstants.BALL_MOMENT/Math.pow(LimeLightConstants.BALL_RADIUS, 2))+
            (Math.pow(LimeLightConstants.BALL_MASS, 2))+
            (LimeLightConstants.WHEEL_MOMENT/Math.pow(LimeLightConstants.WHEEL_RADIUS, 2))
          )/LimeLightConstants.WHEEL_MOMENT
        )
      );
  }

  public boolean hasTarget(){
    return (int)tv.getNumber(0) == 1;
  }

  //in case we ever need to augment the calculations. Which, let's all be honest, we probably will have to. So I added in a basic thing for testing
  public double fixFlyWheelSpeed(double flyWheelSpeed){
    return flyWheelSpeed * flyWheelFixer;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double distance = calculateDistanceWithAngle();
    flyWheelFixer = SmartDashboard.getNumber("Flywheel Speed Multiplier", 1.1);
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Distance From Target (m)", distance);
  }
}
