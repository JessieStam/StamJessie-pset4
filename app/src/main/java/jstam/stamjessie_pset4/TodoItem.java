package jstam.stamjessie_pset4;

/**
 * Created by Jessie on 11/05/2016.
 */
public class TodoItem {

    // vraag dit aan Gracia
    private static int id;

    //field
    private String todo_item;
    private String currentColor;
    private String finished = "finished";
    private String unfinished = "unfinished";

    //constructor
    private TodoItem(String new_string) {todo_item = new_string;}

    //methods
    private String getTitle() {return todo_item;}

    private void setTitle(String new_title) {todo_item = new_title;}

    private void setColor(String currentColor) {

        if (currentColor.equals(unfinished)){
            currentColor = finished;
        }
        else if (currentColor.equals(finished)) {
            currentColor = unfinished;
        }
    }

    private String getColor(String currentColor) {return currentColor;}
}
