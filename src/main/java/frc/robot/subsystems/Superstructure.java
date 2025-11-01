package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.Util.logging.Loggable;
import frc.lib.Util.logging.Logger;
import frc.robot.subsystems.FourBarArm.FourBarArm;

public class Superstructure implements Loggable {
    public final FourBarArm fourBar = new FourBarArm();

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

    @Override
    public void log(String path) {
        Logger.log(path, "FourBarArm", fourBar);
    }

}
