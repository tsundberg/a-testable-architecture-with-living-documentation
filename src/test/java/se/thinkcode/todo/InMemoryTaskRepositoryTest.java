package se.thinkcode.todo;

public class InMemoryTaskRepositoryTest extends TaskRepositoryTests {
    public InMemoryTaskRepositoryTest() {
        super(new InMemoryTaskRepository());
    }
}