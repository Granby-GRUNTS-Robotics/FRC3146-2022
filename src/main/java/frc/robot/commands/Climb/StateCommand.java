// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.SetpointConstants;
import frc.robot.Constants.ControlConstants.BIG_CLIMB_ENUM;
import frc.robot.Constants.ControlConstants.HOOK_ENUM;
import frc.robot.Constants.PneumaticConstants.ARM_ENUM;
import frc.robot.Constants.PneumaticConstants.CLAW_ENUM;
import frc.robot.Constants.PneumaticConstants.RATCHET_ENUM;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;

/**moves climb to given BIG_CLIMB_ENUM state */
public class StateCommand extends CommandBase {
  /** Creates a new StateCommandBase. */
  private Climb climb;
  private static enum FINISH_ENUM{TIME, POSITION, SKIP, TIMEOUT, BAR_SWITCH, RATCHET_SWITCH};
  private FINISH_ENUM finish_type = FINISH_ENUM.TIME;
  private boolean substate_finished;
  BIG_CLIMB_ENUM active_state;
  Timer timer = new Timer();
  double timegoal;

  private BIG_CLIMB_ENUM[] state_list = {BIG_CLIMB_ENUM.RATCHET_RATCHETING};

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
    addRequirements(climb);
  }

  public void setStateList(BIG_CLIMB_ENUM[] state_list){
    this.state_list = state_list;
  }

  public void setArm(ARM_ENUM arm_state){
    if (climb.getArmState() == arm_state){
      skipify();
    }else if (arm_state == ARM_ENUM.HORIZONTAL){
      timeGoalify(ControlConstants.ARM_PISTON_HORIZONTAL_TIME);
    }else timeGoalify(ControlConstants.ARM_PISTON_VERTICAL_TIME);

    climb.setArm(arm_state);
  }

  public void setClaw(CLAW_ENUM claw_state){
    if (climb.getClawState() == claw_state){
      skipify();
    }else {timeGoalify(ControlConstants.CLAW_PISTON_TIME);}
    climb.setClaw(claw_state);
  }

  public void setRatchet(RATCHET_ENUM ratchet_state){
    if (climb.getRatchetState() == ratchet_state){
      skipify();
    }else {timeGoalify(ControlConstants.RATCHET_PISTON_TIME);}
    climb.setRatchet(ratchet_state);
  }

  public void setHook(HOOK_ENUM hook_state){
    climb.setHook(hook_state);
      positionGoalify();
  }

  private void timeGoalify(double timegoal){
    finish_type = FINISH_ENUM.TIME;
    timer.reset();
    this.timegoal = timegoal;
  }

  private void positionGoalify(){
    finish_type = FINISH_ENUM.POSITION;
  }

  private void skipify(){
    finish_type = FINISH_ENUM.SKIP;
  }

  public void activateState(BIG_CLIMB_ENUM state){
    active_state = state;
    switch (state) {
        case FIRST:
        setHook(HOOK_ENUM.FIRST);
      break; case PIDZERO: climb.switchPID(0); timeGoalify(0.1); break; case PIDONE: climb.switchPID(1); skipify(); break;
        case WAIT:
        timeGoalify(0.3);
      break;
        case PULLWITHPNEUMATICS:
        climb.setArm(ARM_ENUM.FLOAT);
        setHook(HOOK_ENUM.CAPTURING);
        timeOutify(5);
      break;
        case HOOK_EXTENDED:
        setHook(HOOK_ENUM.EXTENDED);
      break;
        case HOOK_OFF_PREV:
        climb.setArm(ARM_ENUM.FLOAT);
        setHook(HOOK_ENUM.OFF_PREVIOUS);
      break;
        case HOOK_RESTING:
        setHook(HOOK_ENUM.RESTING);
      break;
        case HOOK_RETRACTED:
        setHook(HOOK_ENUM.RETRACTED);
      break;
        case HOOK_SWING_UP:
        setHook(HOOK_ENUM.EXTENDED);
      break;
        case HOOK_CAPTURING:
        setHook(HOOK_ENUM.CAPTURING);
        timeOutify(3);
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
        climb.setRatchet(RATCHET_ENUM.FREE);
        timeGoalify(0.3);
        ratchetGoalify();
        try {
          climb.setClimbVelocity(-3);
        } catch (Exception e) {
          //TODO: handle exception
        }
      break;
      case LAST:
        setHook(HOOK_ENUM.OFF_PREVIOUS);
        timeOutify(3);
      break;
      case PULL_UNTIL_SWITCH:
        climb.setArm(ARM_ENUM.FLOAT);
        try {
          climb.setClimbPercent(-1.0);
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        barGoalify();
      break;
      default:
        break;
    }
  }

  private void barGoalify() {
    finish_type = FINISH_ENUM.BAR_SWITCH;
  }

  private void ratchetGoalify() {
    finish_type = FINISH_ENUM.RATCHET_SWITCH;
  }


  private void timeOutify(double timegoal) {
    timeGoalify(timegoal);
    finish_type = FINISH_ENUM.TIMEOUT;
  }


  private void incrementState(){
    state_state++;
  }

  @Override
  public void initialize() {
    state_state = 0;
    timer.start();
    substate_finished = true;
    estop = false;
  }

  private boolean passedTimeGoal(){
    return timer.get() > timegoal;
  }

  @Override
  public void execute() {

    if (state_list.length > state_state && substate_finished){
      activateState(state_list[state_state]);
      substate_finished = false;
    }
      
    switch (finish_type) {
      case TIME:
        if (passedTimeGoal()) substate_finished = true ;
        break;
      case POSITION:
        if (climb.isAtPosition()) substate_finished = true; 
        break;
      case SKIP:
        substate_finished = true;
      case TIMEOUT:
        if (climb.isAtPosition() || passedTimeGoal()) substate_finished = true;
        break;
      case BAR_SWITCH:
        if (climb.getBarSwitch()) {
          substate_finished = true;
          try {
            climb.setClimbPercent(0);
          } catch (Exception e) {
            //TODO: handle exception
          }
        }
        break;
      case RATCHET_SWITCH:
        if (climb.getRatchetSwitch() ) {
          substate_finished = true;
          try {
            climb.setClimbPercent(0);
          } catch (Exception e) {
            //TODO: handle exception
          }
        }
        if(passedTimeGoal()){
          substate_finished = true;
          estop = true;
        }
        break;
      default:
        break;
    }
    
    if (active_state == BIG_CLIMB_ENUM.HOOK_CAPTURING || active_state == BIG_CLIMB_ENUM.PULL_UNTIL_SWITCH || active_state == BIG_CLIMB_ENUM.PULLWITHPNEUMATICS){
      if (active_state == BIG_CLIMB_ENUM.PULL_UNTIL_SWITCH){
        if (climb.getPosition() > 15.0){
          climb.setClaw(CLAW_ENUM.OPEN);
          climb.setArm(ARM_ENUM.VERTICAL);
        }
      }
      if (climb.getBarSwitch()) {
        try {
          climb.setClimbPercent(0);
          climb.clearMotionProfile();
        } catch (Exception e) {
          //TODO: handle exception
        }
      }
    }
    if (active_state == BIG_CLIMB_ENUM.HOOK_SWING_UP){
      if (climb.getPosition() > SetpointConstants.HOOK_MIDDLE){
        climb.setArm(ARM_ENUM.HORIZONTAL);
      }
    }

    if (substate_finished){
      incrementState();

      climb.clearMotionProfile();
      try {
        climb.setVoltage(0);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    SmartDashboard.putString("Active State", active_state.toString());
    SmartDashboard.putNumber("state_state", state_state);
    SmartDashboard.putNumber("timer value", timer.get());
    SmartDashboard.putNumber("Timer goal", timegoal);
    SmartDashboard.putBoolean("Finished state state", substate_finished);
  }

  @Override
  public boolean isFinished() {
      return state_list.length == state_state || estop;
  }
  boolean estop = false;
  @Override
  public void end(boolean interrupted) {
    climb.clearMotionProfile();
    try {
      climb.setVoltage(0);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    substate_finished = false;
  }
}
