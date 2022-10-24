package se.thinkcode.todo;

public class InMemoryTaskRepositoryTest extends TaskRepositoryTest {
    public InMemoryTaskRepositoryTest() {
        super(new InMemoryTaskRepository());
    }
}