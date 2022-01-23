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
        public static final int INTAKE_PISTON_PORT = 0;
        public static final int ARM_PISTON_PORT = 0;
        public static final int RATCHET_PISTON_PORT = 0;
        public static final int CLAW_PISTON_PORT = 0;

        public static enum INTAKE_ENUM {UP, DOWN};
        public static enum ARM_ENUM {HORIZONTAL, VERTICAL};
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

        //victors
        public static final int CLIMB_LEAD_VICTOR_ID = 0;
        public static final int CLIMB_FOLLOW_VICTOR_ID = 0;
        public static final int INTAKE_TALON_ID = 0;
        public static final int MAGAZINE_VICTOR_ID = 0;
    }

    public static final class ControlConstants{
        public static final int DRIVE_CONTROLLER_PORT = 0;
        public static final int BUTTON_JOYSTICK_PORT = 1;
        
        public static final int CLIMB_LEFT_LIMIT_PORT = 0;
        public static final int CLIMB_RIGHT_LIMIT_PORT = 0;
        public static final int CLIMB_ENCODER_A_PORT = 0;
        public static final int CLIMB_ENCODER_B_PORT = 0;
        public static final int CLIMB_ENCODER_I_PORT = 0;

        public static final int MAG_ENCODER_A_PORT = 0;
        public static final int MAG_ENCODER_B_PORT = 0;
        public static final int MAG_ENCODER_I_PORT = 0;

        public static final int MAGAZINE_LIMIT_PORT = 0;

        public static enum CLIMB_STATE {CLIMBING, POSITIONING, HELD};
    }

    public static final class SetpointConstants{
        public static final double ShooterSpeedClose = 0;
        public static final double ShooterPrecision = 0;

        public static final double MAGAZINE_SPEED = 0;
        public static final double MAGAZINE_PRECISION = 0;
        public static final double MAGAZINE_BACKSPACE_DISTANCE = 0;
        public static final double MAGAZINE_FEED_DISTANCE = 0;

        public static final double INTAKE_SPEED = 0;
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
