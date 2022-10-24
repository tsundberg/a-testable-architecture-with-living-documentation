package se.thinkcode.todo;

public class SqlTaskRepositoryIT extends TaskRepositoryTest {
    public SqlTaskRepositoryIT() {
        super(new SqlTaskRepository());
    }
}