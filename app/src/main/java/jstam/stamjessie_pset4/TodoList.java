package jstam.stamjessie_pset4;

/**
 * Created by Jessie on 13/05/2016.
 */
public class TodoList {

    // vraag dit aan Gracia
    private int id;

    //field
    private String todo_title;

    //constructor
    public TodoList(String new_string) {todo_title = new_string;}

    //methods
    public String getTitle() {return todo_title;}

    public void setTitle(String new_title) {todo_title = new_title;}

    // volgens Gracia moet hier nog een maakt-lijst functie
}
