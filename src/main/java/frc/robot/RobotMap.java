// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** Add your docs here. */
public class RobotMap {
    //solenoids/pneumatics
    public static final PneumaticsControlModule PNEUMATICS_BASE = new PneumaticsControlModule(PneumaticConstants.PCM_ID);
    public static final Solenoid INTAKE_SOLENOID = new Solenoid(PneumaticsModuleType.CTREPCM, PneumaticConstants.INTAKE_PISTON_PORT);
    public static final Solenoid ARM_SOLENOID = new Solenoid(PneumaticsModuleType.CTREPCM, PneumaticConstants.ARM_PISTON_PORT);
    public static final Solenoid RATCHET_SOLENOID = new Solenoid(PneumaticsModuleType.CTREPCM, PneumaticConstants.RATCHET_PISTON_PORT);
    public static final Solenoid CLAW_SOLENOID = new Solenoid(PneumaticsModuleType.CTREPCM, PneumaticConstants.CLAW_PISTON_PORT);

    //motors
    //spark maxes
    public static final CANSparkMax LEFT_DRIVE_SPARK = new CANSparkMax(MotorConstants.LEFT_DRIVE_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax LEFT_FOLLOW_SPARK = new CANSparkMax(MotorConstants.LEFT_FOLLOW_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax RIGHT_DRIVE_SPARK = new CANSparkMax(MotorConstants.RIGHT_DRIVE_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax RIGHT_FOLLOW_SPARK = new CANSparkMax(MotorConstants.RIGHT_FOLLOW_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax SHOOTER_LEAD_SPARK = new CANSparkMax(MotorConstants.SHOOTER_LEAD_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax SHOOTER_FOLLOW_SPARK = new CANSparkMax(MotorConstants.SHOOTER_FOLLOW_SPARK_ID, MotorType.kBrushless);

    //victors
    public static final VictorSPX CLIMB_DRIVE_VICTOR = new VictorSPX(MotorConstants.CLIMB_LEAD_VICTOR_ID);
    public static final VictorSPX CLIMB_FOLLOW_VICTOR = new VictorSPX(MotorConstants.CLIMB_FOLLOW_VICTOR_ID);
    public static final TalonSRX INTAKE_TALON = new TalonSRX(MotorConstants.INTAKE_TALON_ID);
    public static final VictorSPX MAGAZINE_VICTOR = new VictorSPX(MotorConstants.MAGAZINE_VICTOR_ID);

    //sensors
    public static final DigitalInput CLIMB_LEFT_LIMIT_SWITCH = new DigitalInput(ControlConstants.CLIMB_LEFT_LIMIT_PORT);
    public static final DigitalInput CLIMB_RIGHT_LIMIT_SWITCH = new DigitalInput(ControlConstants.CLIMB_RIGHT_LIMIT_PORT);
    public static final Encoder CLIMB_ENCODER = new Encoder(ControlConstants.CLIMB_ENCODER_A_PORT, ControlConstants.CLIMB_ENCODER_B_PORT, ControlConstants.CLIMB_ENCODER_I_PORT);
    public static final Encoder MAG_ENCODER = new Encoder(ControlConstants.MAG_ENCODER_A_PORT, ControlConstants.MAG_ENCODER_B_PORT, ControlConstants.MAG_ENCODER_I_PORT);

    public static final DigitalInput MAGAZINE_LIMIT_SWITCH = new DigitalInput(ControlConstants.MAGAZINE_LIMIT_PORT);public static final int INTAKE_LIMIT_PORT = 0;


    public static final Joystick DRIVE_JOYSTICK = new Joystick(ControlConstants.DRIVE_CONTROLLER_PORT);
    public static final Joystick BUTTON_JOYSTICK = new Joystick(ControlConstants.BUTTON_JOYSTICK_PORT);

    public static final class Buttons{
        public Button shootButton = new JoystickButton(BUTTON_JOYSTICK, 1);
    }

}
