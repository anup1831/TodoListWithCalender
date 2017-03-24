package com.pathfinder.anup.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toolbar;

import com.pathfinder.anup.model.UpdatedTodoModel;
import com.pathfinder.anup.util.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 3/1/2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final int DB_BERSION = 1;
    public static final String DB_NAME = "db_todo";

    //Table tb_todo_entry
    public static final String TB_TODO_ENTRY = "tb_todo_entry";
    public static final String ID = "id";
    public static final String TODO_SHORT_NAME = "short_name";
    public static final String TODO_DESCRIPTION = "description";

    //Updated todo list table
    public static final String TABLE_NAME_UPDATED_TODO_LIST = "UPDATED_TODO_LIST";
    public static final String TASK_DONE = "done_todo_item";
    public static final String TASK_STATUS = "task_status";
    public static final String ID_UPDATED_TODO = "id_updated_todo";

    List<UpdatedTodoModel> reportList = new ArrayList<UpdatedTodoModel>();



    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_BERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Tools.hasJellyBean()){
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_TODO_ENTRY = "CREATE TABLE " + TB_TODO_ENTRY + "( "
                + ID + " INTEGER PRIMAY KEY UNIQUE, "
                + TODO_SHORT_NAME + " TEXT NOT NULL, "
                + TODO_DESCRIPTION + " TEXT" + " )";
        db.execSQL(CREATE_TB_TODO_ENTRY);

        String CREATE_UPDATED_TODO_LIST = "CREATE TABLE " + TABLE_NAME_UPDATED_TODO_LIST + "("
                + ID_UPDATED_TODO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TASK_DONE + " TEXT, "
                + TASK_STATUS + " INTEGER"
                + ")";
        db.execSQL(CREATE_UPDATED_TODO_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+TB_TODO_ENTRY);

            onCreate(db);
        }
    }

    public List<UpdatedTodoModel> fetchAllUpdatedTodoListFromDB(Context context){
        //TodoListSqlHelper todoListSqlHelper = new TodoListSqlHelper(context);
        DatabaseOpenHelper dbHelper = new DatabaseOpenHelper(context);
        UpdatedTodoModel model;
        String selectQuery = "SELECT * FROM " + DatabaseOpenHelper.TABLE_NAME_UPDATED_TODO_LIST;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

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
