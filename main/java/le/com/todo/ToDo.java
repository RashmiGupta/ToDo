package le.com.todo;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;
import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import le.com.todo.db.TodoListSQLHelper;

public class ToDo extends ListActivity {

    private ListAdapter todoListAdapter;
    private TodoListSQLHelper todoListSQLHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        updateTodoList();
        setupListViewListener();
    }

    private void setupListViewListener() {

        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                TextView childTextView = (TextView) ((ViewGroup)view).getChildAt(0);
                String myText = childTextView.getText().toString();
                editDialog(true, myText);
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                editDialog(false, "");
                return true;

            default:
                return false;
        }
    }


    private void editDialog(boolean flag, String val) {
        final String edit_or_add = (flag)?"edit":"add";

        todoListSQLHelper = new TodoListSQLHelper(ToDo.this);
        final String id = todoListSQLHelper.onSelect(val);

        final EditText todoET = new EditText(this);
        todoET.setText(val, TextView.BufferType.EDITABLE);

        AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
        todoTaskBuilder.setTitle(" Edit/Add Todo Task Item:");
        todoTaskBuilder.setMessage(" saves task to list and db ");
        todoTaskBuilder.setView(todoET);
        todoTaskBuilder.setPositiveButton("Save Task", new DialogInterface.OnClickListener() {
        @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String todoTaskInput = todoET.getText().toString();
                    SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.clear();
                    //write the Todo task input into database table
                    values.put(TodoListSQLHelper.COL1_TASK, todoTaskInput);

                    switch(edit_or_add){
                        case "edit":
                            sqLiteDatabase.update(TodoListSQLHelper.TABLE_NAME,values, "_id = ?", new String[]{id});
                            break;
                        case "add":
                            sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                            break;
                    }

                    //update the Todo task list UI
                    updateTodoList();
                }
        });
        todoTaskBuilder.setNegativeButton("Cancel", null);

        todoTaskBuilder.create().show();

    }


    //update the todo task list UI
    private void updateTodoList() {
        todoListSQLHelper = new TodoListSQLHelper(ToDo.this);
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TodoListSQLHelper.TABLE_NAME,
                new String[]{TodoListSQLHelper._ID, TodoListSQLHelper.COL1_TASK},
                null, null, null, null, null);

        //binds the todo task list with the UI
        todoListAdapter =  new SimpleCursorAdapter(
                this,
                R.layout.task,
                cursor,
                new String[]{TodoListSQLHelper.COL1_TASK},
                new int[]{R.id.todoTaskTV},
                0
        );

        this.setListAdapter(todoListAdapter);

    }

    //closing the todo task item
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = (TextView) v.findViewById(R.id.todoTaskTV);
        String todoTaskItem = todoTV.getText().toString();
        closeTaskDialog(todoTaskItem);
    }

    private void closeTaskDialog(String val) {
        final String todoTaskItem = val;
        AlertDialog.Builder todoTaskRemove = new AlertDialog.Builder(this);
        todoTaskRemove.setTitle(" Close Todo Task Item: \n" + todoTaskItem);
        todoTaskRemove.setMessage(" task will be removed from Todo list and db ");

        todoTaskRemove.setPositiveButton("Close Task", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                        " WHERE " + TodoListSQLHelper.COL1_TASK + " = '" + todoTaskItem + "'";
                todoListSQLHelper = new TodoListSQLHelper(ToDo.this);
                SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
                sqlDB.execSQL(deleteTodoItemSql);
                updateTodoList();
            }
        });
        todoTaskRemove.setNegativeButton("Cancel", null);

        todoTaskRemove.create().show();
    }
}
