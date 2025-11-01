package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.Util.logging.Loggable;
import frc.lib.Util.logging.Logger;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.ExampleIntake.ExampleIntake;

public class Superstructure implements Loggable {
    public final ExampleIntake intake = new ExampleIntake();
    public final Drivetrain drivetrain = new Drivetrain();

    public Command intake() {
        return intake.intake().withName("Intake");
    }

    public Command spit() {
        return intake.spit().withName("Spit");
    }

    public Command stow() {
        return intake.stow().withName("Stow");
    }

    public Command arcadeDrive(DoubleSupplier forward, DoubleSupplier rotation) {
        return drivetrain.arcadeDrive(forward, rotation);
    }

    public Command tankDrive(DoubleSupplier left, DoubleSupplier right) {
        return drivetrain.tankDrive(left, right);
    }

    @Override
    public void log(String path) {
        Logger.log(path, "Intake", intake);
    }
}
