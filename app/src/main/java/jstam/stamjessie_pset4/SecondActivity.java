package jstam.stamjessie_pset4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Jessie on 12/05/2016.
 */
public class SecondActivity extends MainActivity {

    String todo_item;
    String currentColor;
    String finished = "finished";
    String unfinished = "unfinished";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_screen);

        currentColor = unfinished;

        screen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // if item is selected, change color to gray
                if (currentColor.equals(unfinished)) {
                    screen_list.getChildAt(position).setBackgroundColor(Color.GRAY);
                    currentColor = finished;
                }
                // if item is not selected, change color back to white
                else if (currentColor.equals(finished)) {
                    screen_list.getChildAt(position).setBackgroundColor(Color.WHITE);
                    currentColor = unfinished;
                }
            }
        });

        /*
         * set long click listener for removing items
         */
        screen_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View string, int position, long id) {

                // remove the item at the touched position and update data
                todo_list_list.remove(position);
                todoAdapter.notifyDataSetChanged();

                //remove title from the SQLite
                TodoManager.delete(TodoItem.id);

                return true;
            }
        });


    }

    /*
    * Adds an item to the list
    */
    public void addToList(View view) {

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        todo_item = user_input.getText().toString();

        TodoItem new_item = new TodoItem(todo_item);

        // add user input to ListView
        todo_list_list.add(new_item);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        // add title to SQLite
        TodoManager.create(todo_item);

        // clear the input line after text is added
        user_input.getText().clear();
    }

}
