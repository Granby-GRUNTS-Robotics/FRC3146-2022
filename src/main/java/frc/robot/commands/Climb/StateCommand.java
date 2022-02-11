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
public class StateCommand extends SequentialCommandGroup {
  /** Creates a new StateCommandBase. */
  public StateCommand(Climb climb, RATCHET_ENUM ratchet_state, HOOK_ENUM hook_state, ARM_ENUM arm_state, CLAW_ENUM claw_state) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new SetRachet(climb, ratchet_state),new SetArm(climb, arm_state),new SetClaw(climb, claw_state),new SetHook(climb, hook_state));
  }
}
