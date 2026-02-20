package dog;

import java.time.LocalDate;

/**
 * Provides helper methods for interpreting and validating user commands.
 */
public class Parser {
    private static final int TODO_PREFIX_LEN = 5;
    private static final int FIND_PREFIX_LEN = 5;
    private static final int DEADLINE_PREFIX_LEN = 9;
    private static final int EVENT_PREFIX_LEN = 6;

    public static boolean isBye(String input) {
        return input != null && input.trim().equals("bye");
    }

    public static boolean isList(String input) {
        return input != null && input.trim().equals("list");
    }

    public static boolean isMark(String input) {
        return input != null && input.trim().startsWith("mark ");
    }

    public static boolean isUnmark(String input) {
        return input != null && input.trim().startsWith("unmark ");
    }

    public static boolean isDelete(String input) {
        return input != null && input.trim().startsWith("delete ");
    }

    public static boolean isTodo(String input) {
        return input != null && input.trim().startsWith("todo ");
    }

    public static boolean isDeadline(String input) {
        return input != null && input.trim().startsWith("deadline ");
    }

    public static boolean isEvent(String input) {
        return input != null && input.trim().startsWith("event ");
    }

    public static boolean isFind(String input) {
        return input != null && input.trim().startsWith("find ");
    }

    public static boolean isTag(String input) {
        return input != null && input.trim().startsWith("tag ");
    }

    /**
     * Returns the 0-based index for mark/unmark/delete commands.
     */
    public static int getIndex(String input, String prefix) throws DogException {
        assert input != null: "Input should not be null when parsing index";
        assert prefix != null: "Prefix should not be null when parsing index";
        assert input.startsWith(prefix): "getIndex has been called with wrong prefix: " + prefix;
        
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

    public static int getTagIndex(String input) throws DogException {
        return getIndex(input, "tag ");
    }

    /**
     * Returns the tag label (without leading '#') for a tag command like "tag 2 #fun".
     */
    public static String getTagLabel(String input) throws DogException {
        assert input != null: "Input should not be null when parsing tag label";
        String rest = input.substring(4).trim(); // after 'tag '
        String[] parts = rest.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DogException("WOOF! Please provide a tag label after the task number.");
        }
        String label = parts[1].trim();
        if (label.startsWith("#")) {
            label = label.substring(1);
        }
        if (label.isEmpty()) {
            throw new DogException("WOOF! Tag cannot be empty.");
        }
        return label;
    }

    public static String getTodoDescription(String input) throws DogException {
        String desc = input.substring(TODO_PREFIX_LEN).trim();
        if (desc.isEmpty()) {
            throw new DogException("WOOF! Description of a todo cannot be empty...");
        }
        return desc;
    }

    public static String getFindKeyword(String input) throws DogException {
        String keyword = input.substring(FIND_PREFIX_LEN).trim();
        if (keyword.isEmpty()) {
            throw new DogException("WOOF! The keyword for find cannot be empty...");
        }
        return keyword;
    }

    public static String[] getDeadlineParts(String input) throws DogException {
        if (!input.contains(" /by ")) {
            throw new DogException("WOOF! Deadlines must include '/by'.");
        }
        String[] parts = input.substring(DEADLINE_PREFIX_LEN).split(" /by ", 2);
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
        String[] toParts = input.substring(EVENT_PREFIX_LEN).split(" /to ", 2);
        String[] fromParts = toParts[0].split(" /from ", 2);
        if (fromParts.length < 2 || toParts.length < 2) {
            throw new DogException("WOOF! Events must include '/from' and '/to'.");
        }
        return new String[]{fromParts[0].trim(), fromParts[1].trim(), toParts[1].trim()};
    }
}
