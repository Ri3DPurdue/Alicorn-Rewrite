package frc.robot.subsystems.EndEffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.component.ComponentSubsystem;
import frc.lib.component.MotorComponent;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;

public class EndEffector extends ComponentSubsystem {
    private final ServoMotorComponent<SparkBaseIO> pivot;
    private final MotorComponent<SparkBaseIO> roller;

    public EndEffector() {
        pivot = registerComponent("Pivot", PivotConstants.getPivot());
        roller = registerComponent("Roller", RollerConstants.getRoller());
    }

    public Command stow() {
        return pivot.applyPositionSetpointCommandWithWait(PivotConstants.stowSetpoint);
    }

    public Command intakeAlgae() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeIntakeSetpoint),
            roller.applySetpointCommand(RollerConstants.algaeOuttakeSetpoint)
        );
    }

    public Command prepOuttakeAlgae() {
        return pivot.applySetpointCommand(PivotConstants.algaeOuttakeSetpoint);
    }

    public Command outtakeAlgae() {
        return roller.applySetpointCommand(RollerConstants.algaeOuttakeSetpoint);
    }

    public Command intakeCoral() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeIntakeSetpoint),
            roller.applySetpointCommand(RollerConstants.algaeOuttakeSetpoint)
        );
    }

    public Command prepL1() {
        return pivot.applyPositionSetpointCommandWithWait(PivotConstants.l1Setpoint);
    }

    public Command prepL2() {
        return pivot.applyPositionSetpointCommandWithWait(PivotConstants.l2Setpoint);
    }

    public Command prepL3() {
        return pivot.applyPositionSetpointCommandWithWait(PivotConstants.l3Setpoint);
    }

    public Command scoreCoral() {
        return roller.applySetpointCommand(RollerConstants.coralOuttakeSetpoint);
    }
}
