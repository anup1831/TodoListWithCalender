package com.pathfinder.anup.todolist;

import android.content.Context;

import java.util.List;

/**
 * Created by Anup on 2/8/2017.
 */

public interface TodoMainViewInteractor {

    interface OnLoadListFinishedListener{
        void onLoadListFinished(List<String> listItems);

        //void onSuccess();
    }

   /* interface OnAddTodoItemListener{
        void onAddButtonListener();
    }*/

    void pullListFromDB(Context context, OnLoadListFinishedListener listFinishedListener);
    void addWishTodoData(Context context, String todoItem);
}
