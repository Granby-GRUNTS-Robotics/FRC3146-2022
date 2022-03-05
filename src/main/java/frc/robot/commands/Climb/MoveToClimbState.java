// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ControlConstants.BIG_CLIMB_ENUM;
import frc.robot.subsystems.Climb;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
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
         setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_OPEN, BIG_CLIMB_ENUM.HOOK_CAPTURING, 
                                            BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.HOOK_RETRACTED});
          break;
        case 3:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.HOOK_MIDDLE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.HOOK_RESTING});
          break;
        case 4:
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.HOOK_RESTING, BIG_CLIMB_ENUM.PULLWITHPNEUMATICS, 
                                             BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.HOOK_RETRACTED});
          break;
        case 5:
          //same as 3
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RACHET_FREE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.HOOK_MIDDLE, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.HOOK_RESTING});
          break;
        case 6:
          //same as 4?
          setStateList(new BIG_CLIMB_ENUM[]{ BIG_CLIMB_ENUM.RATCHET_RATCHETING, BIG_CLIMB_ENUM.ARM_VERTICAL, BIG_CLIMB_ENUM.CLAW_CLOSED, BIG_CLIMB_ENUM.ARM_FLOAT, BIG_CLIMB_ENUM.FIRST });
          break;
        default:
          break;
      }
  }
}
