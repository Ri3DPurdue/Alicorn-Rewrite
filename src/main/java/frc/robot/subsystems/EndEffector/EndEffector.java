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
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.stowSetpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );
    }

    public Command intakeAlgae() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeIntakeSetpoint),
            roller.applySetpointCommand(RollerConstants.algaeOuttakeSetpoint)
        );
    }

    public Command prepOuttakeAlgae() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeOuttakeSetpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );
    }

    public Command outtakeAlgae() {
        return command(
            roller.applySetpointCommand(RollerConstants.algaeOuttakeSetpoint)
        );
    }

    public Command intakeCoral() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeIntakeSetpoint),
            roller.applySetpointCommand(RollerConstants.algaeOuttakeSetpoint)
        );
    }

    public Command prepL1() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.l1Setpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );

    }

    public Command prepL2() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.l2Setpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );
    }

    public Command prepL3() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.l3Setpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );
    }

    public Command scoreCoral() {
        return command(
            roller.applySetpointCommand(RollerConstants.coralOuttakeSetpoint)
        );
    }
}
