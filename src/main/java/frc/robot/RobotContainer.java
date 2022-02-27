// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ControlConstants.BIG_CLIMB_ENUM;
import frc.robot.RobotMap.Buttons;
import frc.robot.commands.Climb.ClimbPidTune;
import frc.robot.commands.Climb.ClimbSetMove;
import frc.robot.commands.Climb.ClimbSetSpeed;
import frc.robot.commands.Climb.ClimbSetVoltage;
import frc.robot.commands.Climb.DecrementClimbState;
import frc.robot.commands.Climb.IncrementClimbState;
import frc.robot.commands.Climb.ManualClimbMotor;
import frc.robot.commands.Climb.MoveToClimbState;
import frc.robot.commands.Climb.StateCommand;
import frc.robot.commands.Drivetrain.DrivePIDTune;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Drivetrain.JoyDrive;
import frc.robot.commands.Intake.IntakeButtonCommand;
import frc.robot.commands.Intake.ManualIntakeMotor;
import frc.robot.commands.Intake.MoveIntakeDown;
import frc.robot.commands.Intake.MoveIntakeFloat;
import frc.robot.commands.Intake.MoveIntakeSoft;
import frc.robot.commands.Intake.MoveIntakeUp;
import frc.robot.commands.Magazine.MagIntake;
import frc.robot.commands.Magazine.MagMoveBase;
import frc.robot.commands.Shooter.RevUpShuffleboard;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootShuffleBoard;
import frc.robot.commands.Shooter.ShooterBrake;
import frc.robot.commands.Shooter.ShooterPIDTune;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final Climb M_CLIMB = new Climb();
  private static final Intake M_INTAKE = new Intake();
  private static final Shooter M_SHOOTER = new Shooter();
  private static final Drivetrain M_DRIVETRAIN = new Drivetrain();
  //private static final LimeLight M_LIME_LIGHT = new LimeLight();
  private static final Magazine M_MAGAZINE = new Magazine();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SmartDashboard.putData("ManualClimbMotor (Joystick-Controlled)", new ManualClimbMotor(M_CLIMB, Buttons.BUTTON_Y));
    SmartDashboard.putData("Climb PID Set", new ClimbPidTune(M_CLIMB));
    SmartDashboard.putData("Climb Set Move", new ClimbSetMove(M_CLIMB));
    SmartDashboard.putData("Climb Set Speed", new ClimbSetSpeed(M_CLIMB));
    SmartDashboard.putData("Climb Set Voltage", new ClimbSetVoltage(M_CLIMB));

    SmartDashboard.putData("Arm Horizontal", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.ARM_HORIZONTAL));
    SmartDashboard.putData("Arm Vertical", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.ARM_VERTICAL));
    SmartDashboard.putData("Arm Float", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.ARM_FLOAT));
    SmartDashboard.putData("Claw Open", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.CLAW_OPEN));
    SmartDashboard.putData("Claw Closed", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.CLAW_CLOSED));
    SmartDashboard.putData("Ratchet Engaged", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.RATCHET_RATCHETING));
    SmartDashboard.putData("Ratchet Free", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.RACHET_FREE));

    SmartDashboard.putData("Drivetrain PID Set", new DrivePIDTune(M_DRIVETRAIN));
    SmartDashboard.putData("Drive to Location", new DriveToLocation(M_DRIVETRAIN, 24));
    SmartDashboard.putData("Turn 90 Degrees", new DriveToAngle(M_DRIVETRAIN, 90));
    
    SmartDashboard.putData("Manual Shooter Speed", new RevUpShuffleboard(M_SHOOTER));
    SmartDashboard.putData("Shooter PID Set", new ShooterPIDTune(M_SHOOTER));
    SmartDashboard.putData("Manual Intake", new ManualIntakeMotor(M_INTAKE));
    SmartDashboard.putData("Manual Magazine Move", new MagMoveBase(M_MAGAZINE));

    SmartDashboard.putData("Intake Down", new MoveIntakeDown(M_INTAKE));
    SmartDashboard.putData("Intake Up", new MoveIntakeUp(M_INTAKE));
    SmartDashboard.putData("Intake Soft", new MoveIntakeSoft(M_INTAKE));
    SmartDashboard.putData("Intake Float", new MoveIntakeFloat(M_INTAKE));
    
    M_DRIVETRAIN.setDefaultCommand(new JoyDrive(M_DRIVETRAIN, RobotMap.DRIVE_JOYSTICK));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    Buttons.INTAKE_BUTTON.whileActiveOnce(new IntakeButtonCommand(M_INTAKE))
    .whileActiveOnce(new MagIntake(M_MAGAZINE));

    Buttons.INTAKE_UP_BUTTON.whenPressed(new MoveIntakeUp(M_INTAKE));
    Buttons.INTAKE_FLOAT_BUTTON.whenPressed(new MoveIntakeFloat(M_INTAKE));
    Buttons.INTAKE_SOFT_BUTTON.whenPressed(new MoveIntakeSoft(M_INTAKE));
    Buttons.INTAKE_DOWN_BUTTON.whenPressed(new MoveIntakeDown(M_INTAKE));

    Buttons.shootButton.whenReleased(new ShooterBrake(M_SHOOTER));

    Buttons.LOW_GOAL_BUTTON.and(Buttons.shootButton).whileActiveOnce(new ShootShuffleBoard(M_MAGAZINE,M_SHOOTER));
    Buttons.HIGH_GOAL_BUTTON.and(Buttons.shootButton).whileActiveOnce(new ShootHigh(M_MAGAZINE,M_SHOOTER));
    //Buttons.shootButton.whileActiveOnce(new LimeTurnAndShoot(M_DRIVETRAIN, M_LIME_LIGHT, M_SHOOTER, M_MAGAZINE));
    
    
    //Only uncomment once all testing has been done
    Buttons.CLIMB_FORWARDS_BUTTON.whenPressed(new IncrementClimbState(M_CLIMB))
    .whenReleased(new MoveToClimbState(M_CLIMB));
    Buttons.CLIMB_BACKWARDS_BUTTON.whenPressed(new DecrementClimbState(M_CLIMB))
    .whenReleased(new MoveToClimbState(M_CLIMB));
  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
