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

    public Command stowManipulator() {
        return Commands.parallel(
                fourBar.stow(),
                endEffector.stow()).withName("Stow Manipulator");
    }

    // ========= ALGAE COMMANDS =========
    public Command intakeAlgae() {
        return Commands.parallel(
                fourBar.intakeAlgae(),
                endEffector.intakeAlgae()).withName("Intake");
    }

    public Command prepOuttakeAlgae() {
        return Commands.parallel(
                fourBar.outtakeAlgae(),
                endEffector.prepOuttakeAlgae()).withName("Spit");
    }

    public Command spitAlgae() {
        return endEffector.outtakeAlgae().withName("Spit Algae");
    }

    // ========= CORAL COMMANDS =========
    public Command intakeCoral() {
        return Commands.parallel(
                fourBar.intakeCoral(),
                endEffector.intakeCoral()).withName("Intake Coral");
    }

    public Command prepCoralL1() {
        return Commands.parallel(
                fourBar.outtakeCoralL1(),
                endEffector.prepL1()).withName("Outtake Coral L1");
    }

    public Command prepCoralL2() {
        return Commands.parallel(
                fourBar.outtakeCoralL2(),
                endEffector.prepL2()).withName("Outtake Coral L2");
    }

    public Command prepCoralL3() {
        return Commands.parallel(
                fourBar.outtakeCoralL3(),
                endEffector.prepL3()).withName("Outtake Coral L3");
    }

    public Command spitCoral() {
        return endEffector.scoreCoral().withName("Spit Coral");
    }

    // ========= CLIMBER COMMANDS =========
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
