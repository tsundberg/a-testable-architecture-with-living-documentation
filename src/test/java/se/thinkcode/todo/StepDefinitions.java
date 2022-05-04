package se.thinkcode.todo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final TodoListService todoListService = new TodoListService(taskRepository);
    private final Owner owner = new Owner("Thomas");
    private Task task;

    @Given("we are almost out of cat food")
    public void we_are_almost_out_of_cat_food() {
    }

    @When("I add buy cat food to my todo list")
    public void i_add_buy_cat_food_to_my_todo_list() {
        task = new Task("Buy food for the cat");
        todoListService.addTask(owner, task);
    }

    @Then("I won't forget to get more food to My and Gretchen")
    public void i_won_t_forget_to_get_more_food_to_my_and_gretchen() {
        List<Task> actual = todoListService.getTasks(owner);

        assertThat(actual).containsExactly(task);
    }
}
