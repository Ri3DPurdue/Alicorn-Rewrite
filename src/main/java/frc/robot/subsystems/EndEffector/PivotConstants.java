package frc.robot.subsystems.EndEffector;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.lib.component.ServoMotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.hardware.motor.SparkBaseSimIO;
import frc.lib.io.motor.setpoints.*;
import frc.lib.sim.PivotSim;
import frc.lib.sim.SimObject;
import frc.robot.Ports;
import frc.robot.Robot;

public class PivotConstants {
    public static final Angle epsilonThreshold = Units.Degrees.of(10.0);
    public static final double gearing = 3.0;
    public static final DCMotor motor = DCMotor.getNeo550(1);

    public static final Angle minAngle = Units.Degrees.of(-26.0);
    public static final Angle maxAngle = Units.Degrees.of(43.0);

    public static final Angle stowAngle = maxAngle;
    public static final Angle algaeIntakeAngle = Units.Degrees.of(-20.0);
    public static final Angle algaeOuttakeAngle = Units.Degrees.of(0.0);
    public static final Angle l1Angle = Units.Degrees.of(30.0);
    public static final Angle l2Angle = Units.Degrees.of(20.0);
    public static final Angle l3Angle = Units.Degrees.of(10.0);
    public static final Angle coralIntakeAngle = Units.Degrees.of(-15.0);


    public static final PositionSetpoint stowSetpoint = new PositionSetpoint(stowAngle);
    public static final PositionSetpoint algaeIntakeSetpoint = new PositionSetpoint(algaeIntakeAngle);
    public static final PositionSetpoint algaeOuttakeSetpoint = new PositionSetpoint(algaeOuttakeAngle);
    public static final PositionSetpoint l1Setpoint = new PositionSetpoint(l1Angle);
    public static final PositionSetpoint l2Setpoint = new PositionSetpoint(l2Angle);
    public static final PositionSetpoint l3Setpoint = new PositionSetpoint(l3Angle);
    public static final PositionSetpoint coralIntakeSetpoint = new PositionSetpoint(coralIntakeAngle);

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
                Ports.END_EFFECTOR_PIVOT_MAIN.id 
                )
            : new SparkBaseSimIO(
                getSimObject(),
                motor,
                MotorType.kBrushless, 
                getMainConfig(), 
                Ports.END_EFFECTOR_PIVOT_MAIN.id 
            );
    }

    public static final SparkBaseConfig getMainConfig() {
        SparkBaseConfig config = SparkBaseIO.getSafeSparkMaxConfig();
        config.closedLoop
            .p(0.1)
            .d(0.1);
        
        config.encoder
            .positionConversionFactor(1 / gearing)
            .velocityConversionFactor(1 / gearing);
        
        config.softLimit.forwardSoftLimitEnabled(true)
                .forwardSoftLimit(maxAngle.in(Units.Rotations))
                .reverseSoftLimitEnabled(true)
                .reverseSoftLimit(minAngle.in(Units.Rotations));

        return config;    
    }

    public static final SimObject getSimObject() {
        SingleJointedArmSim system = 
            new SingleJointedArmSim(
                motor, 
                gearing, 
                0.01, 
                0.2, 
                minAngle.in(Units.Radians), 
                maxAngle.in(Units.Radians), 
                false,
                0.0, 
                0.0, 0.0);
        return new PivotSim(system);
    }
}