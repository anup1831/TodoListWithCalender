package com.pathfinder.anup.model;

/**
 * Created by Anup on 2/14/2017.
 */

public class WishItemModel {

    private String wishItem;
    private int value;

    public WishItemModel(String wishItem, int value) {
        this.wishItem = wishItem;
        this.value = value;
    }

    public String getWishItem() {
        return wishItem;
    }

    public void setWishItem(String wishItem) {
        this.wishItem = wishItem;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
