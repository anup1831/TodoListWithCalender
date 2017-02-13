package com.pathfinder.anup.todolist;

import java.util.List;

/**
 * Created by Anup on 2/8/2017.
 */

public interface TodoMainView {
    void showProgress();
    void hideProgress();
    void setItems(List<String> items);
    void showMessage(String message);
    void navigateToHome();
    void todoItemErr();
}
