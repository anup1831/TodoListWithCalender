package com.pathfinder.anup.dailyaction;

import android.content.Context;

import com.pathfinder.anup.model.WishItemModel;
import com.pathfinder.anup.todolist.TodoMainViewInteractor;

import java.util.List;

/**
 * Created by Anup on 2/13/2017.
 */

public interface DailyActivityMainViewInteractor {

    interface OnLoadListFinishedListener{
        void onLoadListFinished(List<WishItemModel> listItems);

    }

    void pullListFromDB(Context context, DailyActivityMainViewInteractor.OnLoadListFinishedListener listFinishedListener);

    interface OnCheckBoxClickedListener{
        void onCheckedListener();
    }
}
