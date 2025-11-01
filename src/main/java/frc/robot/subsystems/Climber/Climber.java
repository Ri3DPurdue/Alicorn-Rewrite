package frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.component.ComponentSubsystem;
import frc.lib.component.MotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.io.motor.setpoints.IdleSetpoint;

public class Climber extends ComponentSubsystem {
    private final MotorComponent<SparkBaseIO> climber;

    public Climber() {
        climber = registerComponent("Climber", new MotorComponent<>(
            ClimberConstants.getMotorIO()
        ));
    }

    public Command pull() {
        return command(
            climber.applySetpointCommand(ClimberConstants.pullSetpoint)
        );
    }

    public Command driveUp() {
        return Commands.startEnd(() -> {
            climber.applySetpoint(ClimberConstants.driveUpSetpoint);
        }, () -> {
            climber.applySetpoint(new IdleSetpoint());
        }, this);
    }

    public Command driveDown() {
        return Commands.startEnd(() -> {
            climber.applySetpoint(ClimberConstants.driveDownSetpoint);
        }, () -> {
            climber.applySetpoint(new IdleSetpoint());
        }, this);
    }
}
