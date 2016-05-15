package jstam.stamjessie_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DBHelper.java
 *
 * Jessie Stam
 *
 * This class creates a SQLite database in which todoitems are stored.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstdb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo_table";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    // currentStatus starts as unfinished
    String currentStatus = "unfinished";

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table, add id and to-do items
        String query = "CREATE TABLE"+ TABLE+ " (_id"+" INTEGER PRIMARY KEY AUTOINCREMENT" + "todo_text TEXT, current_status TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table and create it again when application is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(TodoItem todo_item) {

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to the list and insert into table
        values.put("todo_text", todo_item.getTitle());
        values.put("current_status", currentStatus);
        db.insert(TABLE, null, values);
        db.close();
    }

    /*
     * Read through the database
     */
    public ArrayList<HashMap<String, String>> read_item() {

        // initialize database for reading
        SQLiteDatabase db = getReadableDatabase();

        // select id and item from the table
        String query = "SELECT _id "+"todo_text "+ " current_status"+ " FROM"+ TABLE;

        ArrayList<HashMap<String, String>> todo = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        // for every item in the list, read id and to-do
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> todo_list = new HashMap<>();
                todo_list.put("id", cursor.getString(cursor.getColumnIndex(" id")));
                todo_list.put("todo_text", cursor.getString
                        (cursor.getColumnIndex("todo_text")));
                todo_list.put("current_status", cursor.getString
                        (cursor.getColumnIndex("current_status")));
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
    public void update(TodoItem todo_item) {

        // change currentStatus
        if (currentStatus.equals("unfinished")) {
            currentStatus = "finished";
        }
        else if (currentStatus.equals("finished")) {
            currentStatus = "unfinished";
        }

        // initialize database for writing
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // add to-do item to list and add to the table
        values.put("todo_text", todo_item.getTitle());
        values.put("current_status", currentStatus);

        // change data in the database for specific id --- MAKE A COUNTER FOR THE ID SOMEWHERE
        db.update(TABLE, values, " id = ? ", new String[] {String.valueOf(todo_item.getId())});
        db.close();
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
}
