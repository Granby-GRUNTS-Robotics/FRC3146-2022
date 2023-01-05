// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.ControlConstants.MODE_ENUM;
import frc.robot.subsystems.Drivetrain;
/**drive with joystick */
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
    drive.setBrakeMode(IdleMode.kBrake);

    drive.setPIDF(0, 0, ControlConstants.DRIVE_VELOCITY_kV, 0);
    SmartDashboard.putData("slowmode chooser" , modechooser);
  }
  private double reversed = 1;
  private double throttleMult = ControlConstants.kTHROTTLE_MULTIPLIER;
  private double twistMult = ControlConstants.kTWIST_MULTIPLIER;
  private double slowmodehi =  0.75;
  private double slowmodelo =  0.5;
  private double difference = slowmodehi - slowmodelo;

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(joy.getRawButtonPressed(11)){
      reversed = reversed*-1;
    }
    
    double thresh = 0.08;
    double throttle = RobotMap.Buttons.getWithDeadZone(-joy.getY(),.25);
    double twist = RobotMap.Buttons.getWithDeadZone(joy.getTwist(), .15);
    

    double slowfinal = ((1 - joy.getRawAxis(3))/2) * difference + slowmodelo;
    SmartDashboard.putNumber("slowfinal", slowfinal);
    //if a certain button is pressed (drive 1), then the multipliers will be ehalved, for finer control of the chassis

    double left = throttle*throttleMult + twist*twistMult;
    double right = throttle*throttleMult - twist*twistMult;
    if(left !=0 && right !=0){
    drive.setSpeeds(left, right);
    }else drive.brake();
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
