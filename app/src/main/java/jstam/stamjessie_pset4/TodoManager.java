package jstam.stamjessie_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

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

    // define list for lists
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
        String query = "CREATE TABLE"+ TABLE+ " (_id"+" INTEGER PRIMARY KEY AUTOINCREMENT" + "todo_text TEXT, current_color TEXT)";

        db.execSQL(query);

        // initialize todo_list_list
        todo_list_list = new ArrayList();
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table and create it again when application is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);

        // hier nog een upgrade voor de lijst
    }

    /*
     * Add to-do lists to the list
     */
    public void create_list (String todo_list_name) {

        // make new item
        TodoList todo_list = new TodoList(todo_list_name);

        // set title for the new item
        todo_list.setTitle(todo_list_name);

        // add item list to item list list
        todo_list_list.add(todo_list);
    }

    /*
     * Add to-do items to the list
     */
    public void create_item (String todo_item_name) {

        // make new item
        TodoItem todo_item = new TodoItem(todo_item_name);

        // set title for the new item
        todo_item.setTitle(todo_item_name);

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to the list and insert into table
        values.put("todo_text", todo_item.getTitle());
        values.put("current_color", todo_item.getCurrentColor());
        db.insert(TABLE, null, values);
        db.close();

        // add list to put items in?
//        ArrayList<String> new_list = new ArrayList<>();
//
//        todo_list.setList(new_list);

    }

    /*
     * Read through the database
     */
    public ArrayList<HashMap<String, String>> read_item() {

        // initialize database for reading
        SQLiteDatabase db = getReadableDatabase();

        // select id and item from the table
        String query = "SELECT _id "+"todo_text "+ " current_color"+ " FROM"+ TABLE;

        ArrayList<HashMap<String, String>> todo = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        // for every item in the list, read id and to-do
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> todo_list = new HashMap<>();
                todo_list.put("id", cursor.getString(cursor.getColumnIndex(" id")));
                todo_list.put("todo_text", cursor.getString
                        (cursor.getColumnIndex("todo_text")));
                todo_list.put("current_color", cursor.getString
                        (cursor.getColumnIndex("current_color")));
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
    public void update_item(TodoItem todo_item) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to list and add to the table
        values.put("todo_text", todo_item.getTitle());
        values.put("current_color", todo_item.getCurrentColor());

        // change data in the database for specific id --- MAKE A COUNTER FOR THE ID SOMEWHERE
        db.update(TABLE, values, " id = ? ", new String[] {String.valueOf(todo_item.getId())});
        db.close();
    }

//    public void update_item_color(TodoItem todo_item_color) {
//
//        // get item's current color
//        String current_color = todo_item_color.getCurrentColor();
//
//        // maybe just add this to SQLite
//    }

    /*
     * Delete item from the table
     */
    public void delete_item(int id) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();

        // delete to-do item from the table
        db.delete(TABLE, " _id = ? ", new String[] {String.valueOf(id)});
        db.close();
    }

    public void delete_list(String delete_list) {

        for (int i = 0; i < todo_list_list.size(); i++) {

            if (todo_list_list.get(i).toString().equals(delete_list)) {

                // remove item from list list
                todo_list_list.remove(todo_list_list.get(i));

            }
        }
    }
}
