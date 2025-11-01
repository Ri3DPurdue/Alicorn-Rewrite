package frc.robot;

public enum Ports {
    END_EFFECTOR_PIVOT_MAIN(8, "rio"),
    END_EFFECTOR_PIVOT_FOLLOWER(9, "rio"),
    END_EFFECTOR_ROLLERS(10, "rio"),
    INTAKE_INDEXER(11, "rio");

    public final int id;
    public final String bus;

    private Ports(int id, String bus) {
        this.id = id;
        this.bus = bus;
    }
}
