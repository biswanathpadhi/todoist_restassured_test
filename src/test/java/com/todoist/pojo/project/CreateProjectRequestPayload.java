package com.todoist.pojo.project;

/**
 * @author biswanath.padhi
 */

public class CreateProjectRequestPayload {

    String name;
    long parent_id;
    long color;
    Boolean favorite;

    public CreateProjectRequestPayload(){

    }

    public CreateProjectRequestPayload(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
