package frc.robot.subsystems.EndEffector;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.component.ComponentSubsystem;
import frc.lib.component.MotorComponent;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;

public class EndEffector extends ComponentSubsystem {
    private final ServoMotorComponent<SparkBaseIO> pivot;
    private final MotorComponent<SparkBaseIO> roller;

    public EndEffector() {
        pivot = registerComponent("Pivot", PivotConstants.getPivot());
        pivot.resetPosition(PivotConstants.minAngle);
        roller = registerComponent("Roller", RollerConstants.getRoller());
    }

    public Command stow() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.stowSetpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );
    }

    // ========= ALGAE COMMANDS =========
    public Command intakeAlgae() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeIntakeSetpoint),
            roller.applySetpointCommand(RollerConstants.algaeIntakeSetpoint)
        );
    }

    public Command prepOuttakeAlgae() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.algaeOuttakeSetpoint),
            roller.applySetpointCommand(RollerConstants.idleSetpoint)
        );
    }

    public Command outtakeAlgae() {
        return Commands.startEnd(
            () -> roller.applySetpoint(RollerConstants.algaeOuttakeSetpoint), 
            () -> roller.applySetpoint(RollerConstants.idleSetpoint), 
            this
        );
    }

    // ======== CORAL COMMANDS =========
    public Command intakeCoral() {
        return parallel(
            pivot.applyPositionSetpointCommandWithWait(PivotConstants.coralIntakeSetpoint),
            roller.applySetpointCommand(RollerConstants.coralIntakeSetpoint)
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
        return Commands.startEnd(
            () -> roller.applySetpoint(RollerConstants.coralOuttakeSetpoint), 
            () -> roller.applySetpoint(RollerConstants.idleSetpoint), 
            this
        );
    }

    public Command idleRoller() {
        return command(roller.applySetpointCommand(RollerConstants.idleSetpoint));
    }

    public Command holdRoller() {
        return command(roller.applySetpointCommand(RollerConstants.holdingSetpoint));
    }
}
