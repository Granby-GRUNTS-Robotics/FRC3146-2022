// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

/** Add your docs here. */
public class DriveToAngleShuffleboard extends DriveToAngle {
    public DriveToAngleShuffleboard(Drivetrain drivetrain){
        super(drivetrain, 0);
    }
    @Override
    public void initialize() {
        SmartDashboard.setDefaultNumber("Protected Angle", 0);
        angle = SmartDashboard.getNumber("Protected Angle", 0);
        super.initialize();
    }
}
