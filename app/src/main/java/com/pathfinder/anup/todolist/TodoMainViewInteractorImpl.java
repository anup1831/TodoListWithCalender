package com.pathfinder.anup.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.pathfinder.anup.database.DatabaseOpenHelper;
import com.pathfinder.anup.database.TodoListSqlHelper;

import static java.sql.Types.ROWID;

/**
 * Created by Anup on 2/8/2017.
 */

public class TodoMainViewInteractorImpl implements TodoMainViewInteractor {

    //TodoListSqlHelper todoListSqlHelper;
    //private ListAdapter todoListAdapter;
    private DatabaseOpenHelper dbHelper;

    @Override
    public void pullListFromDB(Context context, OnLoadListFinishedListener listFinishedListener) {
        listFinishedListener.onLoadListFinished(updateTodoList(context));
    }

    @Override
    public void addWishTodoData(Context context, int id, String todoItem, String desc) {
        //int id = 1;
        //todoListSqlHelper = new TodoListSqlHelper(context);
        dbHelper = new DatabaseOpenHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.ID, id);
        values.put(dbHelper.TODO_SHORT_NAME, todoItem);
        values.put(dbHelper.TODO_DESCRIPTION, desc);
        //sqLiteDatabase.insertWithOnConflict(TodoListSqlHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);4
        sqLiteDatabase.insert(dbHelper.TB_TODO_ENTRY, null, values);
        sqLiteDatabase.close();
    }


    // To update the table need context, record id and item to be updated
    private List<String> updateTodoList(Context context){
        List<String> wishList = new ArrayList<>();
        //todoListSqlHelper = new TodoListSqlHelper(context);
        dbHelper = new DatabaseOpenHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        String selectQry = "SELECT rowid, * FROM "+  dbHelper.TB_TODO_ENTRY/*+ " WHERE " + dbHelper.TODO_SHORT_NAME +" = " + "test" */;

        Cursor cursor = sqLiteDatabase.rawQuery(selectQry, null);

        /*Cursor cursor = sqLiteDatabase.query(dbHelper.TB_TODO_ENTRY,
                new String[]{"rowid", DatabaseOpenHelper.ID, DatabaseOpenHelper.TODO_SHORT_NAME, DatabaseOpenHelper.TODO_DESCRIPTION},
                null, null, null, null, null); */
        while (cursor.moveToNext()){
            Log.i("Anup", "ID - shortname "+ cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.ID)) + " - "+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.TODO_SHORT_NAME)));
            wishList.add(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.TODO_SHORT_NAME)));
        }
        sqLiteDatabase.close();
        return wishList;
    }
}
