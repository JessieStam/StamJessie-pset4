package jstam.stamjessie_pset4;

/**
 * Created by Jessie on 11/05/2016.
 */
public class TodoItem {

    // field for color
    private String currentColor;

    // field for id
    private int id;

    //field for string
    private String todo_item;

    // constructor for todo_item
    public TodoItem(String new_string) {todo_item = new_string;}

    // constructor for id
    public TodoItem(Integer new_id) {id = new_id;}

    //methods for todo_item
    public String getTitle() {return todo_item;}

    public void setTitle(String new_title) {todo_item = new_title;}

    // methods for color
    public void setColorUnfinished(String unfinished) {currentColor = "unfinished";}

    public void setColorFinished(String finished) {currentColor = "finished";}

    public String getCurrentColor() {return currentColor;}

    // methods for id
    public Integer getId() {return id;}

    public void setId(Integer new_id) {id = new_id;}
}
