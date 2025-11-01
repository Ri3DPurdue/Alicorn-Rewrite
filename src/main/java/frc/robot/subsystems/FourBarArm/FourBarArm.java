package frc.robot.subsystems.FourBarArm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.component.ComponentSubsystem;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;

public class FourBarArm extends ComponentSubsystem {
    private final ServoMotorComponent<SparkBaseIO> pivot;

    public FourBarArm() {
        pivot = registerComponent("Pivot", PivotConstants.getPivot());
    }

    public Command stow() {
        return pivot.applySetpointCommand(PivotConstants.stowSetpoint);
    }

    // ========= ALGAE COMMANDS =========

    public Command intakeAlgae() {
        return pivot.applyPositionSetpointCommandWithWait(PivotConstants.deployAlgaeInSetpoint);
    }

    public Command outtakeAlgae() {
        return pivot.applyPositionSetpointCommandWithWait(PivotConstants.deployAlgaeOutSetpoint);
    }

    // ========= CORAL COMMANDS =========

    public Command intakeCoral() {
        return pivot.applySetpointCommand(PivotConstants.deployCoralInSetpoint);
    }

    public Command outtakeCoralL1() {
        return pivot.applySetpointCommand(PivotConstants.deployCoralL1Setpoint);
    }
}
