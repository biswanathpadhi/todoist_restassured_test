package com.todoist.util;

import com.todoist.pojo.project.CreateProjectRequestPayload;

public class Payload {
    private static TestUtil util = new TestUtil();

    public static CreateProjectRequestPayload createProjectRequestPayload() {
        CreateProjectRequestPayload createProjectPayload = new CreateProjectRequestPayload();
        createProjectPayload.setName(util.getRandomString());
        createProjectPayload.setParent_id(util.getRandomNumber());
        createProjectPayload.setColor(util.getRandomNumber());
        createProjectPayload.setFavorite(util.getRandomBoolean());
        return createProjectPayload;
    }

    public static CreateProjectRequestPayload createProjectRequestPayload(String name) {
        return new CreateProjectRequestPayload(name);
    }
}
