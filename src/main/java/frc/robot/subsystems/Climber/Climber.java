package frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.component.ComponentSubsystem;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.io.motor.setpoints.IdleSetpoint;

public class Climber extends ComponentSubsystem {
    private final ServoMotorComponent<SparkBaseIO> climber;

    public Climber() {
        climber = registerComponent("Climber", new ServoMotorComponent<>(
            ClimberConstants.getMotorIO(),
            ClimberConstants.converter.toAngle(ClimberConstants.positionEpsilon),
            ClimberConstants.minPosition
        ));
    }

    public Command stow() {
        return command(
            climber.applySetpointCommand(ClimberConstants.stowSetpoint)
        );
    }

    public Command raised() {
        return command(
            climber.applySetpointCommand(ClimberConstants.raisedSetpoint)
        );
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
