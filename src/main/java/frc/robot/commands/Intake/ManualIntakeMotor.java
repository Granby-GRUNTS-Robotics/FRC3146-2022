// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/** Add your docs here. */
public class ManualIntakeMotor extends CommandBase{
    private Intake intake;
    public ManualIntakeMotor(Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }
    @Override
    public void initialize() {
        SmartDashboard.setDefaultNumber("Intake Manual Speed", 0);
    }

    @Override
    public void execute() {
        intake.setIntakePercent(SmartDashboard.getNumber("Intake Manual Speed", 0));
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntakePercent(0);
    }
}
