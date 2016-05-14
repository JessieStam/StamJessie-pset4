package jstam.stamjessie_pset4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jessie on 12/05/2016.
 */
public class SecondActivity extends MainActivity {

    ArrayList<String> todo_item_list;
    ListView screen_item_list;
    ArrayAdapter<String> todoItemAdapter;
    EditText user_input;

    String todo_item;
    String currentColor;
    String finished = "finished";
    String unfinished = "unfinished";
    EditText user_input_item;

    TodoManager todo_manager = TodoManager.getOurInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_screen);

        screen_item_list = new ListView(this);
        screen_item_list = (ListView) findViewById(R.id.itemList);
        todo_item_list = new ArrayList<>();
        todoItemAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_item_list);
        currentColor = unfinished;
        user_input_item = (EditText) findViewById(R.id.user_input_item);

        screen_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // if item is selected, change color to gray
                if (currentColor.equals(unfinished)) {
                    screen_item_list.getChildAt(position).setBackgroundColor(Color.GRAY);
                    currentColor = finished;

                    // todo_manager.update(screen_list);
                }
                // if item is not selected, change color back to white
                else if (currentColor.equals(finished)) {
                    screen_item_list.getChildAt(position).setBackgroundColor(Color.WHITE);
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


                //deze werkt nog niet

                // remove the item at the touched position and update data
                todo_item_list.remove(position);
                todoItemAdapter.notifyDataSetChanged();

                //remove title from the SQLite
                // TodoManager.delete(todo_item_list);

                return true;
            }
        });


    }

    /*
    * Adds an item to the list
    */
    public void addToListItem(View view) {

        // use adapter to put todo_list information to screen_list
        screen_item_list.setAdapter(todoItemAdapter);

        todo_item = user_input_item.getText().toString();

        //TodoItem new_item = new TodoItem(todo_item);

        // add user input to ListView
        todo_item_list.add(todo_item);

        // refresh ListView
        todoItemAdapter.notifyDataSetChanged();

        // add title to SQLite
        // todo_manager.create(todo_item);

        // clear the input line after text is added
        user_input_item.getText().clear();
    }

}
