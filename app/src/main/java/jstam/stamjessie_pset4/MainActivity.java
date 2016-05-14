package jstam.stamjessie_pset4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todo_list_list;
    ListView screen_list;
    ArrayAdapter<String> todoAdapter;
    EditText user_input;
    String todo_title;

    // TodoManager todo_manager = TodoManager.getOurInstance();

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
         * set onclick listener for ListView items to check/uncheck them
         */
        screen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //hier moet je nog een getChildAtPosition doen om te bepalen welke lijst je wilt -> uit SQLite halen
                screen_list.getChildAt(position);

                // ga naar een nieuwe activity
                Intent listItems = new Intent(view.getContext(), SecondActivity.class);
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

                //remove title from the SQLite
                // todo_manager.delete(TodoList.getTitle(todo_title));

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

        todo_title = user_input.getText().toString();

        // TodoList new_title = new TodoList(todo_title);

        // add user input to ListView
        todo_list_list.add(todo_title);

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        // add title to SQLite
        // todo_manager.create(todo_title);

        // clear the input line after text is added
        user_input.getText().clear();
    }
}
