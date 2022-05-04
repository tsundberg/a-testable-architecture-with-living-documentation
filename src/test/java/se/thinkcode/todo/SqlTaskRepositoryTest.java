package se.thinkcode.todo;

public class SqlTaskRepositoryTest extends TaskRepositoryTests {
    public SqlTaskRepositoryTest() {
        super(new SqlTaskRepository());
    }
}