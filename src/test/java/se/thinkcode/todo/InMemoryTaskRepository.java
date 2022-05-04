package se.thinkcode.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {
    private final Map<Owner, List<Task>> tasks = new HashMap<>();

    @Override
    public void addTask(Owner owner, Task task) {
        List<Task> tasks = this.tasks.getOrDefault(owner, new ArrayList<>());
        tasks.add(task);

        this.tasks.put(owner, tasks);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        return tasks.getOrDefault(owner, new ArrayList<>());
    }
}
