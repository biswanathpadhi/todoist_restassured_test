package com.todoist.stepdefinitions;

import com.github.javafaker.Faker;
import com.todoist.pojo.Payload;
import com.todoist.pojo.project.CreateProjectResponsePayload;
import com.todoist.util.AuthTokenGenerator;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ProjectStepDefs {
    String resourceEndPoint = "projects";
    RequestSpecification requestSpecification;
    Response response;
    CreateProjectResponsePayload createProjectResponsePayload;
    String bearerToken;
    private Logger log =LogManager.getLogger();
    private TestUtil util = new TestUtil();

    @Given("I am an authorized user and have token")
    public void i_am_an_authorized_user_and_have_token() {
        // Write code here that turns the phrase above into concrete actions
        bearerToken = ConfigReader.readValueFromPropertyFile("bearerToken");
        if (bearerToken == null) {
            log.info("BearerToken not found, getting it using client details");
            bearerToken = new AuthTokenGenerator().getTokenUsingClientDetails();
        }
    }

    @Given("the project endpoint exists")
    public void theProjectEndpointExists() {
        requestSpecification = new SpecBuilder().requestSpecification();
    }

    @When("I send a valid createProject payload")
    public void iSendAValidCreateProjectPayload() {
        response = given(requestSpecification)
                .auth().oauth2(bearerToken)
                .header("Content-Type", "application/json")
                .header("X-Request-Id", UUID.randomUUID().toString())
                .body(Payload.createProjecRequestPayload("TA_projectcreate" + util.getRandomString()))
                .log().all()
                .when().post(resourceEndPoint)
                .then()
                .log().all(true)
                .extract()
                .response();

    }

    @Then("response status code should be {int}")
    public void responseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @And("create project response should be valid")
    public void createProjectResponseShouldBeValid() {
        createProjectResponsePayload = response.as(CreateProjectResponsePayload.class);
        long projectId = createProjectResponsePayload.getId();
        String projectName = createProjectResponsePayload.getName();
        Assert.assertFalse("Project name is empty", projectName.isBlank());
        Assert.assertTrue("Project url is wrong", createProjectResponsePayload.getUrl().equals("https://todoist.com/showProject?id=" + projectId));

        deleteProject(projectId);
    }


    @And("I send a delete request with the id from create project response")
    public void iSendADeleteRequestWithTheIdFromCreateProjectResponse() {
        deleteProject(response.as(CreateProjectResponsePayload.class).getId());
    }

    public void deleteProject(long projectId) {
        response = given(requestSpecification)
                .auth().oauth2(bearerToken)
                .header("Content-Type", "application/json")
                .pathParam("projectPathParam", projectId)
                .log().all()
                .when().delete(resourceEndPoint + "/{projectPathParam}")
                .then()
                .log().all(true)
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract()
                .response();
    }

    public long getProjectIdFromCreateTestProject() {
        i_am_an_authorized_user_and_have_token();
        theProjectEndpointExists();
        iSendAValidCreateProjectPayload();
        createProjectResponsePayload = response.as(CreateProjectResponsePayload.class);
        return createProjectResponsePayload.getId();
    }

    @Then("I should be able to delete the project successfully")
    public void iShouldBeAbleToDeleteTheProjectSuccessfully() {
        Assert.assertEquals(204, response.getStatusCode());
    }
}
