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
    //start by turning off the LEDs. We don't want to be blinding people if we can at all help it
    setLEDS(false);
    SmartDashboard.setDefaultNumber("Flywheel Speed Multiplier", LimeLightConstants.LIME_FIXER_VALUE);
    SmartDashboard.setDefaultNumber("Goal Relative Height", LimeLightConstants.GOAL_RELATIVE_HEIGHT_FEET);
    SmartDashboard.setDefaultNumber("LimelightX", 0);
  }

  /**
   * network tables for limelight
   * tx is x position of target
   * ty is y position of target
   * ta is area of target
   * ledMode controls LED's on or off. 1 is off, 3 is on
   */
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-granby");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");
  NetworkTableEntry ledMode = table.getEntry("ledMode");

  //variables until tuned, then will become constants
  private double flyWheelFixer = 0;
  private double height = 0;

  /**
   * 
   * @return the angle offset to the limelight target
   */
  public double getX(){
    return tx.getDouble(0.0);
  }

  /**
   * 
   * @return true if leds are on, false if leds are off
   */
  public boolean getLedMode() {
      return (ledMode.getDouble(0.0) == 3);
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
   * has a bit of math, but don't worry about it unless you have taken trig
   * @return the distance, in inches, from the edge of the target
   */
  public double getDistanceFromAngle(){
    return height/(Math.tan(LimeLightConstants.LIMELIGHT_MOUNTING_ANGLE+Math.toRadians(ty.getDouble(0.0))));
  }

  /**
   * 
   * @return whether or not the limelight has a valid target
   */
  public boolean hasTarget(){
    return tv.getDouble(0.0) == 1.0;
  }

  //in case we ever need to augment the calculations. Which, let's all be honest, we probably will have to. So I added in a basic thing for testing
  /**
   * 
   * @param distance, the distance, in inches, from the target 
   * @return a suitable flywheel speed
   */
  private static double a = 3364.0143;
  private static double b = -233.49395;
  private static double c = 79.190021;
  public double calculateFlyWheelSpeed(double distance){
    return (a) + (b * distance) + (c * Math.pow(distance, 2));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    height = SmartDashboard.getNumber("Goal Relative Height", LimeLightConstants.GOAL_RELATIVE_HEIGHT_FEET);
    flyWheelFixer = SmartDashboard.getNumber("Flywheel Speed Multiplier", LimeLightConstants.LIME_FIXER_VALUE);
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Distance From Target (in)", getDistanceFromAngle());
  }
}
