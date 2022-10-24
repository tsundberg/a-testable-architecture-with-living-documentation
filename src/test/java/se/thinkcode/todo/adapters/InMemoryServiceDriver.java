package se.thinkcode.todo.adapters;

import se.thinkcode.todo.*;

import java.util.List;

public class InMemoryServiceDriver implements TodoListDriver {
private final TaskRepository taskRepository = new InMemoryTaskRepository();
private final TodoListService todoListService = new TodoListService(taskRepository);

    @Override
    public void addTask(Owner owner, Task task) {
        todoListService.addTask(owner, task);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        return todoListService.getTasks(owner);
    }
}
