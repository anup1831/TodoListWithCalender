package com.pathfinder.anup.dailyaction;

import android.content.Context;

/**
 * Created by Anup on 2/13/2017.
 */

public interface DailyActivityMainViewPresenter {

    void onResume(Context context);
    void onItemClicked(int position);
    void onDestroy();
    void onSuccess();
}
