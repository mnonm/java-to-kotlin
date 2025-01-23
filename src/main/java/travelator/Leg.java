package travelator;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Leg {
    private final String description;
    private final ZonedDateTime plannedStart;
    private final ZonedDateTime plannedEnd;

    public Leg(String description, ZonedDateTime plannedStart, ZonedDateTime plannedEnd) {
        this.description = description;
        this.plannedStart = plannedStart;
        this.plannedEnd = plannedEnd;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getPlannedStart() {
        return plannedStart;
    }

    public ZonedDateTime getPlannedEnd() {
        return plannedEnd;
    }

    public Duration getPlannedDuration() {
        return Duration.between(plannedStart, plannedEnd);
    }
}