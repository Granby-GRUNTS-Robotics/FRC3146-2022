// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.commands.LimeTurnOff;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/** shoot command implementing limelight-based rev-up */
public class ShootLime extends ShootBase{
    public ShootLime(Magazine magazine, Shooter shooter, LimeLight limeLight, Intake intake){
        super(magazine, shooter, new RevUpLime(shooter, limeLight), intake);
        //addCommands(new LimeTurnOff(limeLight));
    }
    public ShootLime(Magazine magazine, Shooter shooter, LimeLight limeLight, Intake intake, boolean auto){
        super(magazine, shooter, new RevUpLime(shooter, limeLight), auto, intake );
        //addCommands(new LimeTurnOff(limeLight));
    }
}
