// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;

/** Add your docs here. */
public class LimeTurn extends DriveToAngle {

    public LimeTurn(Drivetrain drivetrain, LimeLight limeLight) {
        super(drivetrain, 0);
        angle = limeLight.getX();
        addRequirements(limeLight);    
    }}
