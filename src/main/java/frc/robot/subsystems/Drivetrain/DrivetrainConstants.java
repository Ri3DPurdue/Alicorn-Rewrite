package frc.robot.subsystems.Drivetrain;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.Pair;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Voltage;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.robot.Ports;

public class DrivetrainConstants {
    public static final Voltage maxVoltage = Units.Volts.of(8.0);

    @SuppressWarnings({ "unchecked" })
    public static SparkBaseIO getLeftMotorIO() {
        return new SparkBaseIO(
            MotorType.kBrushed,
            getMotorConfig().inverted(false),
            Ports.DRIVETRAIN_LEFT_MAIN.id,
            new Pair<>(Ports.DRIVETRAIN_LEFT_FOLLOWER.id, false)
        );
    }

    @SuppressWarnings({ "unchecked" })
    public static SparkBaseIO getRightMotorIO() {
        return new SparkBaseIO(
            MotorType.kBrushed,
            getMotorConfig().inverted(true),
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
