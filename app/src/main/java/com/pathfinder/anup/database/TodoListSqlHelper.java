package com.pathfinder.anup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Anup on 2/6/2017.
 * change sqlite to Active android
 */

public class TodoListSqlHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "com.pathfinder.anup.imptodo";
    public static final int VERSION_NAME = 2;
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String COL1_TASK = "todo";
    public static final String _ID = BaseColumns._ID;

    //Updated todo list table
    public static final String TABLE_NAME_UPDATED_TODO_LIST = "UPDATED_TODO_LIST";
    public static final String TASK_DONE = "done_todo_item";
    public static final String TASK_STATUS = "task_status";
    public static final String _ID_UPDATED_TODO = BaseColumns._ID;



    public TodoListSqlHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DB_NAME, null, VERSION_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTodoListTable = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1_TASK + " TEXT"
                + ")";

        String createUpdatedTodoListTable = "CREATE TABLE " + TABLE_NAME_UPDATED_TODO_LIST + "("
                + _ID_UPDATED_TODO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TASK_DONE + " TEXT, "
                + TASK_STATUS + " INTEGER"
                + ")";
        sqLiteDatabase.execSQL(createTodoListTable);
        sqLiteDatabase.execSQL(createUpdatedTodoListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_UPDATED_TODO_LIST);

        onCreate(sqLiteDatabase);
    }
}
