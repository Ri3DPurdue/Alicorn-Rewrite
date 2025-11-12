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
        return parallel(
            climber.applySetpointCommand(ClimberConstants.pullSetpoint),
            Commands.idle()
        ).finallyDo(() -> climber.applySetpoint(new IdleSetpoint()));
    }

    public Command driveUp() {
        return parallel(
            climber.applySetpointCommand(ClimberConstants.driveUpSetpoint),
            Commands.idle()
        ).finallyDo(() -> climber.applySetpoint(new IdleSetpoint()));
    }

    public Command driveDown() {
        return parallel(
            climber.applySetpointCommand(ClimberConstants.driveDownSetpoint),
            Commands.idle()
        ).finallyDo(() -> climber.applySetpoint(new IdleSetpoint()));
    }
}
