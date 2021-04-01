Feature: Project Endpoint testing

  Background:
    Given I am an authorized user and have token

  @project
  Scenario: I should be able to create project
    Given the project endpoint exists
    When I send a valid createProject payload
    Then response status code should be 200
    And create project response should be valid

  @project
  Scenario: I should be able to delete project
    Given the project endpoint exists
    When I send a valid createProject payload
    And I send a delete request with the id from create project response
    Then I should be able to delete the project successfully