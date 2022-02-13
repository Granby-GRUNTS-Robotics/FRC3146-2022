// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;

/** Add your docs here. */
public class RevUpLime extends RevUpBase{
    public RevUpLime(Shooter shooter, LimeLight limeLight){
        super(shooter);
        speed = limeLight.calculateFlyWheelSpeed();
    }
    public RevUpLime(Shooter shooter, LimeLight limeLight, boolean fixed){
        super(shooter);
        speed = limeLight.calculateFlyWheelSpeed() * 60;
        if(fixed){speed = limeLight.fixFlyWheelSpeed(speed);}
    }
}
