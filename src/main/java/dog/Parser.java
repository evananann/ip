package dog;
import java.time.LocalDate;

/**
 * Provides helper methods for interpreting and validating user commands.
 */
public class Parser {

    public static boolean isBye(String input) {
        return input != null && input.trim().equals("bye");
    }

    public static boolean isList(String input) {
        return input != null && input.trim().equals("list");
    }

    public static boolean isMark(String input) {
        return input != null && input.startsWith("mark ");
    }

    public static boolean isUnmark(String input) {
        return input != null && input.startsWith("unmark ");
    }

    public static boolean isDelete(String input) {
        return input != null && input.startsWith("delete ");
    }

    public static boolean isTodo(String input) {
        return input != null && input.startsWith("todo ");
    }

    public static boolean isDeadline(String input) {
        return input != null && input.startsWith("deadline ");
    }

    public static boolean isEvent(String input) {
        return input != null && input.startsWith("event ");
    }

    /**
     * Returns the 0-based index for mark/unmark/delete commands.
     */
    public static int getIndex(String input, String prefix) throws DogException {
        String rest = input.substring(prefix.length()).trim();
        try {
            int oneBased = Integer.parseInt(rest);
            if (oneBased < 1) {
                throw new DogException("WOOF! Please provide a valid task number.");
            }
            return oneBased - 1;
        } catch (NumberFormatException e) {
            throw new DogException("WOOF! Please provide a valid task number.");
        }
    }

    public static int getMarkIndex(String input) throws DogException {
        return getIndex(input, "mark ");
    }

    public static int getUnmarkIndex(String input) throws DogException {
        return getIndex(input, "unmark ");
    }

    public static int getDeleteIndex(String input) throws DogException {
        return getIndex(input, "delete ");
    }

    public static String getTodoDescription(String input) throws DogException {
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) {
            throw new DogException("WOOF! Description of a todo cannot be empty.");
        }
        return desc;
    }

    public static String[] getDeadlineParts(String input) throws DogException {
        if (!input.contains(" /by ")) {
            throw new DogException("WOOF! Deadlines must include '/by'.");
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DogException("WOOF! Use date as yyyy-mm-dd format please!");
        }
        try {
            LocalDate.parse(parts[1].trim());
        } catch (Exception e) {
            throw new DogException("WOOF! Use date as yyyy-mm-dd format please!");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    public static String[] getEventParts(String input) throws DogException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new DogException("WOOF! Events must include '/from' and '/to'.");
        }
        String[] toParts = input.substring(6).split(" /to ", 2);
        String[] fromParts = toParts[0].split(" /from ", 2);
        if (fromParts.length < 2 || toParts.length < 2) {
            throw new DogException("WOOF! Events must include '/from' and '/to'.");
        }
        return new String[]{fromParts[0].trim(), fromParts[1].trim(), toParts[1].trim()};
    }
}
