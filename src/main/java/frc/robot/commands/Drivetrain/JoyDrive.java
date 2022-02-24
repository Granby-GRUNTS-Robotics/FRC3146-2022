// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.ControlConstants.MODE_ENUM;
import frc.robot.subsystems.Drivetrain;

public class JoyDrive extends CommandBase {
  
  SendableChooser<MODE_ENUM> modechooser = new SendableChooser<MODE_ENUM>();

  /** Creates a new joyDrive. */
  Joystick joy;
  Drivetrain drive;
  public JoyDrive(Drivetrain drivetrain, Joystick joystick) {
    drive = drivetrain;
    joy = joystick;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("throttle", ControlConstants.kTHROTTLE_MULTIPLIER);
    SmartDashboard.setDefaultNumber("twist", ControlConstants.kTWIST_MULTIPLIER);
    SmartDashboard.setDefaultNumber("slowmodehigh", 0.75);
    SmartDashboard.setDefaultNumber("slowmodelow", 0.5);
  
    modechooser.addOption("both", MODE_ENUM.BOTH);
    modechooser.addOption("twist", MODE_ENUM.TWIST);
    modechooser.addOption("throttle", MODE_ENUM.THROTTLE);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putData("slowmode chooser" , modechooser);
    
    //for tuning
    double throttle = RobotMap.Buttons.getWithDeadZone(-joy.getY());
    double twist = RobotMap.Buttons.getWithDeadZone(joy.getTwist());
    double throttleMult = SmartDashboard.getNumber("throttle", ControlConstants.kTHROTTLE_MULTIPLIER);
    double twistMult = SmartDashboard.getNumber("twist", ControlConstants.kTWIST_MULTIPLIER);
    double slowmodehi = SmartDashboard.getNumber("slowmodehigh", 0.75);
    double slowmodelo = SmartDashboard.getNumber("slowmodelow", 0.5);
    double difference = slowmodehi - slowmodelo;

    double slowfinal = ((1 - joy.getRawAxis(3))/2) * difference + slowmodelo;
    SmartDashboard.putNumber("slowfinal", slowfinal);
    MODE_ENUM mode = modechooser.getSelected();
    //if a certain button is pressed (drive 1), then the multipliers will be ehalved, for finer control of the chassis
    if (mode == MODE_ENUM.BOTH || mode == MODE_ENUM.TWIST) twistMult = twistMult * ((joy.getRawButton(2)) ? slowfinal : 1);
    if (mode == MODE_ENUM.BOTH || mode == MODE_ENUM.THROTTLE) throttleMult = throttleMult * ((joy.getRawButton(2)) ? slowfinal : 1);
    double left = throttle*throttleMult + twist*twistMult;
    double right = throttle*throttleMult - twist*twistMult;

    drive.setSpeeds(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Retwists true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
