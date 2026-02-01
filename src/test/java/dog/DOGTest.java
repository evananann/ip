package dog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DOGTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("test task");
        assertEquals("[T][ ] test task", todo.toString());
    }
}