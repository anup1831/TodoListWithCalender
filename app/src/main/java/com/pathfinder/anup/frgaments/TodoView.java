package com.pathfinder.anup.frgaments;

import java.util.List;

/**
 * Created by Anup on 2/6/2017.
 */

public interface TodoView {

    void setWishEntryErr();
    void showProgress();
    void hideProgress();
    void setItems(List<String> items);
    void navigateToCalender();
    void showMessage(String message);
}
