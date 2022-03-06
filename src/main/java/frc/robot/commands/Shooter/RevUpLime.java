// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;

/** rev up based on the distance to the goal. Calculated using the angle of the goal on the limelight */
public class RevUpLime extends RevUpBase{
    private LimeLight limeLight;
    public RevUpLime(Shooter shooter, LimeLight limeLight){
        super(shooter);
        this.limeLight = limeLight;
        addRequirements(limeLight);
    }
    @Override
    public void initialize() {
        speed = limeLight.calculateFlyWheelSpeed(limeLight.getDistanceFromAngle());
        super.initialize();
    }
}
