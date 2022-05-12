package se.thinkcode.todo;

import java.util.List;
import java.util.UUID;

public class SqlTaskRepository implements TaskRepository {
    private final DataSource dataSource;

    public SqlTaskRepository() {
        dataSource = new DataSource();
    }

    @Override
    public void addTask(Owner owner, Task task) {
        TaskDAO dao = dataSource.getTaskDAO();

        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();

        String ownerName = owner.getName();
        String taskName = task.getDescription();

        dao.add(key, ownerName, taskName);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        TaskDAO dao = dataSource.getTaskDAO();

        String ownerName = owner.getName();

        return dao.getTasks(ownerName);
    }
}
