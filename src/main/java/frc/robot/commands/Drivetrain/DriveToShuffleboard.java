// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

/** Add your docs here. */
public class DriveToShuffleboard extends DriveToLocation {
    public DriveToShuffleboard(Drivetrain drivetrain){
        super(drivetrain,0);
    }
    @Override
    public void initialize() {
        SmartDashboard.setDefaultNumber("Protected Distance", 0);
        distance = SmartDashboard.getNumber("Protected Distance", 0);
        super.initialize();
    }
}
