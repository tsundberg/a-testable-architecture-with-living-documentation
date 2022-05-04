package se.thinkcode.todo;

import java.util.List;

public class TodoListService {
    private final TaskRepository taskRepository;

    public TodoListService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Owner owner, Task task) {
        taskRepository.addTask(owner, task);
    }

    public List<Task> getTasks(Owner owner) {
        return taskRepository.getTasks(owner);
    }
}
