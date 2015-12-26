package matthewallenlinsoftware.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ListView myListView = (ListView) findViewById(R.id.myListView); //Can refer to my list view using this
//
//        final ArrayList<String> myFamily = new ArrayList<String>();
//
//        myFamily.add("Christine");
//        myFamily.add("Catrina");
//        myFamily.add("Cody");
//        myFamily.add("Amanda");
//        myFamily.add("Matthew");
//
//        //Display them in the table using 2 lines (uses Array adapter to convert it into a different format)
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);
//        /*Create an array adapter of Objects and converted it*/
//
//        myListView.setAdapter(arrayAdapter);    //Very simple and intuitive way to add code to a table
//        //Should actually have some content in the table
//
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                /*AdapterView is a ListView that has been tapped (we don't know what type of view it was though
//                * in the code (? because it is a generic) */
//                parent.setVisibility(View.GONE);    //Represents that it disappeared
//
//                //id and position are generally the same, but they can vary (usually just use position)
//                Log.i("Person tapped: ", myFamily.get(position));
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView friendsListView = (ListView) findViewById(R.id.myListView);

        final ArrayList<String> myFriends = new ArrayList<String>(Arrays.asList("John", "Paul", "George", "Ringo"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFriends);

        friendsListView.setAdapter(arrayAdapter);

        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hello " + myFriends.get(position), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
