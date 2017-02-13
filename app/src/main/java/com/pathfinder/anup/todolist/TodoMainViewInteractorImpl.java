package com.pathfinder.anup.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import com.pathfinder.anup.database.TodoListSqlHelper;

/**
 * Created by Anup on 2/8/2017.
 */

public class TodoMainViewInteractorImpl implements TodoMainViewInteractor {

    TodoListSqlHelper todoListSqlHelper;
    private ListAdapter todoListAdapter;

    @Override
    public void pullListFromDB(Context context, OnLoadListFinishedListener listFinishedListener) {
        listFinishedListener.onLoadListFinished(updateTodoList(context));
    }

    @Override
    public void addWishTodoData(Context context, String todoItem) {
        todoListSqlHelper = new TodoListSqlHelper(context);
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.clear();
        values.put(TodoListSqlHelper.COL1_TASK, todoItem);
        sqLiteDatabase.insertWithOnConflict(TodoListSqlHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }


    private List<String> updateTodoList(Context context){
        List<String> wishList = new ArrayList<>();
        todoListSqlHelper = new TodoListSqlHelper(context);
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TodoListSqlHelper.TABLE_NAME,
                new String[]{TodoListSqlHelper._ID, TodoListSqlHelper.COL1_TASK},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            wishList.add(cursor.getString(cursor.getColumnIndexOrThrow(TodoListSqlHelper.COL1_TASK)));
        }

        /*todoListAdapter = new SimpleCursorAdapter(
                context,
                R.layout.layout_todo_list_item,
                cursor,
                new String[]{TodoListSqlHelper.COL1_TASK},
                new int[]{R.id.todoTaskTV},
                0
        );*/
        return wishList;
        /*return Arrays.asList(
                "Jamshedpur",
                "Jamnagar",
                "Junagadh",
                "Jogeshwari",
                "Powai",
                "Khargarh",
                "Panvel",
                "Boriwali",
                "Virar"
        );*/
    }
}
