package dog;

import java.util.Scanner;

/**
 * Handles all interactions with the user, including printing messages
 * and reading commands from standard input.
 */
public class Ui {
    private static final String LINE = "----------------------------------------------------------";
    private final Scanner scanner;

    /**
     * Constructs a new UI using {@link System#in} for input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome banner shown at application startup.
     */
    public void showWelcome() {
        System.out.println("   " + LINE);
        System.out.println("   WOOF! I am DOG!");
        System.out.println("   How are you?");
        System.out.println("   What can I do for you today?");
        System.out.println("   " + LINE);
    }

    /**
     * Prints the goodbye message shown when exiting the application.
     */
    public void showGoodbye() {
        System.out.println("   " + LINE);
        System.out.println("   Bye! Catch you later!");
        System.out.println("   " + LINE);
    }

    /**
     * Prints a horizontal divider line.
     */
    public void showLine() {
        System.out.println("   " + LINE);
    }

    /**
     * Prints an error message surrounded by divider lines.
     *
     * @param message error message to display
     */
    public void showError(String message) {
        System.out.println("   " + LINE + "\n   " + message + "\n   " + LINE);
    }

    /**
     * Prints a normal informational message surrounded by divider lines.
     *
     * @param message message to display
     */
    public void showMessage(String message) {
        System.out.println("   " + LINE + "\n   " + message + "\n   " + LINE);
    }

    /**
     * Prints an error message indicating that persisted tasks could not be loaded.
     */
    public void showLoadingError() {
        showError("WOOF! Could not load tasks. Starting with an empty list.");
    }

    /**
     * Reads the next line of user input.
     *
     * @return the raw command string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
