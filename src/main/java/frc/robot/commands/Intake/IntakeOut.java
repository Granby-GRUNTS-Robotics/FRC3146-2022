// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Intake;

/** moves the intake for ejecting balls */
public class IntakeOut extends IntakeSpeedBase{
    public IntakeOut(Intake intake){
        super(intake, false);
        percent = -SetpointConstants.INTAKE_SPEED;
    }
}