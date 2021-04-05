package com.todoist.pojo;

import com.todoist.pojo.due.Due;
import com.todoist.pojo.project.CreateProjectRequestPayload;
import com.todoist.pojo.task.CreateTaskRequestPayload;
import com.todoist.util.TestUtil;

public class Payload {
    private TestUtil util = new TestUtil();

    public static CreateProjectRequestPayload createProjecRequestPayload(String name) {
        return new CreateProjectRequestPayload(name);
    }

    public static CreateTaskRequestPayload createTaskRequestPayload(long projectId, String content, String due_date, String due_string) {
        CreateTaskRequestPayload createTaskRequestPayload = new CreateTaskRequestPayload();

        Due due = new Due();
        due.setString(due_string);
        due.setDate(due_date);
        createTaskRequestPayload.setDue(due);
        createTaskRequestPayload.setProject_id(projectId);
        createTaskRequestPayload.setContent(content);
        return createTaskRequestPayload;

    }

    public CreateProjectRequestPayload createProjecRequestPayload() {
        CreateProjectRequestPayload createProjectPayload = new CreateProjectRequestPayload();
        createProjectPayload.setParent_id(util.getRandomNumber());
        createProjectPayload.setFavorite(util.getRandomBoolean());
        createProjectPayload.setColor(util.getRandomNumber());
        createProjectPayload.setName(util.getRandomString());
        return createProjectPayload;
    }
}
