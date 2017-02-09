package database;

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
    public static final int VERSION_NAME = 1;
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String COL1_TASK = "todo";
    public static final String _ID = BaseColumns._ID;



    public TodoListSqlHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DB_NAME, null, VERSION_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTodoListTable = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1_TASK + " TEXT)";
        sqLiteDatabase.execSQL(createTodoListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
