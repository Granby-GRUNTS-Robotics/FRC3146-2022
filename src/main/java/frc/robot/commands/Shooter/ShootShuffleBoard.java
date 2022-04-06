// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/** shoots based on shuffleboard value. great for testing, probably shouldn't be used in competition */
public class ShootShuffleBoard extends ShootBase{
    public ShootShuffleBoard(Magazine magazine, Shooter shooter, Intake intake){
        super(magazine, shooter, new RevUpShuffleboard(shooter, true), intake);
    }
}
