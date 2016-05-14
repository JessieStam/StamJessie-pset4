package jstam.stamjessie_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jessie on 11/05/2016.
 */
public class TodoManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstdb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo_table";
    private TodoManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    private ArrayList<TodoList> todo_list_list;

    // create first and only instance
    private static TodoManager ourInstance = null;

    // methods
    public static TodoManager getOurInstance() {
        return ourInstance;
    }

//    public static TodoManager getInstance(){
//
//        if (ourInstance == null) {
//            ourInstance = new TodoManager();
//        }
//        return ourInstance;
//    }

//    public void addItem(task){
//
//    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table, add id and to-do items
        String query = "CREATE TABLE"+ TABLE+ " (_id"+" INTEGER PRIMARY KEY AUTOINCREMENT" + "todo_text TEXT)";
        db.execSQL(query);

        todo_list_list = new ArrayList();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table and create it again when application is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    /*
     * Add to-do items to the list
     */
    public void create(String todo_list_name) {

//        // initialize database for writing
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        // add to-do item to the list and insert into table
//        values.put("todo_text", todo_list_name);
//        db.insert(TABLE, null, values);
//        db.close();

        // make new item list
        TodoList todo_list = new TodoList(todo_list_name);

        // add item list to  item list list
        todo_list_list.add(todo_list);
    }

    /*
     * Read through the database
     */
    public ArrayList<HashMap<String, String>> read() {

        // initialize database for reading
        SQLiteDatabase db = getReadableDatabase();

        // select id and item from the table
        String query = "SELECT _id "+"todo_text "+ " FROM"+ TABLE;
        ArrayList<HashMap<String, String>> todo = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        // for every item in the list, read id and to-do
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> todo_list = new HashMap<>();
                todo_list.put("id", cursor.getString(cursor.getColumnIndex(" id")));
                todo_list.put("todo_item", cursor.getString
                        (cursor.getColumnIndex("todo")));
                todo.add(todo_list);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return todo;
    }

    /*
     * Update database
     */
    public void update(TodoItem todo_title) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to list and add to the table
        //values.put("todo", TodoItem.getTitle());

        // hoe vul ik deze id in?
//        db.update(TABLE, values, " id = ? ", new String[] {String.valueOf(TodoItem.id)});
//        db.close();
    }

    /*
     * Delete item from the table
     */
    public void delete(int id) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();

        // delete to-do item from the table
        db.delete(TABLE, " _id = ? ", new String[] {String.valueOf(id)});
        db.close();
    }

    public void delete_list(int id) {

        String delete_list = String.valueOf(id);

        // add item list to  item list list
        todo_list_list.remove(delete_list);
    }
}
