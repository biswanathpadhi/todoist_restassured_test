package com.todoist.stepdefinitions;

import com.todoist.pojo.Payload;
import com.todoist.pojo.task.CreateTaskRequestPayload;
import com.todoist.pojo.task.CreateTaskResponsePayload;
import com.todoist.util.ConfigReader;
import com.todoist.util.SpecBuilder;
import com.todoist.util.TestUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TasksStepDefs {

    String resourceEndPoint = "tasks";
    long projectId;
    CreateTaskRequestPayload createTaskRequestPayload;
    CreateTaskResponsePayload createTaskResponsePayload;
    private RequestSpecification requestSpecification;
    private Response response;
    private TestUtil util = new TestUtil();
    private ProjectStepDefs projectStepDefs;

    @Given("the task endpoint exists")
    public void theTaskEndpointExists() {
        requestSpecification = new SpecBuilder().requestSpecification();
    }

    @When("I have created project and remember the response projectid")
    public void iHaveCreatedProjectAndRememberTheResponseProjectid() {
        projectStepDefs = new ProjectStepDefs();
        projectId = projectStepDefs.getProjectIdFromCreateTestProject();
    }


    @And("I have the create task payload with the projectid")
    public void iHaveTheCreateTaskPayloadWithTheProjectid() {

        String content = "TA_taskcreate" + util.getRandomString();
        String due_date = "2021-04-01";
        String due_string = "tomorrow at 12";

        createTaskRequestPayload = Payload.createTaskRequestPayload(projectId, content, due_date, due_string);
    }

    @And("I post the task payload")
    public void iPostTheTaskPayload() {

        response = given(requestSpecification)
                .auth().oauth2(ConfigReader.readValueFromPropertyFile("bearerToken"))
                .header("Content-Type", "application/json")
                .header("X-Request-Id", UUID.randomUUID().toString())
                .body(createTaskRequestPayload)
                .log().all()
                .when().post(resourceEndPoint)
                .then()
                .log().all(true)
                .extract()
                .response();


    }


    @And("I should see the task under the entered projectid")
    public void iShouldSeeTheTaskUnderTheEnteredProjectid() {
        createTaskResponsePayload = response.as(CreateTaskResponsePayload.class);
        Assert.assertEquals(createTaskResponsePayload.getProject_id(), projectId);

        deleteTask(createTaskResponsePayload.getId());
        projectStepDefs.deleteProject(projectId);
    }


    @When("I have created a task")
    public void iHaveCreatedATask() {
        theTaskEndpointExists();

        createTaskRequestPayload = new CreateTaskRequestPayload();
        createTaskRequestPayload.setContent("TA_CreateTask_" + util.getRandomString());

        createTaskResponsePayload = given(requestSpecification)
                .auth().oauth2(ConfigReader.readValueFromPropertyFile("bearerToken"))
                .header("Content-Type", "application/json")
                .header("X-Request-Id", UUID.randomUUID().toString())
                .body(createTaskRequestPayload)
                .log().all()
                .when().post(resourceEndPoint)
                .then()
                .log().all(true)
                .extract()
                .response().as(CreateTaskResponsePayload.class);

    }


    @And("I update the task content")
    public void iUpdateTheTaskContent() {
        String updatedContent = "TA_TaskUpdate" + util.getRandomString();
        createTaskRequestPayload.setContent(updatedContent);

        response = given(requestSpecification)
                .auth().oauth2(ConfigReader.readValueFromPropertyFile("bearerToken"))
                .header("Content-Type", "application/json")
                .header("X-Request-Id", UUID.randomUUID().toString())
                .pathParam("taskId", createTaskResponsePayload.getId())
                .body(createTaskRequestPayload)
                .log().all()
                .when().post(resourceEndPoint + "/{taskId}")
                .then()
                .log().all(true)
                .extract()
                .response();

    }

    @Then("the content should be updated successfully")
    public void theContentShouldBeUpdatedSuccessfully() {
        Assert.assertEquals(204, response.getStatusCode());
        deleteTask(createTaskResponsePayload.getId());
    }

    @Then("I should be able to create task successfully")
    public void iShouldBeAbleToCreateTaskSuccessfully() {
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    private void deleteTask(long id) {

        // Deleting the Task
        given(requestSpecification)
                .auth().oauth2(ConfigReader.readValueFromPropertyFile("bearerToken"))
                .header("Content-Type", "application/json")
                .pathParam("taskId", id)
                .log().all()
                .when().delete(resourceEndPoint + "/{taskId}")
                .then()
                .log().all(true)
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);

        // Should get 404 Resource Not Found after deleting
        given(requestSpecification)
                .auth().oauth2(ConfigReader.readValueFromPropertyFile("bearerToken"))
                .header("Content-Type", "application/json")
                .pathParam("taskId", id)
                .log().all()
                .when().get(resourceEndPoint + "/{taskId}")
                .then()
                .log().all(true)
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
