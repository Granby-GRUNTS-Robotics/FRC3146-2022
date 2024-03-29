// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ControlConstants.BIG_CLIMB_ENUM;
import frc.robot.RobotMap.Buttons;
import frc.robot.commands.LimeToggle;
import frc.robot.commands.LimeTurnAndShoot;
import frc.robot.commands.LimeTurnOff;
import frc.robot.commands.LimeTurnOn;
import frc.robot.commands.LimelightTune;
import frc.robot.commands.Autonomous.AutoFromLine;
import frc.robot.commands.Autonomous.AutoWithTurn;
import frc.robot.commands.Autonomous.FourBallAuto;
import frc.robot.commands.Autonomous.ProtectedShooting;
import frc.robot.commands.Autonomous.ProtectedShootingTuner;
import frc.robot.commands.Climb.ClimbPidTune;
import frc.robot.commands.Climb.ClimbSetMove;
import frc.robot.commands.Climb.ClimbSetSpeed;
import frc.robot.commands.Climb.ClimbSetVoltage;
import frc.robot.commands.Climb.DecrementClimbState;
import frc.robot.commands.Climb.IncrementClimbState;
import frc.robot.commands.Climb.ManualClimbMotor;
import frc.robot.commands.Climb.MoveToClimbState;
import frc.robot.commands.Climb.PIDSlotSwitch;
import frc.robot.commands.Climb.StateCommand;
import frc.robot.commands.Drivetrain.DrivePIDTune;
import frc.robot.commands.Drivetrain.DriveToAbsoluteAngle;
import frc.robot.commands.Drivetrain.DriveToAngle;
import frc.robot.commands.Drivetrain.DriveToLocation;
import frc.robot.commands.Drivetrain.JoyDrive;
import frc.robot.commands.Drivetrain.LimeTurn;
import frc.robot.commands.Intake.IntakeButtonCommand;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOff;
import frc.robot.commands.Intake.IntakeOut;
import frc.robot.commands.Intake.MoveIntakeDown;
import frc.robot.commands.Intake.MoveIntakePartialDown;
import frc.robot.commands.Intake.MoveIntakePartialUp;
import frc.robot.commands.Intake.MoveIntakeUp;
import frc.robot.commands.Magazine.MagIntake;
import frc.robot.commands.Magazine.MagMoveBase;
import frc.robot.commands.Magazine.MagazineOut;
import frc.robot.commands.Shooter.RevUpShuffleboard;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootLime;
import frc.robot.commands.Shooter.ShootLow;
import frc.robot.commands.Shooter.ShootShuffleBoard;
import frc.robot.commands.Shooter.ShooterBrake;
import frc.robot.commands.Shooter.ShooterPIDTune;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
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
  private static final LimeLight M_LIME_LIGHT = new LimeLight();
  private static final Magazine M_MAGAZINE = new Magazine();
  private static final SendableChooser<Command> auto_chooser = new SendableChooser<Command>();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    CameraServer.startAutomaticCapture(0);
    SmartDashboard.setDefaultNumber("Auto Start Time", 0);
    auto_chooser.addOption("Line", new AutoFromLine(M_MAGAZINE, M_INTAKE, M_DRIVETRAIN, M_SHOOTER, M_LIME_LIGHT));
    auto_chooser.addOption("Turn", new AutoWithTurn(M_MAGAZINE, M_INTAKE, M_DRIVETRAIN, M_SHOOTER));
    auto_chooser.addOption("4 Ball Test", new FourBallAuto(M_MAGAZINE, M_INTAKE, M_DRIVETRAIN, M_SHOOTER, M_LIME_LIGHT));
    SmartDashboard.putData("auto chooser", auto_chooser);

    SmartDashboard.setPersistent("auto chooser");

    SmartDashboard.putData("ratchet free", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.RACHET_FREE));
    SmartDashboard.putData("Manual Climb Motor", new ManualClimbMotor(M_CLIMB, RobotMap.Buttons.BUTTON_Y));
    SmartDashboard.putData("ratchet engaged", new StateCommand(M_CLIMB, BIG_CLIMB_ENUM.RATCHET_RATCHETING));
    //SmartDashboard.putData("Rev up shuffleboard", new RevUpShuffleboard(M_SHOOTER));
    //SmartDashboard.putData("Shooter PID Tune", new ShooterPIDTune(M_SHOOTER));
    /*
    SmartDashboard.putData("Drivetrain PID Set", new DrivePIDTune(M_DRIVETRAIN));
    SmartDashboard.putData("Drive to Location", new DriveToLocation(M_DRIVETRAIN, 24));
    SmartDashboard.putData("Turn 90 Degrees", new DriveToAngle(M_DRIVETRAIN, 90));

    
    SmartDashboard.putData("Lime Turn", new LimeTurn(M_DRIVETRAIN, M_LIME_LIGHT));
    SmartDashboard.putData("Lime Set Speed Shoot", new ShootLime(M_MAGAZINE, M_SHOOTER, M_LIME_LIGHT));
    
    
    
    
    SmartDashboard.putData("Manual Magazine Move", new MagMoveBase(M_MAGAZINE));

    SmartDashboard.putData("Intake Down", new MoveIntakeDown(M_INTAKE));
    SmartDashboard.putData("Intake Up", new MoveIntakeUp(M_INTAKE));
    SmartDashboard.putData("Intake Soft", new MoveIntakeSoft(M_INTAKE));
    SmartDashboard.putData("Intake Float", new MoveIntakeFloat(M_INTAKE));
    */
    //SmartDashboard.putData("turn to 90", new DriveToAbsoluteAngle(M_DRIVETRAIN, 90));
    //SmartDashboard.putData("Limelight Tune", new LimelightTune(M_LIME_LIGHT));
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
    
    Buttons.INTAKE_BUTTON.whenHeld(new IntakeButtonCommand(M_INTAKE))
    .whenHeld(new MagIntake(M_MAGAZINE));

    Buttons.SLOW_MODE_BUTTON.whenPressed(new LimeToggle(M_LIME_LIGHT));

    Buttons.INTAKE_UP_BUTTON.whenPressed(new MoveIntakeUp(M_INTAKE).andThen(new IntakeOff(M_INTAKE)));
    Buttons.INTAKE_PARTIAL_UP_BUTTON.whenPressed(new MoveIntakePartialUp(M_INTAKE).andThen(new IntakeOff(M_INTAKE)));
    Buttons.INTAKE_PARTIAL_DOWN_BUTTON.whenPressed(new MoveIntakePartialDown(M_INTAKE).andThen(new IntakeIn(M_INTAKE)));
    Buttons.INTAKE_DOWN_BUTTON.whenPressed(new MoveIntakeDown(M_INTAKE).andThen(new IntakeIn(M_INTAKE)));

    Buttons.EJECT_BUTTON.whenHeld(new MagazineOut(M_MAGAZINE)).whenHeld(new IntakeOut(M_INTAKE));

    Buttons.SHOOT_BUTTON.whenReleased(new ShooterBrake(M_SHOOTER));

    Buttons.LOW_GOAL_TRIGGER.whileActiveOnce(new ShootLow(M_MAGAZINE,M_SHOOTER, M_INTAKE));
    Buttons.HIGH_GOAL_TRIGGER.whileActiveOnce(new ShootHigh(M_MAGAZINE, M_SHOOTER, M_INTAKE));
    //Buttons.LIME_TURN_TRIGGER.whileActiveOnce(new LimeTurn(M_DRIVETRAIN, M_LIME_LIGHT));
    
    Buttons.LIME_GOAL_TRIGGER.whileActiveOnce(new LimeTurnAndShoot(M_DRIVETRAIN, M_LIME_LIGHT, M_SHOOTER, M_MAGAZINE,M_INTAKE));
    Buttons.PROTECTED_GOAL_TRIGGER.whileActiveOnce(new ProtectedShootingTuner(M_MAGAZINE,M_INTAKE,M_DRIVETRAIN, M_SHOOTER));
    
    //Only uncomment once all testing has been done
    Buttons.CLIMB_FORWARDS_BUTTON.whenPressed(new IncrementClimbState(M_CLIMB))
    .whenReleased(new MoveToClimbState(M_CLIMB));
    Buttons.CLIMB_BACKWARDS_BUTTON.whenPressed(new DecrementClimbState(M_CLIMB))
    .whenReleased(new MoveToClimbState(M_CLIMB));
    Buttons.CLIMB_MANUAL_A.and(Buttons.CLIMB_MANUAL_B).whileActiveOnce(new ManualClimbMotor(M_CLIMB, RobotMap.Buttons.BUTTON_Y));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return (auto_chooser.getSelected()!=null)?auto_chooser.getSelected():new AutoFromLine(M_MAGAZINE, M_INTAKE, M_DRIVETRAIN, M_SHOOTER, M_LIME_LIGHT);
  }
}
