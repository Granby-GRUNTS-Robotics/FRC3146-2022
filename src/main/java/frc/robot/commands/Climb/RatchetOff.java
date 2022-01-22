package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.subsystems.Climb;

public class RatchetOff extends InstantCommand {
  Climb climb;
  public RatchetOff(Climb climb) {
    this.climb=climb;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climb.setRatchet(RATCHET_ENUM.RATCHETING);
  }
}
