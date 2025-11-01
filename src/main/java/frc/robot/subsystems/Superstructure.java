package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.Util.logging.Loggable;
import frc.lib.Util.logging.Logger;
import frc.robot.subsystems.Climber.Climber;
import frc.robot.subsystems.ExampleIntake.ExampleIntake;

public class Superstructure implements Loggable {
    public final ExampleIntake intake = new ExampleIntake();
    public final Climber climber = new Climber();

    public Command intake() {
        return intake.intake().withName("Intake");
    }

    public Command spit() {
        return intake.spit().withName("Spit");
    }

    public Command stow() {
        return intake.stow().withName("Stow");
    }

    public Command stowClimber() {
        return climber.stow().withName("Stow");
    }

    public Command raiseClimber() {
        return climber.raised().withName("Raise");
    }

    public Command liftRobot() {
        return climber.pull().withName("Lift");
    }

    @Override
    public void log(String path) {
        Logger.log(path, "Intake", intake);
    }
}
