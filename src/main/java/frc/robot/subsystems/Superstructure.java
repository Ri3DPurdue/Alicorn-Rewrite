package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.Util.logging.Loggable;
import frc.lib.Util.logging.Logger;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Climber.Climber;
import frc.robot.subsystems.EndEffector.EndEffector;
import frc.robot.subsystems.FourBarArm.FourBarArm;

public class Superstructure implements Loggable {
    public final Climber climber = new Climber();
    public final Drivetrain drivetrain = new Drivetrain();
    public final FourBarArm fourBar = new FourBarArm();
    public final EndEffector endEffector = new EndEffector();


    public Command stow() {
        return Commands.parallel(
            fourBar.stow().withName("Stow")
        );
    }

    // ========= ALGAE COMMANDS =========
    public Command intakeAlgae() {
        return Commands.parallel(
            fourBar.intakeAlgae().withName("Intake")
            );
    }

    public Command outtakeAlgae() {
        return Commands.parallel(
            fourBar.outtakeAlgae().withName("Spit")
        );
    }

    // ========= CORAL COMMANDS =========
    public Command intakeCoral() {
        return Commands.parallel(
            fourBar.intakeCoral().withName("Intake Coral")
        );
    }

    public Command outtakeCoralL1() {
        return Commands.parallel(
            fourBar.outtakeCoralL1().withName("Outtake Coral L1")
        );
    }

    public Command outtakeCoralL2() {
        return Commands.parallel(
            fourBar.outtakeCoralL2().withName("Outtake Coral L2")
        );
    }

    public Command outtakeCoralL3() {
        return Commands.parallel(
            fourBar.outtakeCoralL3().withName("Outtake Coral L3")
        );
    }

    // ========= CLIMBER COMMANDS =========
    public Command stowClimber() {
        return climber.stow().withName("Stow");
    }

    public Command raiseClimber() {
        return climber.raised().withName("Raise");
    }

    public Command liftRobot() {
        return climber.pull().withName("Lift");
    }

    public Command driveClimberUp() {
        return climber.driveUp().withName("Drive Up");
    }

    public Command driveClimberDown() {
        return climber.driveDown().withName("Drive Down");
    }

    // ========= DRIVING COMMANDS =========
    public Command arcadeDrive(DoubleSupplier forward, DoubleSupplier rotation) {
        return drivetrain.arcadeDrive(forward, rotation);
    }

    public Command tankDrive(DoubleSupplier left, DoubleSupplier right) {
        return drivetrain.tankDrive(left, right);
    }

    @Override
    public void log(String path) {
        Logger.log(path, "FourBarArm", fourBar);
        Logger.log(path, "EndEffector", endEffector);
    }
}
