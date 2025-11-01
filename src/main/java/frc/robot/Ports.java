package frc.robot;

public enum Ports {
    DRIVETRAIN_LEFT_MAIN(0, "rio"),
    DRIVETRAIN_LEFT_FOLLOWER(1, "rio"),
    DRIVETRAIN_RIGHT_MAIN(2, "rio"),
    DRIVETRAIN_RIGHT_FOLLOWER(3, "rio"),
    END_EFFECTOR_PIVOT_MAIN(8, "rio"),
    END_EFFECTOR_PIVOT_FOLLOWER(9, "rio"),
    END_EFFECTOR_ROLLERS(10, "rio"),
    FOUR_BAR_ARM_MAIN(12, "rio");

    public final int id;
    public final String bus;

    private Ports(int id, String bus) {
        this.id = id;
        this.bus = bus;
    }
}
