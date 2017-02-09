package frgaments;

import android.app.Activity;
import android.content.Context;

import java.util.List;

/**
 * Created by Anup on 2/6/2017.
 */

public interface TodoViewInteractor {

    interface OnFinishedListener{
        void onWishEntryError();
        void onSuccess(List<String> items);
    }

    void addWishTodoList(Context context, String wishItem, OnFinishedListener listener);
    void pullListFromDB(Context context, OnFinishedListener listener);
}
