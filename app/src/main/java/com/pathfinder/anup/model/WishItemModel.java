package com.pathfinder.anup.model;

/**
 * Created by Anup on 2/14/2017.
 */

public class WishItemModel {


    private String wishItem;
    private int id;
    private int status;


    public WishItemModel(String wishItem, int id, int status) {
        this.wishItem = wishItem;
        this.id = id;
        this.status = status;
    }

    public String getWishItem() {
        return wishItem;
    }

    public void setWishItem(String wishItem) {
        this.wishItem = wishItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
