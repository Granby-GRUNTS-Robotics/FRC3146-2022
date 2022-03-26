// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ControlConstants.BIG_CLIMB_ENUM;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;

/**list of climb states. We will determine where brakes are necessary (hopefully nowhere) and then add that to FullClimbNoStops (which should really be called FullClimbLessStops) */
public class MoveToClimbState extends StateCommand {
  /** Creates a new MoveToClimbState. */
  public static int climb_state;
  public MoveToClimbState(Climb climb) {
    super(climb);
  }
  @Override
  public void initialize() {
      super.initialize();
      SmartDashboard.putNumber("climb state", climb_state);
      switch (climb_state) {
        case 0:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.HOOK_RETRACTED});
          break;
        case 1:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_OPEN, BIG_CLIMB_ENUM.FIRST});
          break;
        case 2:
         setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.PIDONE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_OPEN, BIG_CLIMB_ENUM.HOOK_CAPTURING, BIG_CLIMB_ENUM.PULL_UNTIL_SWITCH,
                                            BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.PIDZERO, BIG_CLIMB_ENUM.HOOK_RETRACTED});
          break;
        case 3:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED,BIG_CLIMB_ENUM.PIDZERO, BIG_CLIMB_ENUM.HOOK_SWING_UP, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.HOOK_RESTING});
          break;
        case 4:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING,BIG_CLIMB_ENUM.PIDZERO, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.HOOK_RESTING,BIG_CLIMB_ENUM.PIDONE, BIG_CLIMB_ENUM.HOOK_OFF_PREV, BIG_CLIMB_ENUM.WAIT, BIG_CLIMB_ENUM.PULLWITHPNEUMATICS, BIG_CLIMB_ENUM.PULL_UNTIL_SWITCH,
                                             BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.HOOK_RETRACTED});
          break;
        case 5:
          //same as 3
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED,BIG_CLIMB_ENUM.PIDZERO, BIG_CLIMB_ENUM.HOOK_SWING_UP});
          break;
        case 6:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.PIDONE, BIG_CLIMB_ENUM.LAST });
          break;
        default:
          break;
      }
  }
}
