// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.ControlConstants.BIG_CLIMB_ENUM;
import frc.robot.Constants.ControlConstants.HOOK_ENUM;
import frc.robot.Constants.PneumaticConstants.ARM_ENUM;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.subsystems.Climb;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StateCommand extends CommandBase {
  /** Creates a new StateCommandBase. */
  private Climb climb;
  private static enum FINISH_ENUM{TIME, POSITION};
  private FINISH_ENUM finish_type;
  private boolean substate_finished;
  BIG_CLIMB_ENUM active_state;
  Timer timer = new Timer();
  double timegoal;

  private BIG_CLIMB_ENUM[] state_list;

  int state_state = 0;

  public StateCommand(Climb climb, BIG_CLIMB_ENUM ratchet_state, BIG_CLIMB_ENUM hook_state, BIG_CLIMB_ENUM arm_state, BIG_CLIMB_ENUM claw_state) {
    this.climb = climb;
    setStateList(new BIG_CLIMB_ENUM[]{ratchet_state, arm_state, claw_state, hook_state});
    addRequirements(climb);
    // Add your commands in the ) call, e.g.
    // new FooCommand(), new BarCommand())
  }


  public StateCommand(Climb climb, BIG_CLIMB_ENUM qwikmove) {
    this.climb = climb;
    setStateList(new BIG_CLIMB_ENUM[]{qwikmove});
    addRequirements(climb);
    //ratchet arm claw hook
  }

  public StateCommand(Climb climb) {
    this.climb = climb;//ratchet arm claw hook
  }

  public void setStateList(BIG_CLIMB_ENUM[] state_list){
    this.state_list = state_list;
  }

  public void setArm(ARM_ENUM arm_state){
    if (climb.getArmState() == arm_state){
      resetAndSetGoal(0);
    }else if (arm_state == ARM_ENUM.HORIZONTAL){
      resetAndSetGoal(ControlConstants.ARM_PISTON_HORIZONTAL_TIME);
    }else resetAndSetGoal(ControlConstants.ARM_PISTON_VERTICAL_TIME);
    finish_type = FINISH_ENUM.TIME;
    climb.setArm(arm_state);
  }

  public void setClaw(CLAW_ENUM claw_state){
    if (climb.getClawState() == claw_state){
      resetAndSetGoal(0);
    }else resetAndSetGoal(ControlConstants.CLAW_PISTON_TIME);
    finish_type = FINISH_ENUM.TIME;
    climb.setClaw(claw_state);
  }

  public void setRatchet(RATCHET_ENUM ratchet_state){
    if (climb.getRatchetState() == ratchet_state){
      resetAndSetGoal(0);
    }else resetAndSetGoal(ControlConstants.RATCHET_PISTON_TIME);
    finish_type = FINISH_ENUM.TIME;
    climb.setRatchet(ratchet_state);
  }

  public void setHook(HOOK_ENUM hook_state){
    if (climb.getHookState() != hook_state){
      climb.setHook(hook_state);
      finish_type = FINISH_ENUM.POSITION;
    }else substate_finished = true;
  }

  private void resetAndSetGoal(double timegoal){
    timer.reset();
    if(timegoal != 0){
      this.timegoal = timegoal;
    }else {
      substate_finished = true;
      timer.stop();
    }
  }

  public void activateState(BIG_CLIMB_ENUM state){
    active_state = state;
    SmartDashboard.putString("Active State", active_state.toString());
    substate_finished = false;
    switch (state) {
        case PULLWITHPNEUMATICS:
      break;
        case HOOK_EXTENDED:
        setHook(HOOK_ENUM.EXTENDED);
      break;
        case HOOK_RESTING:
        setHook(HOOK_ENUM.RESTING);
      break;
        case HOOK_RETRACTED:
        setHook(HOOK_ENUM.RETRACTED);
      break;
        case HOOK_MIDDLE:
        setHook(HOOK_ENUM.MIDDLE);
      break;
        case HOOK_CAPTURING:
        setHook(HOOK_ENUM.CAPTURING);
      break;
        case ARM_HORIZONTAL:
        setArm(ARM_ENUM.HORIZONTAL);
      break;
        case ARM_VERTICAL:
        setArm(ARM_ENUM.VERTICAL);
      break;
        case ARM_FLOAT:
        setArm(ARM_ENUM.FLOAT);
      break;
        case ARM_BOTH:
        setArm(ARM_ENUM.BOTH);
      break;
        case CLAW_OPEN:
        setClaw(CLAW_ENUM.OPEN);
      break;
        case CLAW_CLOSED:
        setClaw(CLAW_ENUM.CLOSED);
      break;
        case RATCHET_RATCHETING:
        setRatchet(RATCHET_ENUM.RATCHETING);
      break;
        case RACHET_FREE:
        setRatchet(RATCHET_ENUM.FREE);
      break;
      default:
        break;
    }
  }

  @Override
  public void initialize() {
    state_state = 0;
    timer.start();
  }

  @Override
  public void execute() {

    if (state_list.length > state_state && state_list[state_state] != null){
      activateState(state_list[state_state]);
    }

    if(substate_finished){
      climb.clearMotionProfile();
      try {
        climb.setVoltage(0);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      state_state++;
    }else{
      switch (finish_type) {
        case TIME:
          substate_finished = timegoal > timer.get();
          break;
        case POSITION:
          substate_finished = climb.isAtPosition() && climb.isTrapezoidOver();
          break;
        default:
          break;
      }
    }
    if (active_state == BIG_CLIMB_ENUM.HOOK_CAPTURING || active_state == BIG_CLIMB_ENUM.HOOK_RETRACTED || active_state == BIG_CLIMB_ENUM.PULLWITHPNEUMATICS){
      try {
        climb.switchPID();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (active_state == BIG_CLIMB_ENUM.PULLWITHPNEUMATICS && climb.isHooked()){
      climb.setArm(ARM_ENUM.HORIZONTAL);
    }
  }

  @Override
  public boolean isFinished() {
      return state_list.length == state_state;
  }
}
