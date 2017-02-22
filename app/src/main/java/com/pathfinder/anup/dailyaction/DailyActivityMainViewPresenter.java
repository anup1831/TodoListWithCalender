package com.pathfinder.anup.dailyaction;

import android.content.Context;

import com.pathfinder.anup.model.WishItemModel;

import java.util.List;

/**
 * Created by Anup on 2/13/2017.
 */

public interface DailyActivityMainViewPresenter {

    void onResume(Context context);
    void onItemClicked(int position);
    void onDestroy();
    void onSuccess();
    void addUpdatedTodoDataInDB(Context context, List<WishItemModel> updatedTodoItemList);
}
