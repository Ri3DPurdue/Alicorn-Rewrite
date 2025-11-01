package frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.Util.logging.Logger;
import frc.lib.component.ComponentSubsystem;
import frc.lib.io.motor.MotorIO;
import frc.lib.io.motor.setpoints.VoltageSetpoint;

public class Drivetrain extends ComponentSubsystem {
    private final DifferentialDrive drive;
    private final MotorIO leftMotor;
    private final MotorIO rightMotor;

    public Drivetrain() {
        leftMotor = DrivetrainConstants.getLeftMotorIO();
        rightMotor = DrivetrainConstants.getRightMotorIO();

        drive = new DifferentialDrive(this::driveLeft, this::driveRight);
    }

    private void driveLeft(double leftSpeed) {
        leftMotor.applySetpoint(new VoltageSetpoint(Units.Volts.of(leftSpeed * 11.0)));
    }

    private void driveRight(double rightSpeed) {
        rightMotor.applySetpoint(new VoltageSetpoint(Units.Volts.of(rightSpeed * 11.0)));
    }

    public Command arcadeDrive(DoubleSupplier forward, DoubleSupplier rotation) {
        return Commands.run(() -> {
            this.drive.arcadeDrive(forward.getAsDouble(), rotation.getAsDouble());
        }, this);
    }

    public Command tankDrive(DoubleSupplier left, DoubleSupplier right) {
        return Commands.run(() -> {
            this.drive.tankDrive(left.getAsDouble(), right.getAsDouble());
        }, this);
    }

    @Override
    public void log(String name) {
        super.log(name);

        Logger.log(name, "Left Motor", leftMotor);
        Logger.log(name, "Right Motor", rightMotor);
    }
}
