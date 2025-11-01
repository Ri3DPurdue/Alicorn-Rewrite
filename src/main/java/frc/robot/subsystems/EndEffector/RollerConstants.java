package frc.robot.subsystems.EndEffector;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.lib.component.MotorComponent;
import frc.lib.hardware.motor.SparkBaseIO;
import frc.lib.hardware.motor.SparkBaseSimIO;
import frc.lib.io.motor.setpoints.*;
import frc.lib.sim.RollerSim;
import frc.lib.sim.SimObject;
import frc.robot.Ports;
import frc.robot.Robot;

public class RollerConstants {
    public static final double gearing = 1.0;
    public static final DCMotor motor = DCMotor.getNeo550(1);

    public static final Voltage coralIntakeVoltage = Units.Volts.of(1.0);
    public static final Voltage coralOuttakeVoltage = Units.Volts.of(-1.0);
    public static final Voltage algaeIntakeVoltage = Units.Volts.of(1.0);
    public static final Voltage algaeOuttakeVoltage = Units.Volts.of(-1.0);

    public static final VoltageSetpoint coralIntakeSetpoint = new VoltageSetpoint(coralIntakeVoltage);
    public static final VoltageSetpoint coralOuttakeSetpoint = new VoltageSetpoint(coralOuttakeVoltage);
    public static final VoltageSetpoint algaeIntakeSetpoint = new VoltageSetpoint(algaeIntakeVoltage);
    public static final VoltageSetpoint algaeOuttakeSetpoint = new VoltageSetpoint(algaeOuttakeVoltage);

    public static final IdleSetpoint idleSetpoint = new IdleSetpoint();

    public static final MotorComponent<SparkBaseIO> getRoller() {
        SparkBaseIO motorIO = getMotorIO();
        motorIO.overrideLoggedUnits(Units.Rotations, Units.RPM, Units.Celsius);
        return new MotorComponent<SparkBaseIO>(motorIO);
    }

    @SuppressWarnings("unchecked")
    public static final SparkBaseIO getMotorIO() {
        return Robot.isReal() 
            ? new SparkBaseIO(
                MotorType.kBrushless, 
                getMainConfig(), 
                Ports.END_EFFECTOR_ROLLERS.id
                )
            : new SparkBaseSimIO(
                getSimObject(),
                motor,
                MotorType.kBrushless, 
                getMainConfig(), 
                Ports.END_EFFECTOR_ROLLERS.id
            );
    }

    public static final SparkBaseConfig getMainConfig() {
        SparkMaxConfig config = SparkBaseIO.getSafeSparkMaxConfig();

        config.encoder
            .positionConversionFactor(gearing)
            .velocityConversionFactor(gearing);
            
        return config;
    }

    public static final SimObject getSimObject() {
        FlywheelSim system = 
            new FlywheelSim(
                LinearSystemId.createFlywheelSystem(
                    motor, 
                    0.01, 
                    gearing),
                motor
            );
        return new RollerSim(system);
    }
}
