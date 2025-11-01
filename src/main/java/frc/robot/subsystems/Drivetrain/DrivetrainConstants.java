package frc.robot.subsystems.Drivetrain;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.Pair;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.robot.Ports;

public class DrivetrainConstants {
    @SuppressWarnings({ "unchecked" })
    public static SparkBaseIO getLeftMotorIO() {
        return new SparkBaseIO(
            MotorType.kBrushless,
            getMotorConfig(),
            Ports.DRIVETRAIN_LEFT_MAIN.id,
            new Pair<>(Ports.DRIVETRAIN_LEFT_FOLLOWER.id, false)
        );
    }

    @SuppressWarnings({ "unchecked" })
    public static SparkBaseIO getRightMotorIO() {
        return new SparkBaseIO(
            MotorType.kBrushless,
            getMotorConfig(),
            Ports.DRIVETRAIN_RIGHT_MAIN.id,
            new Pair<>(Ports.DRIVETRAIN_RIGHT_FOLLOWER.id, false)
        );
    }

    public static SparkBaseConfig getMotorConfig() {
        SparkMaxConfig config = new SparkMaxConfig();

        config.smartCurrentLimit(40);

        return config;
    }
}
