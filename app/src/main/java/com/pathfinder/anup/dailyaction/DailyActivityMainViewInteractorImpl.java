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
        sqLiteDatabase.beginTransaction();
        try{
            //Todo item may be already present in DB so update its status only, Todo item is unique here
           // long taskID = addOrUpdateTodoItem(updatedTodoList.get());

            ContentValues values = new ContentValues();
            for (WishItemModel item: updatedTodoList) {
                Log.i("Anup", "insert item "+item.getWishItem());
                values.put(TodoListSqlHelper.TASK_DONE, item.getWishItem());
                Log.i("Anup", "insert status "+item.getValue());
                values.put(TodoListSqlHelper.TASK_STATUS, item.getValue());
            }

            Log.i("Anup", "size of updatedTodoList list - "+updatedTodoList.size());
            sqLiteDatabase.insertOrThrow(TodoListSqlHelper.TABLE_NAME_UPDATED_TODO_LIST, null, values);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.d("Anup", "Error while trying to add post to database");
        } finally {
            sqLiteDatabase.endTransaction();
        }

    }

    /*private List<UpdatedTodoModel> fetchAllUpdatedTodoListFromDB(Context context){
        todoListSqlHelper = new TodoListSqlHelper(context);
        List<UpdatedTodoModel> reportList = new ArrayList<UpdatedTodoModel>();
        UpdatedTodoModel model;
        String selectQuery = "SELECT * FROM " + TodoListSqlHelper.TABLE_NAME_UPDATED_TODO_LIST;
        SQLiteDatabase sqLiteDatabase = todoListSqlHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        try{
           if(cursor.moveToFirst()){
                do{
                    model = new UpdatedTodoModel();
                    model.setId(cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper._ID_UPDATED_TODO)));
                    Log.d("Anup", "ID "+cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper._ID_UPDATED_TODO)));
                    model.setItem(cursor.getString(cursor.getColumnIndex(TodoListSqlHelper.TASK_DONE)));
                    Log.d("Anup", "Item "+cursor.getString(cursor.getColumnIndex(TodoListSqlHelper.TASK_DONE)));
                    model.setStatus(cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper.TASK_STATUS)));
                    Log.d("Anup", "Status "+cursor.getInt(cursor.getColumnIndex(TodoListSqlHelper.TASK_STATUS)));
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
    }*/

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
