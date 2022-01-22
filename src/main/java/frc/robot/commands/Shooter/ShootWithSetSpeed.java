// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/** Add your docs here. */
public class ShootWithSetSpeed extends ShootBase{
    public ShootWithSetSpeed(Magazine magazine, Shooter shooter, double speed){
        super(magazine, shooter);
        speedBase = new RevUpSet(shooter, speed);
    }
}
