// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import frc.robot.Constants.PneumaticConstants.INTAKE_ENUM;
import frc.robot.subsystems.Intake;

/** Intake Down*/
public class MoveIntakeDown extends MoveIntakeBase{
    public MoveIntakeDown(Intake intake){
        super(intake);
        pos = INTAKE_ENUM.DOWN;
    }
}
