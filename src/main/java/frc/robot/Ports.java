package frc.robot;

public enum Ports {
    DRIVETRAIN_LEFT_MAIN(1, "rio"),
    DRIVETRAIN_LEFT_FOLLOWER(2, "rio"),
    DRIVETRAIN_RIGHT_MAIN(3, "rio"),
    DRIVETRAIN_RIGHT_FOLLOWER(4, "rio"),
    END_EFFECTOR_PIVOT(5, "rio"),
    END_EFFECTOR_ROLLERS(6, "rio"),
    FOUR_BAR_ARM_MAIN(7, "rio"),
    CLIMBER_MOTOR(8, "rio");

    public final int id;
    public final String bus;

    private Ports(int id, String bus) {
        this.id = id;
        this.bus = bus;
    }
}
