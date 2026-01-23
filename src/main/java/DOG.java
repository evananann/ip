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
    String input = sc.nextLine(); // takes in user input

    if (input.equals("bye")) {
        break; // break out of loop if user says "bye"
    } else if (input.equals("list")) {
        //enumerates tasks from the list array
        System.out.println("   " + line);
        for (int i = 0; i < number; i++) {
            System.out.println("   " + (i + 1) + "." + tasks[i]);
        }
        System.out.println("   " + line);
    } else if (input.startsWith("mark ")) {
        // marks a task as done
        int idx = Integer.parseInt(input.substring(5)) - 1;
        tasks[idx].markAsDone();
        System.out.println("   " + line + "\n   Alright! I have marked this task as done:\n     " + tasks[idx] + "\n   " + line);
    } else if (input.startsWith("unmark ")) {
        // unmarks a task as not done
        int idx = Integer.parseInt(input.substring(7)) - 1;
        tasks[idx].unmark();
        System.out.println("   " + line + "\n   Okay! I have unmarked this task:\n     " + tasks[idx] + "\n   " + line);
    } else if (input.startsWith("todo ")) {
        tasks[number] = new Todo(input.substring(5));
        number++;
        System.out.println("   " + line + "\n   Got it. I've added this task:\n     " + tasks[number-1] + "\n   Now you have " + number + " tasks in the list.\n   " + line);
    } else if (input.startsWith("deadline ")) {
        String[] parts = input.substring(9).split(" /by ");
        tasks[number] = new Deadline(parts[0], parts[1]);
        number++;
        System.out.println("   " + line + "\n   Got it. I've added this task:\n     " + tasks[number-1] + "\n   Now you have " + number + " tasks in the list.\n   " + line);
    } else if (input.startsWith("event ")) {
        String[] parts = input.substring(6).split(" /from | /to ");
        tasks[number] = new Event(parts[0], parts[1], parts[2]);
        number++;
        System.out.println("   " + line + "\n   Got it. I've added this task:\n     " + tasks[number-1] + "\n   Now you have " + number + " tasks in the list.\n   " + line);
    }
}

        System.out.println("   " + line);
        System.out.println("   Bye! Catch you later!");
        System.out.println("   " + line);
    }
}