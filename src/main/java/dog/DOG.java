package dog;
import java.time.LocalDate;

public class Dog {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            try {
                if (Parser.isBye(input)) {
                    break;
                } else if (Parser.isList(input)) {
                    ui.showLine();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("   " + (i + 1) + "." + tasks.getTask(i));
                    }
                    ui.showLine();
                } else if (Parser.isMark(input)) {
                    int idx = Parser.getMarkIndex(input);
                    tasks.markDone(idx);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Alright! I have marked this task as done:\n     " + tasks.getTask(idx));
                } else if (Parser.isUnmark(input)) {
                    int idx = Parser.getUnmarkIndex(input);
                    tasks.unmark(idx);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay! I have unmarked this task:\n     " + tasks.getTask(idx));
                } else if (Parser.isDelete(input)) {
                    int idx = Parser.getDeleteIndex(input);
                    Task removedTask = tasks.deleteTask(idx);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay. I have removed this task:\n     " + removedTask
                            + "\n   There are now " + tasks.size() + " tasks in the list.");
                } else if (Parser.isTodo(input)) {
                    String desc = Parser.getTodoDescription(input);
                    Task task = new Todo(desc);
                    tasks.addTask(task);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Got it. I've added this task:\n     " + task
                            + "\n   Now you have " + tasks.size() + " tasks in the list.");
                } else if (Parser.isDeadline(input)) {
                    String[] parts = Parser.getDeadlineParts(input);
                    Task task = new Deadline(parts[0], LocalDate.parse(parts[1].trim()));
                    tasks.addTask(task);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Got it. I've added this task:\n     " + task
                            + "\n   Now you have " + tasks.size() + " tasks in the list.");
                } else if (Parser.isEvent(input)) {
                    String[] parts = Parser.getEventParts(input);
                    Task task = new Event(parts[0], parts[1], parts[2]);
                    tasks.addTask(task);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Got it. I've added this task:\n     " + task
                            + "\n   Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DogException("WOOF!!! I'm sorry, but I don't know what that means...");
                }
            } catch (DogException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showError("WOOF! Please provide a valid task number.");
            }
        }
        ui.showGoodbye();
    }
    public static void main(String[] args) {
        new Dog("data/dog.txt").run();
    }
}
