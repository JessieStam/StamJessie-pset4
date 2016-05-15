package jstam.stamjessie_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todo_list_list;
    ListView screen_list;
    ArrayAdapter<String> todoAdapter;
    EditText user_input;
    String todo_title;

    TodoManager todo_manager = TodoManager.getOurInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_input = (EditText) findViewById(R.id.user_input);
        screen_list = new ListView(this);
        screen_list = (ListView) findViewById(R.id.titleList);
        todo_list_list = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_list_list);

        /*
         * set onclick listener for ListView lists to open them
         */
        screen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //hier moet je nog een getChildAtPosition doen om te bepalen welke lijst je wilt -> uit SQLite halen
                // screen_list.getChildAt(position);

                // remember the name of the clicked list
                String title = screen_list.getItemAtPosition(position).toString();

                // move to SecondActivity
                Intent listItems = new Intent(view.getContext(), SecondActivity.class);

                listItems.putExtra("list_name", title);

                startActivity(listItems);

                // Jessie, kijk er nog even naar of je dit moet afsluiten of niet (volgens Hella waarschijnlijk niet)
                // finish();
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

                // define which list to remove for todo_list_list
                String delete_list = screen_list.getItemAtPosition(position).toString();

                // remove the item from the todo_list_list
                todo_manager.delete_list(delete_list);

                return true;
            }
        });
    }

    /*
     * Save data for when screen is rotated or application is shut down
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save to-do items
        todo_title = user_input.getText().toString();

        outState.putString("todo_title", todo_title);
        //outState.putStringArrayList("todo_list", todo_list_save);
    }

    /*
     * Restore data after screen is rotated or application is restarted
     */
    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        // restore to-do items
        user_input.setText(saveInstanceState.getString("todo_title"));
        //todo_list = saveInstanceState.getStringArrayList("todo_list");
    }


    /*
    * Adds an item to the list
    */
    public void addToList(View view) {

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        // get title for the list
        todo_title = user_input.getText().toString();

        // create a new list item
        todo_manager.create_list(todo_title);

        // add user input to ListView --- deze moet blijven staan, want todo_list_list is hier de listview, misschien nog anders
        todo_list_list.add(todo_title);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        // clear the input line after text is added
        user_input.getText().clear();
    }
}
