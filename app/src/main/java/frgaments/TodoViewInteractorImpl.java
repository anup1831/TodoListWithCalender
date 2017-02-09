package frgaments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import com.pathfinder.anup.imptodo.R;

import java.util.ArrayList;
import java.util.List;

import database.TodoListSqlHelper;

/**
 * Created by Anup on 2/6/2017.
 */

public class TodoViewInteractorImpl implements TodoViewInteractor {
    TodoListSqlHelper todoListSqlHelper;
    private ListAdapter todoListAdapter;
    @Override
    public void addWishTodoList(Context context, String wishItem, OnFinishedListener listener) {
        // add wish item in local database
        todoListSqlHelper = new TodoListSqlHelper(context);
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.clear();
        values.put(TodoListSqlHelper.COL1_TASK, wishItem);
        sqLiteDatabase.insertWithOnConflict(TodoListSqlHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

    }

    @Override
    public void pullListFromDB(Context context, OnFinishedListener listener) {

        listener.onSuccess(updateTodoList(context));

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
    }
}
