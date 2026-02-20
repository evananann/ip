package dog;

/**
 * Represents a basic to-do task with only a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        String base = "T | " + getDoneStatus() + " | " + description;
        if (getTag() != null && !getTag().isEmpty()) {
            base += " | " + getTag();
        }
        return base;
    }
}
