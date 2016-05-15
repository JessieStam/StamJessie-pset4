package jstam.stamjessie_pset4;

import java.util.ArrayList;


/**
 * TodoManager.java
 *
 * Jessie Stam
 *
 * This singleton class contains functions that help to create todolists and todoitems.
 */
public class TodoManager {

    // define list for lists
    private ArrayList<TodoList> todo_list_list = new ArrayList<>();

    // define counter for list id
    private Integer list_id = 0;

    // define counter for item id
    private Integer item_id = 0;

    private static TodoManager ourInstance = null;

    // constructor
    public static TodoManager getOurInstance(){

        if (ourInstance == null) {
            ourInstance = new TodoManager();
        }
        return ourInstance;
    }

    /*
     * Adds to-do lists to the list
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

        // add item list to item list list
        todo_list_list.add(todo_list);

        return todo_list;
    }

    /*
     * Adds to-do items to the list
     */
    public TodoItem create_item (String todo_item_name) {

        // make new item
        TodoItem todo_item = new TodoItem(todo_item_name);

        // set title for the new item
        todo_item.setTitle(todo_item_name);

        // set id for the new item
        todo_item.setId(item_id);

        // update id for next item
        item_id += 1;

        return todo_item;
    }

    /*
     * Iterates over todo_list_list and checks if it's the list to be deleted
     */
    public void delete_list(String delete_list) {

        for (int i = 0; i < todo_list_list.size(); i++) {

            if (todo_list_list.get(i).toString().equals(delete_list)) {

                // remove item from list list
                todo_list_list.remove(todo_list_list.get(i));

            }
        }
    }
}
