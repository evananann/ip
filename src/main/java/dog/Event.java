package dog;

/**
 * Represents a task that occurs within a specified time range.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
    * Constructor for Event task.
    * @param description Task description.
    * @param from Start time/date.
    * @param to End time/date.
    */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + getDoneStatus() + " | " + description + " | " + from + " | " + to;
    }
}
