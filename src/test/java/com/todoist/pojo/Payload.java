package com.todoist.pojo;

import com.todoist.pojo.due.Due;
import com.todoist.pojo.project.CreateProjectRequestPayload;
import com.todoist.pojo.task.CreateTaskRequestPayload;
import com.todoist.util.TestUtil;

public class Payload {
    private TestUtil util = new TestUtil();
    public static CreateProjectRequestPayload createProjecRequestPayload() {
        CreateProjectRequestPayload createProjectPayload = new CreateProjectRequestPayload();


//            "id": 12321,
//            "name": "Test12321",
//            "comment_count": 12321,
//            "color": 12321,
//            "shared": false,
//            "sync_id": 12321,
//            "order": 12321,
//            "favorite": false,
//            "url": "https://todoist.com/showProject?id=12321"


        createProjectPayload.setName("hello");

        return createProjectPayload;
    }

    public static CreateProjectRequestPayload createProjecRequestPayload(String name) {
        return new CreateProjectRequestPayload(name);
    }

    public static CreateTaskRequestPayload createTaskRequestPayload(long projectId, String content, String due_date, String due_string) {
        CreateTaskRequestPayload createTaskRequestPayload =  new CreateTaskRequestPayload();

        Due due = new Due();
        due.setString(due_string);
        due.setDate(due_date);
//        "YYYY-MM-DD"
        createTaskRequestPayload.setDue(due);
        createTaskRequestPayload.setProject_id(projectId);
        createTaskRequestPayload.setContent(content);
        return createTaskRequestPayload;

    }
}
