Feature: Add tasks to the todo list

  Scenario: Remember to buy cat food
    Given we are almost out of cat food
    When I add buy cat food to my todo list
    Then I won't forget to get more food to My and Gretchen
