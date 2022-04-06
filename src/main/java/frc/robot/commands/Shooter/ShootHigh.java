// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

/** high shot */
public class ShootHigh extends ShootBase {

    public ShootHigh(Magazine magazine, Shooter shooter, Intake intake)  {
        super(magazine, shooter, new RevUpSet(shooter, SetpointConstants.SHOOTER_SPEED_HIGH), intake);
        //TODO Auto-generated constructor stub
    }}
