package dog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath.replace("/", java.io.File.separator));
    }

    // Loads tasks from the file and returns an empty list if file does not exist or cannot be read from
    public ArrayList<Task> load() throws DogException {
        try {
            if (Files.notExists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return new ArrayList<>();
            }
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line : Files.readAllLines(filePath)) {
                Task t = parseLine(line.trim());
                if (t != null) {
                    tasks.add(t);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DogException("WOOF! Could not load tasks.");
        }
    }

    private Task parseLine(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        String[] p = line.split(" \\| ", 5);
        if (p.length < 3) {
            return null;
        }
        String type = p[0].trim();
        boolean done = "1".equals(p[1].trim());
        String desc = p[2].trim();
        Task t = null;
        if ("T".equals(type)) {
            t = new Todo(desc);
        } else if ("D".equals(type) && p.length >= 4) {
            try {
                t = new Deadline(desc, LocalDate.parse(p[3].trim()));
            } catch (Exception e) {
                return null;
            }
        } else if ("E".equals(type) && p.length >= 5) {
            t = new Event(desc, p[3].trim(), p[4].trim());
        }
        if (t != null && done) {
            t.markAsDone();
        }
        return t;
    }

    // Saves the given task list to the file.
    public void save(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(task.toFileString());
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Could not save tasks to disk!");
        }
    }
}
