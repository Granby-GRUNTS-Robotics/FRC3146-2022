// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.PneumaticConstants;

/** Add your docs here. */
public class RobotMap {
    //solenoids/pneumatics
    public static final PneumaticsControlModule PNEUMATICS_BASE = new PneumaticsControlModule(PneumaticConstants.PCM_ID);
    
    public static final Solenoid INTAKE_UP_SOLENOID = new Solenoid(PneumaticConstants.PCM_ID, PneumaticsModuleType.CTREPCM, PneumaticConstants.INTAKE_UP_SOLENOID_PORT);
    public static final Solenoid INTAKE_DOWN_SOLENOID = new Solenoid(PneumaticConstants.PCM_ID, PneumaticsModuleType.CTREPCM, PneumaticConstants.INTAKE_DOWN_SOLENOID_PORT);    
    
    public static final Solenoid ARM_VERT_SOLENOID = new Solenoid(PneumaticConstants.PCM_ID, PneumaticsModuleType.CTREPCM, PneumaticConstants.ARM_VERT_SOLENOID_PORT);
    public static final Solenoid ARM_HORI_SOLENOID = new Solenoid(PneumaticConstants.PCM_ID, PneumaticsModuleType.CTREPCM, PneumaticConstants.ARM_HORI_SOLENOID_PORT);

    public static final Solenoid RATCHET_SOLENOID = new Solenoid(PneumaticConstants.PCM_ID, PneumaticsModuleType.CTREPCM, PneumaticConstants.RATCHET_SOLENOID_PORT);
    public static final Solenoid CLAW_SOLENOID = new Solenoid(PneumaticConstants.PCM_ID, PneumaticsModuleType.CTREPCM, PneumaticConstants.CLAW_SOLENOID_PORT);

    //motors
    //spark maxes
    public static final CANSparkMax LEFT_DRIVE_SPARK = new CANSparkMax(MotorConstants.LEFT_DRIVE_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax LEFT_FOLLOW_SPARK = new CANSparkMax(MotorConstants.LEFT_FOLLOW_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax RIGHT_DRIVE_SPARK = new CANSparkMax(MotorConstants.RIGHT_DRIVE_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax RIGHT_FOLLOW_SPARK = new CANSparkMax(MotorConstants.RIGHT_FOLLOW_SPARK_ID, MotorType.kBrushless);
    public static final CANSparkMax SHOOTER_LEAD_SPARK = new CANSparkMax(MotorConstants.SHOOTER_LEAD_SPARK_ID, MotorType.kBrushless); //left side
    public static final CANSparkMax SHOOTER_FOLLOW_SPARK = new CANSparkMax(MotorConstants.SHOOTER_FOLLOW_SPARK_ID, MotorType.kBrushless);

    //victors
    public static final TalonSRX CLIMB_DRIVE_TALON = new TalonSRX(MotorConstants.CLIMB_LEAD_TALON_ID);
    public static final VictorSPX CLIMB_FOLLOW_VICTOR = new VictorSPX(MotorConstants.CLIMB_FOLLOW_VICTOR_ID);
    public static final VictorSPX INTAKE_VICTOR = new VictorSPX(MotorConstants.INTAKE_VICTOR_ID);
    public static final VictorSPX MAGAZINE_VICTOR = new VictorSPX(MotorConstants.MAGAZINE_VICTOR_ID);

    //sensors
    public static final AnalogInput CLIMB_POTENTIOMETER = new AnalogInput(ControlConstants.POTENTIOMETER_PORT);
    public static final DigitalInput MAGAZINE_LIMIT_SWITCH = new DigitalInput(ControlConstants.MAGAZINE_LIMIT_SWITCH_PORT);
    public static final DigitalInput CLIMB_LIMIT_SWITCH = new DigitalInput(ControlConstants.CLIMB_LIMIT_SWITCH_PORT);


    public static final PigeonIMU PIGEON = new PigeonIMU(ControlConstants.PIGEON_IMU_ID); 
    
    public static final Joystick DRIVE_JOYSTICK = new Joystick(ControlConstants.DRIVE_CONTROLLER_PORT);
    public static final Joystick BUTTON_JOYSTICK = new Joystick(ControlConstants.BUTTON_JOYSTICK_PORT);

    public static final class Buttons{        
        public static final DoubleSupplier BUTTON_Y = new DoubleSupplier() {
            public double getAsDouble() {
                double x = -BUTTON_JOYSTICK.getRawAxis(1);
                return x;
            };
        };

        public static double getWithDeadZone(double value, double threshold){
                if (Math.abs(value) < threshold) value = 0; else value = (Math.abs(value) - threshold) * (1.0/0.95) * Math.signum(value);
                return value;
        }

        public static final Button INTAKE_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 2);
        public static final Button EJECT_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 10);
        public static final Button INTAKE_UP_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 6);
        public static final Button INTAKE_DOWN_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 5);
        public static final Button INTAKE_FLOAT_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 4);
        public static final Button INTAKE_SOFT_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 3);

        public static final Button SHOOT_BUTTON = new JoystickButton(DRIVE_JOYSTICK, 1);
        public static final Button LOW_GOAL_BUTTON = new JoystickButton(DRIVE_JOYSTICK, 3);
        public static final Button HIGH_GOAL_BUTTON = new JoystickButton(DRIVE_JOYSTICK, 4);

        public static final Trigger LOW_GOAL_TRIGGER = new Trigger(new BooleanSupplier() {
            public boolean getAsBoolean() {
                return LOW_GOAL_BUTTON.get() && SHOOT_BUTTON.get();
            };
        });
        public static final Trigger HIGH_GOAL_TRIGGER = new Trigger(new BooleanSupplier() {
            public boolean getAsBoolean() {
                return HIGH_GOAL_BUTTON.get() && SHOOT_BUTTON.get();
            };
        });
        public static final Trigger LIME_SHOOT_TRIGGER = new Trigger(new BooleanSupplier() {
            public boolean getAsBoolean() {
                return !LOW_GOAL_BUTTON.get() && !HIGH_GOAL_BUTTON.get() && SHOOT_BUTTON.get();
            };
        });
        
        public static final Button CLIMB_BACKWARDS_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 7);
        public static final Button CLIMB_FORWARDS_BUTTON = new JoystickButton(BUTTON_JOYSTICK, 8);
    }

}
