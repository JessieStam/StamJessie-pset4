package jstam.stamjessie_pset4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jessie on 12/05/2016.
 */
public class SecondActivity extends MainActivity {

    ArrayList<String> todo_item_list;
    ListView screen_item_list;
    ArrayAdapter<String> todoItemAdapter;

    String todo_item;
    String currentStatus;
    String finished = "finished";
    String unfinished = "unfinished";
    EditText user_input_item;
    String list_name;
    TextView list_textbox;

    TodoManager todo_manager = TodoManager.getOurInstance();
    DBHelper db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_screen);

        Bundle extraInfo = getIntent().getExtras();
        list_name = extraInfo.getString("list_name");
        todo_list_list = extraInfo.getStringArrayList("todo_list_list");

        list_textbox = (TextView) findViewById(R.id.todoListTitle);

        list_textbox.setText(list_name);

        screen_item_list = new ListView(this);
        screen_item_list = (ListView) findViewById(R.id.itemList);
        todo_item_list = new ArrayList<>();
        todoItemAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_item_list);
        currentStatus = unfinished;
        user_input_item = (EditText) findViewById(R.id.user_input_item);

        db_helper = new DBHelper(this);

        screen_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // if item is selected, change color to gray
                if (currentStatus.equals(unfinished)) {
                    screen_item_list.getChildAt(position).setBackgroundColor(Color.GRAY);
                    currentStatus = finished;

                    // todo_manager.update(screen_list);
                }
                // if item is not selected, change color back to white
                else if (currentStatus.equals(finished)) {
                    screen_item_list.getChildAt(position).setBackgroundColor(Color.WHITE);
                    currentStatus = unfinished;
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
                todo_item_list.remove(position);
                todoItemAdapter.notifyDataSetChanged();

                //remove title from the SQLite
                db_helper.delete((int) id);

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

        // get item for the list
        todo_item = user_input_item.getText().toString();

        // create new item
        String new_item = todo_manager.create_item(todo_item);

        // add user input to ListView
        todo_item_list.add(new_item);

        // refresh ListView
        todoItemAdapter.notifyDataSetChanged();

        // clear the input line after text is added
        user_input_item.getText().clear();
    }

}
