package frc.robot.controlBoard;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Superstructure;

public class ControlBoard {
    public static final CommandXboxController driver = new CommandXboxController(0);
    public static void bindControls(Superstructure s) {
        driver.a().onTrue(s.prepCoralL1());
        driver.x().onTrue(s.prepCoralL2());
        driver.y().onTrue(s.prepCoralL3());

        driver.b().onTrue(s.stowManipulator());

        driver.leftTrigger().onTrue(s.intakeCoral());
        driver.rightTrigger().onTrue(s.spitCoral());

        driver.leftBumper().onTrue(s.intakeAlgae());
        driver.rightBumper().onTrue(s.spitAlgae());

        driver.povLeft().onTrue(s.prepOuttakeAlgae());

        driver.povUp().whileTrue(s.driveClimberUp());
        driver.povUp().whileTrue(s.driveClimberDown());
        driver.povRight().whileTrue(s.liftRobot());
    }
}
