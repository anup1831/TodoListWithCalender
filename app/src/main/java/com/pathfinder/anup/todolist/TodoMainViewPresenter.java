package com.pathfinder.anup.todolist;

import android.content.Context;

/**
 * Created by Anup on 2/8/2017.
 */

public interface TodoMainViewPresenter {

    void onResume(Context context);
    void onItemClicked(int position);
    void onDestroy();
    void onSuccess();
    void addWishTodoInDB(Context context, String todoItem);
    //void onAddTodoEntryErr();
}
