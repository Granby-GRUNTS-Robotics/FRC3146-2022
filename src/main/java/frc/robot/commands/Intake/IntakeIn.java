// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import frc.robot.Constants.SetpointConstants;
import frc.robot.subsystems.Intake;

/** moves the intake for picking up balls */
public class IntakeIn extends IntakeSpeedBase{
    public IntakeIn(Intake intake){
        super(intake, true);
        percent = SetpointConstants.INTAKE_SPEED;
    }
}