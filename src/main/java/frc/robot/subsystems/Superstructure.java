package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.Util.logging.Loggable;
import frc.lib.Util.logging.Logger;
import frc.robot.subsystems.FourBarArm.FourBarArm;

public class Superstructure implements Loggable {
    public final FourBarArm fourBar = new FourBarArm();

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

    public Command stow() {
        return Commands.parallel(
            fourBar.stow().withName("Stow")
        );
    }

    @Override
    public void log(String path) {
        Logger.log(path, "FourBarArm", fourBar);
    }
}
