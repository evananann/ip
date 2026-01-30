import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DOG {
    // Data file under the current working directory 
    private static final Path DATA_PATH = Paths.get(System.getProperty("user.dir"), "data", "dog.txt");

    private static int loadTasks(Task[] tasks) {
        try {
            if (Files.notExists(DATA_PATH)) {
                Files.createDirectories(DATA_PATH.getParent());
                Files.createFile(DATA_PATH);
                return 0;
            }
            int n = 0;
            for (String s : Files.readAllLines(DATA_PATH)) {
                if (n >= tasks.length) break;
                Task t = parseLine(s.trim());
                if (t != null) tasks[n++] = t;
            }
            return n;
        } catch (IOException e) {
            return 0;
        }
    }

    private static Task parseLine(String line) {
        if (line == null || line.isEmpty()) return null;
        String[] p = line.split(" \\| ", 5);
        if (p.length < 3) return null;
        String type = p[0].trim();
        boolean done = "1".equals(p[1].trim());
        String desc = p[2].trim();
        Task t = null;
        if ("T".equals(type)) t = new Todo(desc);
        else if ("D".equals(type) && p.length >= 4) t = new Deadline(desc, p[3].trim());
        else if ("E".equals(type) && p.length >= 5) t = new Event(desc, p[3].trim(), p[4].trim());
        if (t != null && done) t.markAsDone();
        return t;
    }

    private static void saveTasks(Task[] tasks, int n) {
        try {
            Files.createDirectories(DATA_PATH.getParent());
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < n; i++) lines.add(tasks[i].toFileString());
            Files.write(DATA_PATH, lines);
        } catch (IOException ignored) {}
    }
    public static void main(String[] args) {
        String line = "----------------------------------------------------------";
        Task[] tasks = new Task[100]; //array to store tasks specified by user
        int number = loadTasks(tasks); //counter to track number of tasks
        Scanner sc = new Scanner(System.in);

        System.out.println("   " + line);
        System.out.println("   WOOF! I am DOG!");
        System.out.println("   How are you?");
        System.out.println("   What can I do for you today?");
        System.out.println("   " + line);
        
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("   " + line);
                    for (int i = 0; i < number; i++) {
                        System.out.println("   " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println("   " + line);
                } else if (input.startsWith("mark ")) {
                    int idx = Integer.parseInt(input.substring(5)) - 1;
                    tasks[idx].markAsDone();
                    saveTasks(tasks, number);
                    System.out.println("   " + line + "\n   Nice! I've marked this as done:\n     " + tasks[idx] + "\n   " + line);
                } else if (input.startsWith("unmark ")) {
                    int idx = Integer.parseInt(input.substring(7)) - 1;
                    tasks[idx].unmark();
                    saveTasks(tasks, number);
                    System.out.println("   " + line + "\n   OK, I've unmarked this task:\n     " + tasks[idx] + "\n   " + line);
                } else if (input.startsWith("delete ")) {
                    int idx = Integer.parseInt(input.substring(7)) - 1;
                    if (idx < 0 || idx >= number) {
                        throw new DogException("WOOF! I can't delete a task that doesn't exist!");
                    }
                    Task removedTask = tasks[idx];
                    // Level-6: Shift remaining tasks to the left
                    for (int i = idx; i < number - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    number--;
                    saveTasks(tasks, number);
                    System.out.println("   " + line + "\n   Okay. I have removed this task:\n     " + removedTask);
                    System.out.println("   There are now " + number + " tasks in the list.\n   " + line);
                } else if (input.startsWith("todo ")) {
                    if (input.substring(5).trim().isEmpty()) {
                        throw new DogException("WOOF! Description of a todo cannot be empty.");
                    }
                    tasks[number++] = new Todo(input.substring(5));
                    saveTasks(tasks, number);
                    System.out.println("   " + line + "\n   Added: " + tasks[number-1] + "\n   " + line);
                } else if (input.startsWith("deadline ")) {
                    if (!input.contains(" /by ")) {
                        throw new DogException("WOOF! Deadlines must include '/by'.");
                    }
                    String[] parts = input.substring(9).split(" /by ");
                    tasks[number++] = new Deadline(parts[0], parts[1]);
                    saveTasks(tasks, number);
                    System.out.println("   " + line + "\n   Added: " + tasks[number-1] + "\n   " + line);
                } else if (input.startsWith("event ")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new DogException("WOOF! Events must include '/from' and '/to'.");
                    }
                    String[] toParts = input.substring(6).split(" /to ");
                    String[] fromParts = toParts[0].split(" /from ");
                    tasks[number++] = new Event(fromParts[0].trim(), fromParts[1].trim(), toParts[1].trim());
                    saveTasks(tasks, number);
                    System.out.println("   " + line + "\n   Added: " + tasks[number-1] + "\n   " + line);
                } else {
                    throw new DogException("WOOF!!! I'm sorry, but I don't know what that means...");
                }
            } catch (DogException e) {
                System.out.println("   " + line + "\n   " + e.getMessage() + "\n   " + line);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("   " + line + "\n   WOOF! Please provide a valid task number.\n   " + line);
            }
        }

        System.out.println("   " + line);
        System.out.println("   Bye! Catch you later!");
        System.out.println("   " + line);
    }
}