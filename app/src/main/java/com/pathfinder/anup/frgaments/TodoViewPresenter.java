package com.pathfinder.anup.frgaments;

/**
 * Created by Anup on 2/7/2017.
 */

public interface TodoViewPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();

    void addWishTodoItems(String wishStr);
}
