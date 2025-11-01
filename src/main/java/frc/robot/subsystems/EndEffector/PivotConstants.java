package frc.robot.subsystems.EndEffector;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.Pair;
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
    public static final double gearing = 1.0;
    public static final DCMotor motor = DCMotor.getNeo550(2);

    public static final Angle minAngle = Units.Radians.of(-10.0);
    public static final Angle maxAngle = Units.Radians.of(110.0);

    public static final Angle stowAngle = Units.Radians.of(110.0);
    public static final Angle algaaeIntakeAngle = Units.Radians.of(110.0);
    public static final Angle algaeOuttakeAngle = Units.Radians.of(110.0);
    public static final Angle l1Angle = Units.Radians.of(110.0);
    public static final Angle l2Angle = Units.Radians.of(110.0);
    public static final Angle l3Angle = Units.Radians.of(110.0);
    public static final Angle coralIntakeAngle = Units.Radians.of(110.0);


    public static final Angle unjamAngle = Units.Radians.of(70.0);

    public static final PositionSetpoint stowSetpoint = new PositionSetpoint(stowAngle);
    public static final PositionSetpoint algaeIntakeSetpoint = new PositionSetpoint(algaaeIntakeAngle);
    public static final PositionSetpoint algaeOuttakeSetpoint = new PositionSetpoint(algaeOuttakeAngle);
    public static final PositionSetpoint l1Setpoint = new PositionSetpoint(l1Angle);
    public static final PositionSetpoint l2Setpoint = new PositionSetpoint(l2Angle);
    public static final PositionSetpoint l3Setpoint = new PositionSetpoint(l3Angle);
    public static final PositionSetpoint coralIntakeSetpoint = new PositionSetpoint(coralIntakeAngle);



    public static final ServoMotorComponent<SparkBaseIO> getPivot() {
        return new ServoMotorComponent<SparkBaseIO>(getMotorIO(), epsilonThreshold, unjamAngle);
    }

    @SuppressWarnings("unchecked")
    public static final SparkBaseIO getMotorIO() {
        return Robot.isReal() 
            ? new SparkBaseIO(
                MotorType.kBrushless, 
                getMainConfig(), 
                Ports.END_EFFECTOR_PIVOT_MAIN.id, 
                Pair.of(Ports.END_EFFECTOR_PIVOT_FOLLOWER.id, false)
                )
            : new SparkBaseSimIO(
                getSimObject(),
                motor,
                MotorType.kBrushless, 
                getMainConfig(), 
                Ports.END_EFFECTOR_PIVOT_MAIN.id, 
                Pair.of(Ports.END_EFFECTOR_PIVOT_FOLLOWER.id, false)
            );
    }

    public static final SparkBaseConfig getMainConfig() {
        SparkMaxConfig config = new SparkMaxConfig();
        config.closedLoop
            .p(0.15)
            .d(0.15);
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
