package com.pathfinder.anup.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.pathfinder.anup.model.UpdatedTodoModel;
import com.pathfinder.anup.util.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 2/6/2017.
 * change sqlite to Active android
 */

public class TodoListSqlHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "com.pathfinder.anup.imptodo";
    public static final int VERSION_NAME = 2;
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String COL1_TASK = "todo";
    public static final String _ID = "id";

    //Updated todo list table
    public static final String TABLE_NAME_UPDATED_TODO_LIST = "UPDATED_TODO_LIST";
    public static final String TASK_DONE = "done_todo_item";
    public static final String TASK_STATUS = "task_status";
    public static final String _ID_UPDATED_TODO = "id_updated_todo";



    public TodoListSqlHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DB_NAME, null, VERSION_NAME);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if(Tools.hasJellyBean()){
            db.setForeignKeyConstraintsEnabled(true);
        }
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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        if(olderVersion != newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_UPDATED_TODO_LIST);

            onCreate(sqLiteDatabase);
        }

    }

    public List<UpdatedTodoModel> fetchAllUpdatedTodoListFromDB(Context context){
        TodoListSqlHelper todoListSqlHelper = new TodoListSqlHelper(context);
        List<UpdatedTodoModel> reportList = new ArrayList<UpdatedTodoModel>();
        UpdatedTodoModel model;
        String selectQuery = "SELECT * FROM " + TodoListSqlHelper.TABLE_NAME_UPDATED_TODO_LIST;
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        try{
            if(cursor.moveToFirst()){
                do{
                    model = new UpdatedTodoModel(cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper._ID_UPDATED_TODO)), cursor.getString(cursor.getColumnIndex(TodoListSqlHelper.TASK_DONE)),
                            cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper.TASK_STATUS)));
                    /*model.setId(cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper._ID_UPDATED_TODO)));
                    model.setItem(cursor.getString(cursor.getColumnIndex(TodoListSqlHelper.TASK_DONE)));
                    model.setStatus(cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper.TASK_STATUS)));*/
                    reportList.add(model);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("Anup", "Error while trying to get posts from database");
        } finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return reportList;
    }
}
