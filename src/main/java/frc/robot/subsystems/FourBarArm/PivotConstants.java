package frc.robot.subsystems.FourBarArm;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.hardware.motor.SparkBaseSimIO;
import frc.lib.io.motor.setpoints.PositionSetpoint;
import frc.lib.sim.PivotSim;
import frc.lib.sim.SimObject;
import frc.robot.Ports;
import frc.robot.Robot;

public class PivotConstants {
    public static final Angle epsilonThreshold = Units.Degrees.of(5.0);
    public static final double gearing = (100.0 / 1.0) * (26.0 / 16.0);
    public static final DCMotor motor = DCMotor.getNEO(1);

    public static final Angle minAngle = Units.Degrees.of(-69.0);
    public static final Angle maxAngle = Units.Degrees.of(59.5);

    public static final Angle deployAlgaeInAngle = Units.Degrees.of(-20.0);
    public static final Angle deployAlgaeOutAngle = Units.Degrees.of(-20.0);
    public static final Angle deployCoralIntakeAngle = Units.Degrees.of(0.0);
    public static final Angle deployCoralL1Angle = Units.Degrees.of(10.0);
    public static final Angle deployCoralL2Angle = Units.Degrees.of(20.0);
    public static final Angle deployCoralL3Angle = Units.Degrees.of(30.0);
    public static final Angle stowAngle = minAngle;

    // Setpoints
    public static final PositionSetpoint stowSetpoint = new PositionSetpoint(stowAngle);

    public static final PositionSetpoint deployAlgaeInSetpoint = new PositionSetpoint(deployAlgaeInAngle);
    public static final PositionSetpoint deployAlgaeOutSetpoint = new PositionSetpoint(deployAlgaeOutAngle);

    public static final PositionSetpoint deployCoralInSetpoint = new PositionSetpoint(deployCoralIntakeAngle);
    public static final PositionSetpoint deployCoralL1Setpoint = new PositionSetpoint(deployCoralL1Angle);
    public static final PositionSetpoint deployCoralL2Setpoint = new PositionSetpoint(deployCoralL2Angle);
    public static final PositionSetpoint deployCoralL3Setpoint = new PositionSetpoint(deployCoralL3Angle);

    public static final ServoMotorComponent<SparkBaseIO> getPivot() {
        SparkBaseIO io = getMotorIO();
        io.overrideLoggedUnits(Units.Degrees, Units.DegreesPerSecond, Units.Celsius);

        return new ServoMotorComponent<SparkBaseIO>(io, epsilonThreshold, stowAngle);
    }

    @SuppressWarnings("unchecked")
    public static final SparkBaseIO getMotorIO() {
        return Robot.isReal()
                ? new SparkBaseIO(
                        MotorType.kBrushless,
                        getMainConfig(),
                        Ports.FOUR_BAR_ARM_MAIN.id)
                : new SparkBaseSimIO(
                        getSimObject(),
                        motor,
                        MotorType.kBrushless,
                        getMainConfig(),
                        Ports.FOUR_BAR_ARM_MAIN.id);
    }

    public static final SparkBaseConfig getMainConfig() {
        SparkMaxConfig config = SparkBaseIO.getSafeSparkMaxConfig();
        config.closedLoop
                .p(5.0)
                .d(0.0);

        config.smartCurrentLimit(20);
        config.encoder.positionConversionFactor(1.0 / gearing)
                .velocityConversionFactor(1.0 / gearing);
        
        config.softLimit.forwardSoftLimitEnabled(true)
                .forwardSoftLimit(maxAngle.in(Units.Rotations))
                .reverseSoftLimitEnabled(true)
                .reverseSoftLimit(minAngle.in(Units.Rotations));

        config.idleMode(SparkMaxConfig.IdleMode.kCoast);
        return config;
    }

    public static final SimObject getSimObject() {
        SingleJointedArmSim system = new SingleJointedArmSim(
                motor,
                gearing,
                0.01,
                0.46,
                minAngle.in(Units.Radians),
                maxAngle.in(Units.Radians),
                false,
                0.0,
                0.0, 0.0);
        return new PivotSim(system);
    }
}
