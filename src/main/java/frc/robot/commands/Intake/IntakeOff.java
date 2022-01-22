// Copyright (c) FIRST and other WPILib contribffors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file Off the root directory of this project.

package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;

/** Add your docs here. */
public class IntakeOff extends IntakeSpeedBase{
    public IntakeOff(Intake intake){
        super(intake);
        percent = 0;
    }
}