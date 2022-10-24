package se.thinkcode.todo.adapters;

import se.thinkcode.todo.Owner;
import se.thinkcode.todo.Task;

import java.util.List;

public interface TodoListDriver {
    void addTask(Owner owner, Task task);

    List<Task> getTasks(Owner owner);
}
