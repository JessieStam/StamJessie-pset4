package jstam.stamjessie_pset4;

/**
 * Created by Jessie on 11/05/2016.
 */
public class TodoItem {

    // vraag dit
    public static int id;

    //field
    private String todo_title;

    //constructor
    private TodoItem(String new_string) {todo_title = new_string;}

    //methods
    private String getTitle() {return todo_title;}

    private void setTitle(String new_title) {todo_title = new_title;}

}
