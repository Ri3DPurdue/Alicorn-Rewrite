package frc.robot.subsystems.Climber;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Voltage;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.io.motor.setpoints.VoltageSetpoint;
import frc.robot.Ports;

public class ClimberConstants {
    public static final Voltage pullVoltage = Units.Volts.of(-10.0);
    public static final Voltage driveUpVoltage = Units.Volts.of(6.0);
    public static final Voltage driveDownVoltage = Units.Volts.of(-4.0);

    public static final VoltageSetpoint pullSetpoint = new VoltageSetpoint(pullVoltage);
    public static final VoltageSetpoint driveUpSetpoint = new VoltageSetpoint(driveUpVoltage);
    public static final VoltageSetpoint driveDownSetpoint = new VoltageSetpoint(driveDownVoltage);

    @SuppressWarnings("unchecked")
    public static SparkBaseIO getMotorIO() {
        return new SparkBaseIO(
            MotorType.kBrushed,
            getMainConfig(),
            Ports.CLIMBER_MOTOR.id
        );
    }

    private static SparkBaseConfig getMainConfig() {
        SparkMaxConfig config = SparkBaseIO.getSafeSparkMaxConfig();

        return config;
    }
}
