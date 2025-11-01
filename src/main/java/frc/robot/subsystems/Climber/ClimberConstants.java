package frc.robot.subsystems.Climber;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Voltage;
import frc.lib.Util.UnitsUtil.DistanceAngleConverter;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.io.motor.setpoints.PositionSetpoint;
import frc.lib.io.motor.setpoints.VoltageSetpoint;
import frc.robot.Ports;

public class ClimberConstants {
    public static final Distance pulleyRadius = Units.Inches.of(2.0);
    public static final DistanceAngleConverter converter = new DistanceAngleConverter(pulleyRadius);

    public static final Distance positionEpsilon = Units.Inches.of(0.5);

    public static final Angle stowPosition = converter.toAngle(Units.Feet.of(0.0));
    public static final Angle raisedPosition = converter.toAngle(Units.Feet.of(2.0));
    public static final Voltage pullVoltage = Units.Volts.of(12.0);

    public static final Voltage driveUpVoltage = Units.Volts.of(2.0);
    public static final Voltage driveDownVoltage = Units.Volts.of(-2.0);

    public static final PositionSetpoint stowSetpoint = new PositionSetpoint(stowPosition);
    public static final PositionSetpoint raisedSetpoint = new PositionSetpoint(raisedPosition);
    public static final VoltageSetpoint pullSetpoint = new VoltageSetpoint(pullVoltage);

    public static final VoltageSetpoint driveUpSetpoint = new VoltageSetpoint(driveUpVoltage);
    public static final VoltageSetpoint driveDownSetpoint = new VoltageSetpoint(driveDownVoltage);

    @SuppressWarnings("unchecked")
    public static SparkBaseIO getMotorIO() {
        return new SparkBaseIO(
            MotorType.kBrushless,
            getMainConfig(),
            Ports.CLIMBER_MOTOR.id
        );
    }

    private static SparkBaseConfig getMainConfig() {
        SparkMaxConfig config = SparkBaseIO.getSafeSparkMaxConfig();

        return config;
    }
}
