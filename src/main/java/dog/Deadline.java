package dog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate by;

    /**
     * Constructor for Deadline task.
     * @param description The task description.
     * @param by The deadline date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        String base = "D | " + getDoneStatus() + " | " + description + " | " + by;
        if (getTag() != null && !getTag().isEmpty()) {
            base += " | " + getTag();
        }
        return base;
    }
}
