package dog;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a one-character status icon indicating whether the task is done.
     *
     * @return {@code "X"} if done, otherwise a single space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // X represents a completed task
    }

    /**
     * Returns the file representation of the done status.
     *
     * @return {@code "1"} if done, otherwise {@code "0"}
     */
    protected String getDoneStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a machine-readable representation of this task for storage.
     *
     * @return encoded task representation suitable for saving to disk
     */
    public abstract String toFileString();
}
