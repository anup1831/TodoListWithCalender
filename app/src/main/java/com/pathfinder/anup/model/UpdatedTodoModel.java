package com.pathfinder.anup.model;

/**
 * Created by Anup on 2/22/2017.
 */

public class UpdatedTodoModel {
    private int id;
    private String item;
    private int status;

    public UpdatedTodoModel() {
    }

    public UpdatedTodoModel(int id, String item, int status) {
        this.id = id;
        this.item = item;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
