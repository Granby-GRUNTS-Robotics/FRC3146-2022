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

    public static int climb_state = 0;
    
    public static final class PneumaticConstants{
        //PCM ID, solenoid ports
        public static final int PCM_ID = 2;
        public static final int INTAKE_UP_SOLENOID_PORT = 5;
        public static final int INTAKE_DOWN_SOLENOID_PORT = 4;
        public static final int ARM_VERT_SOLENOID_PORT = 2;
        public static final int ARM_HORI_SOLENOID_PORT = 3;
        public static final int RATCHET_SOLENOID_PORT = 0;
        public static final int CLAW_SOLENOID_PORT = 1;
        //"double solenoids" have 4 permutations. Normal solenoids only have 2 states
        public static enum INTAKE_ENUM {UP, DOWN, FLOAT, SOFT};
        public static enum ARM_ENUM {HORIZONTAL, VERTICAL, FLOAT, BOTH};
        public static enum CLAW_ENUM {OPEN, CLOSED};
        public static enum RATCHET_ENUM {RATCHETING, FREE};
    }

    public static final class MotorConstants{
        //spark maxes
        public static final int LEFT_DRIVE_SPARK_ID = 8;
        public static final int LEFT_FOLLOW_SPARK_ID = 9;
        public static final int RIGHT_DRIVE_SPARK_ID = 3;
        public static final int RIGHT_FOLLOW_SPARK_ID = 4;
        public static final int SHOOTER_LEAD_SPARK_ID = 6; 
        public static final int SHOOTER_FOLLOW_SPARK_ID = 7;
        public static final double CLIMB_AMPS = -30;

        //CTRE (victors and talons)
        public static final int CLIMB_LEAD_TALON_ID = 11;
        public static final int CLIMB_FOLLOW_VICTOR_ID = 10;
        public static final int INTAKE_VICTOR_ID = 12;
        public static final int MAGAZINE_VICTOR_ID = 5;
    }

    public static final class ControlConstants{

        public static final int DRIVE_CONTROLLER_PORT = 0;
        public static final int BUTTON_JOYSTICK_PORT = 1;

        //sensors
        public static final int POTENTIOMETER_PORT = 0;
        public static final int PIGEON_IMU_ID = 1;
        public static final int MAGAZINE_LIMIT_SWITCH_PORT = 0;

        //conversion factors
        public static final double CLIMB_ENCODER_TO_DISTANCE = (double) 10 / 16 * Math.PI / 8192; //8192 == counts per rotation. Will be different if we use potentiometer
        public static final double DRIVE_ENCODER_TO_DISTANCE = 6*Math.PI / 10.71; //encoder * DRIVE_ENCODER_TO_DISTANCE = wheel distance
        public static final double DRIVE_WIDTH = 21.5 ;//inches

        //timings
        public static final double ARM_PISTON_HORIZONTAL_TIME = 2.0;
        public static final double ARM_PISTON_VERTICAL_TIME = 0.75;
        public static final double CLAW_PISTON_TIME = 0.3;
        public static final double RATCHET_PISTON_TIME = 0.2;
        //eek
        public static enum BIG_CLIMB_ENUM{PULLWITHPNEUMATICS, HOOK_EXTENDED, HOOK_RESTING, HOOK_RETRACTED, HOOK_SWING_UP, HOOK_CAPTURING, 
                                          ARM_HORIZONTAL, ARM_VERTICAL, ARM_FLOAT, ARM_BOTH, 
                                          CLAW_OPEN, CLAW_CLOSED, 
                                          RATCHET_RATCHETING, RACHET_FREE, FIRST, LAST, HOOK_OFF_PREV, WAIT
                                         };
        public static enum HOOK_ENUM {EXTENDED, RESTING, RETRACTED, MIDDLE, CAPTURING, FIRST, OFF_PREVIOUS};

        //for joy drive. Might keep it in, might not
        public static enum MODE_ENUM {BOTH, TWIST, THROTTLE};

        //PID, motion control, feed forward values
        public static final double SHOOTER_kP = .00025;
        public static final double SHOOTER_kD = 0;
        public static final double SHOOTER_kS = 0.161;
        public static final double SHOOTER_kV = 0.000185;

        public static final double DRIVE_ARB_FF = -0.15;

        public static final double DRIVE_MAX_ACC = 30;
        public static final double DRIVE_CRUISE = 50;

        public static final double DRIVE_POSITION_kP = 0.06;
        public static final double DRIVE_POSITION_kD = 0.0007;
        public static final double DRIVE_POSITION_kS = 0.;
        public static final double DRIVE_POSITION_kV = 0.0;

        public static final double DRIVE_VELOCITY_kP = 0.03;
        public static final double DRIVE_VELOCITY_kD = 0.0003;
        public static final double DRIVE_VELOCITY_kV = 0.0062;

        public static final double CLIMB_UP_ARB_FF = 0;
        public static final double CLIMB_DOWN_ARB_FF = 12;

        public static final double CLIMB_MAX_ACC = 20;
        public static final double CLIMB_CRUISE = 40
        ;
        
        public static final double HOOK_UP_kP = 0.05;
        public static final double HOOK_UP_kD = 0;
        public static final double HOOK_UP_kF = 0.165;
        
        public static final double HOOK_DOWN_kP = 0.15;
        public static final double HOOK_DOWN_kD = 0;
        public static final double HOOK_DOWN_kF = 0.227;
        
        //drivetrain control constants
        public static final double kTHROTTLE_MULTIPLIER = 30;
        public static final double kTWIST_MULTIPLIER = 40;
        public static final double WHEEL_DIAMETER = 5.9; // approx in inches
    }

    public static final class SetpointConstants{
        public static final double SHOOTER_SPEED_LOW = 1000;
        public static final double SHOOTER_SPEED_HIGH = 1000;
        public static final double SHOOTER_PRECISISON = 30;

        public static final double MAGAZINE_SPEED = 1.0;
        public static final double MAGAZINE_INTAKE_SPEED = 0.6;
        public static final double MAGAZINE_PRECISION = 10;
        public static final double MAGAZINE_BACKSPACE_DISTANCE = -0.05;
        public static final double MAGAZINE_FEED_DISTANCE = 1.5;

        public static final double INTAKE_SPEED = 0.75;

        public static final double HOOK_EXTENDED = 27.5;
        public static final double HOOK_FIRST_BAR = 22;
        public static final double HOOK_RETRACTED = 2;
        public static final double HOOK_MIDDLE = 7;
        public static final double HOOK_RESTING = 25.4;
        public static final double HOOK_CAPTURING = -1.5;
        public static final double HOOK_OFF_PREV = 20;

        public static final double HOOK_PRECISON = 0.5;
    }

    public static final class LimeLightConstants{
        public static final double GOAL_RELATIVE_HEIGHT_FEET = 5.91;
        public static final double LIMELIGHT_MOUNTING_ANGLE = Math.toRadians(45);
        public static final double LIME_FIXER_VALUE = 100.;
    }
}
