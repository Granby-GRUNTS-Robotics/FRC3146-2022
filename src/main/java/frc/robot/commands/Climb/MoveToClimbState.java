// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ControlConstants.HOOK_ENUM;
import frc.robot.Constants.PneumaticConstants.ARM_ENUM;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.subsystems.Climb;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MoveToClimbState extends SequentialCommandGroup {
  /** Creates a new MoveToClimbState. */
  public MoveToClimbState(Climb climb) {
    int state = climb.getClimbState();
    switch (state) {
      case 0:
        addCommands(new StateCommand(climb, RATCHET_ENUM.RATCHETING, HOOK_ENUM.RETRACTED, ARM_ENUM.VERTICAL, CLAW_ENUM.CLOSED));
        break;
      case 1:
        addCommands(new StateCommand(climb, RATCHET_ENUM.FREE, HOOK_ENUM.EXTENDED, ARM_ENUM.VERTICAL, CLAW_ENUM.OPEN));
        break;
      case 2:
       addCommands(new StateCommand(climb, RATCHET_ENUM.RATCHETING, HOOK_ENUM.RETRACTED, ARM_ENUM.VERTICAL, CLAW_ENUM.OPEN));
        break;
      case 3:
        addCommands(new StateCommand(climb, RATCHET_ENUM.RATCHETING, HOOK_ENUM.RETRACTED, ARM_ENUM.VERTICAL, CLAW_ENUM.CLOSED));
        break;
      case 4:
        addCommands(new StateCommand(climb, RATCHET_ENUM.FREE, HOOK_ENUM.MIDDLE, ARM_ENUM.VERTICAL, CLAW_ENUM.CLOSED), new StateCommand(climb, RATCHET_ENUM.FREE, HOOK_ENUM.EXTENDED, ARM_ENUM.HORIZONTAL, CLAW_ENUM.CLOSED), new SetArm(climb, ARM_ENUM.VERTICAL), new SetHook(climb, HOOK_ENUM.RESTING));
        break;
      case 5:
        addCommands(new StateCommand(climb, RATCHET_ENUM.RATCHETING, HOOK_ENUM.RETRACTED, ARM_ENUM.FLOAT, CLAW_ENUM.OPEN));
        break;
      case 6:
        //same as 3
        addCommands(new StateCommand(climb, RATCHET_ENUM.RATCHETING, HOOK_ENUM.RETRACTED, ARM_ENUM.VERTICAL, CLAW_ENUM.CLOSED));
        break;
      case 7:
        //same as 4
        addCommands(new StateCommand(climb, RATCHET_ENUM.FREE, HOOK_ENUM.MIDDLE, ARM_ENUM.VERTICAL, CLAW_ENUM.CLOSED), new StateCommand(climb, RATCHET_ENUM.FREE, HOOK_ENUM.EXTENDED, ARM_ENUM.HORIZONTAL, CLAW_ENUM.CLOSED), new SetArm(climb, ARM_ENUM.VERTICAL), new SetHook(climb, HOOK_ENUM.RESTING));
        break;
      case 8:
        //same as 5
        addCommands(new StateCommand(climb, RATCHET_ENUM.RATCHETING, HOOK_ENUM.RETRACTED, ARM_ENUM.FLOAT, CLAW_ENUM.OPEN));
        break;
      default:
        break;
    }
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
  }
}
