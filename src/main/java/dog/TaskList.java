package dog;

import java.util.ArrayList;

/**
 * Holds the list of tasks in memory and provides operations to modify it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list pre-populated with the given tasks.
     *
     * @param initialTasks tasks to initialize the list with
     */
    public TaskList(ArrayList<Task> initialTasks) {
        this.tasks = initialTasks != null ? new ArrayList<>(initialTasks) : new ArrayList<>();
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index zero-based index of the task to delete
     * @return the removed task
     * @throws DogException if the index is out of range
     */
    public Task deleteTask(int index) throws DogException {
        if (index < 0 || index >= tasks.size()) {
            throw new DogException("WOOF! I can't delete a task that doesn't exist!");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index zero-based index
     * @return task at the given index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param index zero-based index of the task
     */
    public void markDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not completed.
     *
     * @param index zero-based index of the task
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return backing {@link ArrayList} of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return number of tasks
     */
    public int size() {
        return tasks.size();
    }
}
