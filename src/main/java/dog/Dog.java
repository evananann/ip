package dog;

import java.time.LocalDate;

/**
 * Main class for the Dog chatbot.
 */
public class Dog {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the chatbot with a file path for storage.
     */
    public Dog(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DogException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main interaction loop until the user issues the {@code bye} command.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            if (Parser.isBye(input)) {
                break;
            }
            String response = processCommand(input);
            ui.showMessage(response);
        }
        ui.showGoodbye();
    }

    /**
     * Processes a user command and returns the response message.
     *
     * @param input the user input command
     * @return the response message to display
     */
    private String processCommand(String input) {
        try {
            if (Parser.isList(input)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    sb.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
                }
                return sb.toString().trim();

            } else if (Parser.isMark(input)) {
                int idx = Parser.getMarkIndex(input);
                tasks.markDone(idx);
                storage.save(tasks.getTasks());
                return "Alright! I have marked this task as done:\n  " + tasks.getTask(idx);

            } else if (Parser.isUnmark(input)) {
                int idx = Parser.getUnmarkIndex(input);
                tasks.unmark(idx);
                storage.save(tasks.getTasks());
                return "Okay! I have unmarked this task:\n  " + tasks.getTask(idx);

            } else if (Parser.isDelete(input)) {
                int idx = Parser.getDeleteIndex(input);
                Task removedTask = tasks.deleteTask(idx);
                storage.save(tasks.getTasks());
                return "Okay. I have removed this task:\n  " + removedTask
                        + "\nThere are now " + tasks.size() + " tasks in the list.";

            } else if (Parser.isTodo(input)) {
                String desc = Parser.getTodoDescription(input);
                Task task = new Todo(desc);
                tasks.addTask(task);
                storage.save(tasks.getTasks());
                return formatAddTaskResponse(task, tasks.size());

            } else if (Parser.isDeadline(input)) {
                String[] parts = Parser.getDeadlineParts(input);
                Task task = new Deadline(parts[0], LocalDate.parse(parts[1].trim()));
                tasks.addTask(task);
                storage.save(tasks.getTasks());
                return formatAddTaskResponse(task, tasks.size());

            } else if (Parser.isEvent(input)) {
                String[] parts = Parser.getEventParts(input);
                Task task = new Event(parts[0], parts[1], parts[2]);
                tasks.addTask(task);
                storage.save(tasks.getTasks());
                return formatAddTaskResponse(task, tasks.size());

            } else if (Parser.isFind(input)) {
                String keyword = Parser.getFindKeyword(input);
                return formatFindResults(keyword);

            } else if (Parser.isTag(input)) {
                int idx = Parser.getTagIndex(input);
                String label = Parser.getTagLabel(input);
                Task task = tasks.getTask(idx);
                task.setTag(label);
                storage.save(tasks.getTasks());
                return "Okay! I tagged the task:\n  " + task;

            } else {
                throw new DogException("WOOF!!! I'm sorry, but I don't know what that means...");
            }
        } catch (DogException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "WOOF! Please provide a valid task number.";
        }
    }

    /**
     * Processes a user command and returns the response message.
     * Used by GUI components to get responses without side effects on UI.
     *
     * @param input the user input command
     * @return the response message
     */
    public String getResponse(String input) {
        if (Parser.isBye(input)) {
            return "Bye! Catch you later!";
        }
        return processCommand(input);
    }

    private String formatAddTaskResponse(Task task, int newSize) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + newSize + " tasks in the list.";
    }

    private String formatFindResults(String keyword) throws DogException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        int displayIndex = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.toString().contains(keyword)) {
                sb.append(displayIndex).append(". ").append(task).append("\n");
                displayIndex++;
            }
        }
        if (displayIndex == 1) {
            return "No matching tasks found.";
        }
        return sb.toString().trim();
    }

    /**
     * Application entry point.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        new Dog("data/dog.txt").run();
    }
}
