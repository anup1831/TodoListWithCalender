package com.pathfinder.anup.dailyaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListAdapter;

import com.pathfinder.anup.database.TodoListSqlHelper;
import com.pathfinder.anup.model.UpdatedTodoModel;
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

    @Override
    public void addUpdatedTodoDataInDB(Context context, List<WishItemModel> updatedTodoList) {

        todoListSqlHelper = new TodoListSqlHelper(context);
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getWritableDatabase();

        Log.i("Anup", "size of updatedTodoList list - "+updatedTodoList.size());
        ContentValues values = new ContentValues();
        for (WishItemModel item: updatedTodoList) {
            Log.i("Anup", "insert item "+item.getWishItem());
            values.put(TodoListSqlHelper.TASK_DONE, item.getWishItem());
            Log.i("Anup", "insert status "+item.getValue());
            values.put(TodoListSqlHelper.TASK_STATUS, item.getValue());
        }

        sqLiteDatabase.insertWithOnConflict(TodoListSqlHelper.TABLE_NAME_UPDATED_TODO_LIST, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        sqLiteDatabase.close();

    }

    private void fetchUpdatedTodoListFromDB(Context context){
        todoListSqlHelper = new TodoListSqlHelper(context);
        List<UpdatedTodoModel> reportList = new ArrayList<UpdatedTodoModel>();
        UpdatedTodoModel model;
        String selectQuery = "SELECT  * FROM " + TodoListSqlHelper.TABLE_NAME_UPDATED_TODO_LIST;
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        Log.i("Anup", "---------------------------- " +cursor.getCount());

        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                model = new UpdatedTodoModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TodoListSqlHelper._ID_UPDATED_TODO)));
                model.setItem(cursor.getString(cursor.getColumnIndexOrThrow(TodoListSqlHelper.TASK_DONE)));
                model.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(TodoListSqlHelper.TASK_STATUS)));
                reportList.add(model);
            }
        }
        sqLiteDatabase.close();

        Log.i("Anup", "size of report list - "+reportList.size());
        for (UpdatedTodoModel item: reportList) {
            Log.i("Anup", "ID "+item.getId());
            Log.i("Anup", "Done Task "+item.getItem());
            Log.i("Anup", "Done status "+item.getStatus());
        }
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
