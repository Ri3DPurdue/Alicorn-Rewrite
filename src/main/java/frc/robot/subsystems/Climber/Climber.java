package frc.robot.subsystems.Climber;

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.component.ComponentSubsystem;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;

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
}
