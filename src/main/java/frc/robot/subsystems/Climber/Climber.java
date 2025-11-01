package frc.robot.subsystems.Climber;

import edu.wpi.first.units.Units;
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
            Units.Radians.of(0)
        ));
    }

    public Command stow() {
        Command c = climber.applySetpointCommand(ClimberConstants.stowSetpoint);
        c.addRequirements(this);

        return c;
    }

    public Command raised() {
        Command c = climber.applySetpointCommand(ClimberConstants.raisedSetpoint);
        c.addRequirements(this);

        return c;
    }

    public Command pull() {
        Command c = climber.applySetpointCommand(ClimberConstants.pullSetpoint);
        c.addRequirements(this);

        return c;
    }

    public Command driveUp() {
        return Commands.runEnd(() -> {
            climber.applySetpoint(ClimberConstants.driveUpSetpoint);
        }, () -> {
            climber.applySetpoint(new IdleSetpoint());
        }, this);
    }

    public Command driveDown() {
        return Commands.runEnd(() -> {
            climber.applySetpoint(ClimberConstants.driveDownSetpoint);
        }, () -> {
            climber.applySetpoint(new IdleSetpoint());
        }, this);
    }
}
