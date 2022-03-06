// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;

/** turn to an angle given by the limelight's X coordinate as of right now only takes the initial position of the limelight, 
 * but it might be worth either adding in multiple uses (each getting narrower in than the last)
 * or constant uploading of the X coordinate, but that might introduce some phase lag (supposedly the limelight updates values pretty quickly but I don't believe it) 
*/
public class LimeTurn extends DriveToAngle {
    private LimeLight limeLight;
    public LimeTurn(Drivetrain drivetrain, LimeLight limeLight) {
        super(drivetrain, 0);
        this.limeLight = limeLight;
        addRequirements(limeLight);    
    }
    @Override
    public void initialize() {
        angle = limeLight.getX();
        super.initialize();
    }
}
