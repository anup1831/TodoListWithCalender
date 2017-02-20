package com.pathfinder.anup.dailyaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListAdapter;

import com.pathfinder.anup.database.TodoListSqlHelper;
import com.pathfinder.anup.model.WishItemModel;
import com.pathfinder.anup.todolist.TodoMainViewInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 2/13/2017.
 */

public class DailyActivityMainViewInteractorImpl implements DailyActivityMainViewInteractor {

    TodoListSqlHelper todoListSqlHelper;
    private ListAdapter todoListAdapter;
    @Override
    public void pullListFromDB(Context context, DailyActivityMainViewInteractor.OnLoadListFinishedListener listFinishedListener) {
        listFinishedListener.onLoadListFinished(fetchListFromDB(context));
    }

    private List<WishItemModel> fetchListFromDB(Context context){
        List<WishItemModel> wishList = new ArrayList<>();
        //WishItemModel itemModel;
        todoListSqlHelper = new TodoListSqlHelper(context);
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TodoListSqlHelper.TABLE_NAME,
                new String[]{TodoListSqlHelper._ID, TodoListSqlHelper.COL1_TASK},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            //wishList.add(cursor.getString(cursor.getColumnIndexOrThrow(TodoListSqlHelper.COL1_TASK)));
            wishList.add(new WishItemModel(cursor.getString(cursor.getColumnIndexOrThrow(TodoListSqlHelper.COL1_TASK)), 0));
        }
        sqLiteDatabase.close();
        return wishList;
    }



}
