package le.com.todo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import le.com.todo.ToDo;

/**
 * Created by Akansha Gupta on 29-07-2017.
 */

public class TodoListSQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "com.le.todo";
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String COL1_TASK = "todo";
    public static final String _ID = BaseColumns._ID;

    public TodoListSQLHelper(Context context) {
        //1 is todo list database version
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String createTodoListTable = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1_TASK + " TEXT)";
        sqlDB.execSQL(createTodoListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqlDB);
    }


    public String onSelect( String val) {
        SQLiteDatabase sqlDB = getReadableDatabase();
        Cursor cursor = null;
        String id = "";
        try {
            cursor = sqlDB.rawQuery("SELECT "+ _ID + " FROM " + TABLE_NAME + " WHERE " + COL1_TASK + " = ?", new String[] {val});
            if (cursor.getCount()> 0){
                cursor.moveToFirst();
                id = cursor.getString(cursor.getColumnIndex(_ID));
            }
            return id;
        }finally{
            cursor.close();
        }
    }


}
