import java.util.Scanner;

public class DOG {
    public static void main(String[] args) {
        String line = "----------------------------------------------------------";
        String[] tasks = new String[100]; //array to store tasks specified by user
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
                    System.out.println("   " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("   " + line);
            } else {
                // adds a task to the tasks array
                tasks[number] = input;
                number++;
                System.out.println("   " + line);
                System.out.println("   " + "The task \"" + input + "\" has been added to the list!");
                System.out.println("   " + line);
            }
        }

        System.out.println("   " + line);
        System.out.println("   Bye! Catch you later!");
        System.out.println("   " + line);

    }
}
