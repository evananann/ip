import java.util.Scanner;

public class DOG {
    public static void main(String[] args) {
        String line = "----------------------------------------------------------";
        Scanner sc = new Scanner(System.in);

        System.out.println(line);
        System.out.println("WOOF! I am DOG!");
        System.out.println("How are you?");
        System.out.println("What can I do for you today?");
        System.out.println(line);
        
        while (true) {
            String input = sc.nextLine(); // takes in user input

            if (input.equals("bye")) {
                break; // break out of loop if user says "bye"
            }

            // echo input back to user
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println("Bye! Catch you later!");
        System.out.println(line);

    }
}
