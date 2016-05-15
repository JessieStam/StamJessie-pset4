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
public class TodoManager {

    // define list for lists
    private ArrayList<TodoList> todo_list_list = new ArrayList<>();

    // define counter for list id
    private Integer list_id = 0;

    private static TodoManager ourInstance = null;

    // constructor
    public static TodoManager getOurInstance(){

        if (ourInstance == null) {
            ourInstance = new TodoManager();
        }
        return ourInstance;
    }


    /*
     * Add to-do lists to the list
     */
    public TodoList create_list (String todo_list_name) {

        // create new array list for TodoList
        ArrayList<String> new_list = new ArrayList<>();

        // make new TodoList item
        TodoList todo_list = new TodoList(todo_list_name);

        // set title for the new list
        todo_list.setTitle(todo_list_name);

        // set id for the new list
        todo_list.setId(list_id);

        // update id for next list
        list_id += 1;

        // set list for the new list
        todo_list.setList(new_list);

//        new_list = todo_list.getList();
//        Integer new_id = todo_list.getId();

        // add item list to item list list
        todo_list_list.add(todo_list);

        return todo_list;
    }

    /*
     * Add to-do items to the list
     */
    public String create_item (String todo_item_name) {

        // make new item
        TodoItem todo_item = new TodoItem(todo_item_name);

        // set title for the new item
        todo_item.setTitle(todo_item_name);



        // add list to put items in?
//        ArrayList<String> new_list = new ArrayList<>();
//
//        todo_list.setList(new_list);

        return todo_item_name;
    }

//    public void update_item_color(TodoItem todo_item_color) {
//
//        // get item's current color
//        String current_color = todo_item_color.getCurrentColor();
//
//        // maybe just add this to SQLite
//    }


    public void delete_list(String delete_list) {

        for (int i = 0; i < todo_list_list.size(); i++) {

            if (todo_list_list.get(i).toString().equals(delete_list)) {

                // remove item from list list
                todo_list_list.remove(todo_list_list.get(i));

            }
        }
    }
}
