package dog;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DOGTest {
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