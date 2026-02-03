package dog;

// CHECKSTYLE.OFF: CustomImportOrder
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
// CHECKSTYLE.ON: CustomImportOrder

public class DogTest {
    @Test
    public void testTaskMarking() {
        Todo todo = new Todo("read");
        todo.markAsDone();
        assertEquals("[T][X] read", todo.toString());
    }

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("test task");
        assertEquals("[T][ ] test task", todo.toString());
    }
}
