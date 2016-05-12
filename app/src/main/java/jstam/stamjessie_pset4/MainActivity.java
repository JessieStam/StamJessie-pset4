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
    TodoManager TodoManager = new TodoManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_input = (EditText) findViewById(R.id.user_input);
        screen_list = new ListView(this);
        screen_list = (ListView) findViewById(R.id.titleList);
        todoAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_list_list);

        /*
         * set onclick listener for ListView items to check/uncheck them
         */
        screen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ga naar een nieuwe activity
                Intent listItems = new Intent(this, SecondActivity.class);
                startActivity(listItems);

                // Jessie, kijk er nog even naar of je dit moet afsluiten of niet
                finish();
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

        todo_title = user_input.getText().toString();

        TodoItem new_title = new TodoItem(todo_title);

        // add user input to ListView
        todo_list_list.add(new_title);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        // add title to SQLite
        TodoManager.create(todo_title);

        // clear the input line after text is added
        user_input.getText().clear();
    }
}
