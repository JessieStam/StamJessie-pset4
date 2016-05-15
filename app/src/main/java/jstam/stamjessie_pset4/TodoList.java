package jstam.stamjessie_pset4;

import java.util.ArrayList;

/**
 * Created by Jessie on 13/05/2016.
 */
public class TodoList {

    // field for id
    private int id;

    //field for item name
    private String todo_title;

    //field for list
    private ArrayList<String> todo_list;

    //constructor
    public TodoList(String new_string) {todo_title = new_string;}

    public TodoList(ArrayList<String> new_list) {todo_list = new_list;}

    public TodoList(Integer new_id) {id = new_id;}

    //methods
    public String getTitle() {return todo_title;}

    public void setTitle(String new_title) {todo_title = new_title;}

    public ArrayList<String> getList() {return todo_list;}

    public Integer getId() {return id;}

    public void setId(Integer new_id) {id = new_id;}

    // public void setList(ArrayList<String> new_list) {todo_list = new_list;}
}
