// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
//TODO fill in constants
public final class Constants {
    public static final class PneumaticConstants{
        public static final int PCM_ID = 0;
        public static final int INTAKE_UP_SOLENOID_PORT = 0;
        public static final int INTAKE_DOWN_SOLENOID_PORT = 0;
        public static final int ARM_VERT_SOLENOID_PORT = 0;
        public static final int ARM_HORI_SOLENOID_PORT = 0;
        public static final int RATCHET_SOLENOID_PORT = 0;
        public static final int CLAW_SOLENOID_PORT = 0;

        public static enum INTAKE_ENUM {UP, DOWN, FLOAT, SOFT};
        public static enum ARM_ENUM {HORIZONTAL, VERTICAL, FLOAT, BOTH};
        public static enum CLAW_ENUM {OPEN, CLOSED};
        public static enum RATCHET_ENUM {RATCHETING, FREE};
    }

    public static final class MotorConstants{
        //spark maxes
        public static final int LEFT_DRIVE_SPARK_ID = 0;
        public static final int LEFT_FOLLOW_SPARK_ID = 0;
        public static final int RIGHT_DRIVE_SPARK_ID = 0;
        public static final int RIGHT_FOLLOW_SPARK_ID = 0;
        public static final int SHOOTER_LEAD_SPARK_ID = 0; 
        public static final int SHOOTER_FOLLOW_SPARK_ID = 0;
        public static final double CLIMB_AMPS = 0;

        //victors
        public static final int CLIMB_LEAD_TALON_ID = 6;
        public static final int CLIMB_FOLLOW_VICTOR_ID = 0;
        public static final int INTAKE_TALON_ID = 0;
        public static final int MAGAZINE_VICTOR_ID = 0;
    }

    public static final class ControlConstants{
        public static final int DRIVE_CONTROLLER_PORT = 0;
        public static final int BUTTON_JOYSTICK_PORT = 1;

        public static final int MAG_ENCODER_A_PORT = 0;
        public static final int MAG_ENCODER_B_PORT = 0;
        public static final int MAG_ENCODER_I_PORT = 0;
        public static final int PIGEON_IMU_ID = 0;

        public static final int MAGAZINE_LIMIT_PORT = 0;
        public static final double CLIMB_ENCODER_TO_DISTANCE = 5 / 16 * Math.PI / 8192;

        public static enum HOOK_ENUM {EXTENDED, RESTING, RETRACTED, MIDDLE};

        public static final double SHOOTER_kP = .00049;
        public static final double SHOOTER_kD = 0;
        public static final double SHOOTER_kS = 0.161;
        public static final double SHOOTER_kV = 0.000187;
        public static final double DRIVE_kP = 0;
        public static final double DRIVE_kD = 0;
        public static final double DRIVE_kF = 0;
        public static final double HOOK_UP_kP = 0;
        public static final double HOOK_DOWN_kP = 0;
        public static final double HOOK_UP_kD = 0;
        public static final double HOOK_DOWN_kD = 0;
        public static final double HOOK_UP_kF = 0;
        public static final double HOOK_DOWN_kF = 0;
    }

    public static final class SetpointConstants{
        public static final double SHOOTER_SPEED_LOW = 0;
        public static final double SHOOTER_SPEED_HIGH = 0;
        public static final double SHOOTER_PRECISISON = 0;

        public static final double MAGAZINE_SPEED = 0;
        public static final double MAGAZINE_PRECISION = 100;
        public static final double MAGAZINE_BACKSPACE_DISTANCE = 0;
        public static final double MAGAZINE_FEED_DISTANCE = 0;

        public static final double INTAKE_SPEED = 0;

        public static final double HOOK_EXTENDED = 0;
        public static final double HOOK_RETRACTED = 0;
        public static final double HOOK_MIDDLE = 0;
        public static final double HOOK_RESTING = 0;
        public static final double HOOK_CAPTURING = 0;
    }

    public static final class LimeLightConstants{
        public static final double GOAL_RELATIVE_HEIGHT_M = 2.5;
        public static final double SHOOTER_ANGLE = Math.toRadians(0);
        public static final double LIMELIGHT_MOUNTING_ANGLE = Math.toRadians(0);
        public static final double BALL_MOMENT = 0;
        public static final double BALL_MASS = 0;
        public static final double BALL_RADIUS = 0;
        public static final double WHEEL_RADIUS = 0;
        public static final double WHEEL_MOMENT = 0;

    }
}
