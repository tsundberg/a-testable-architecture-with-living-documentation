package se.thinkcode.todo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TaskRepositoryTests {
    private final TaskRepository taskRepository;

    public TaskRepositoryTests(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    public void should_add_a_task() {
        Task expected = new Task("Buy Minutuu");
        Owner owner = new Owner("Malin");

        taskRepository.addTask(owner, expected);
        List<Task> actual = taskRepository.getTasks(owner);

        assertThat(actual).containsExactly(expected);
    }
}
