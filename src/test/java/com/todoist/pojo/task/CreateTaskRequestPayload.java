package com.todoist.pojo.task;

import com.todoist.pojo.due.Due;

/**
 * @author biswanath.padhi
 */

public class CreateTaskRequestPayload {
    long id;
    long project_id;
    long section_id;
    String content;
    boolean completed;
    long[] label_ids;
    long parent_id;
    long order;
    long priority;
    Due due;
    String url;
    long comment_count;
    long assignee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public long getSection_id() {
        return section_id;
    }

    public void setSection_id(long section_id) {
        this.section_id = section_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long[] getLabel_ids() {
        return label_ids;
    }

    public void setLabel_ids(long[] label_ids) {
        this.label_ids = label_ids;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public Due getDue() {
        return due;
    }

    public void setDue(Due due) {
        this.due = due;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getComment_count() {
        return comment_count;
    }

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    public long getAssignee() {
        return assignee;
    }

    public void setAssignee(long assignee) {
        this.assignee = assignee;
    }


}
