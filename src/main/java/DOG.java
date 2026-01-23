import java.util.Scanner;

public class DOG {
    public static void main(String[] args) {
        String line = "----------------------------------------------------------";
        Task[] tasks = new Task[100]; //array to store tasks specified by user
        int number = 0; //counter to track number of tasks
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
            System.out.println("   " + line + "\n   Nice! I've marked this as done:\n     " + tasks[idx] + "\n   " + line);
        } else if (input.startsWith("todo ")) {
            if (input.substring(5).trim().isEmpty()) {
                throw new DogException("WOOF! The description of a todo cannot be empty.");
            }
            tasks[number++] = new Todo(input.substring(5));
            System.out.println("   " + line + "\n   Added: " + tasks[number-1] + "\n   " + line);
        } else if (input.startsWith("deadline ")) {
            // Add error handling for missing /by
            if (!input.contains(" /by ")) {
                throw new DogException("WOOF! Deadlines must include '/by'.");
            }
            String[] parts = input.substring(9).split(" /by ");
            tasks[number++] = new Deadline(parts[0], parts[1]);
            System.out.println("   " + line + "\n   Added: " + tasks[number-1] + "\n   " + line);
        } else {
            // Handle unknown commands
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