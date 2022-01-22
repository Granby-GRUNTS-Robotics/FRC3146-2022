package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.subsystems.Climb;

public class ClawOpen extends InstantCommand {
  Climb climb;
  public ClawOpen(Climb climb) {
    this.climb=climb;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climb.setClaw(CLAW_ENUM.OPEN);
  }
}
