package dog;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "----------------------------------------------------------";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("   " + LINE);
        System.out.println("   WOOF! I am DOG!");
        System.out.println("   How are you?");
        System.out.println("   What can I do for you today?");
        System.out.println("   " + LINE);
    }

    public void showGoodbye() {
        System.out.println("   " + LINE);
        System.out.println("   Bye! Catch you later!");
        System.out.println("   " + LINE);
    }

    public void showLine() {
        System.out.println("   " + LINE);
    }

    public void showError(String message) {
        System.out.println("   " + LINE + "\n   " + message + "\n   " + LINE);
    }

    public void showMessage(String message) {
        System.out.println("   " + LINE + "\n   " + message + "\n   " + LINE);
    }

    public void showLoadingError() {
        showError("WOOF! Could not load tasks. Starting with an empty list.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
