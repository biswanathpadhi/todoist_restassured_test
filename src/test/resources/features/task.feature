Feature: Task Endpoint testing

  Background:
    Given I am an authorized user and have token

  @task
  Scenario: I should be able to create task under a project
    Given the task endpoint exists
    When I have created project and remember the response projectid
    And I have the create task payload with the projectid
    And I post the task payload
    Then I should be able to create task successfully
    And I should see the task under the entered projectid

  @task @updateTask
  Scenario: I should be able to update the task content
    Given the task endpoint exists
    When I have created a task
    And I update the task content
    Then the content should be updated successfully