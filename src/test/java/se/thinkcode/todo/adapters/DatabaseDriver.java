package se.thinkcode.todo.adapters;

import se.thinkcode.todo.*;

import java.util.List;

public class DatabaseDriver implements TodoListDriver {
    private final TaskRepository taskRepository = new SqlTaskRepository();

    @Override
    public void addTask(Owner owner, Task task) {
        taskRepository.addTask(owner, task);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        return taskRepository.getTasks(owner);
    }
}
