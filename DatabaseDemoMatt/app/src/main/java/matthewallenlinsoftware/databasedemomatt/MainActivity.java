package matthewallenlinsoftware.databasedemomatt;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Create database
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);    //Opens database if it exists or creates it

            //Primary key
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");

            //Put a table in your database
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Rob', 34)"); //Rob has single quotes to show it is a string

            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Matthew', 21)");

            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Tommy', 4)");

            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Christine', 20)");

            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Wendy', 21)");

            myDatabase.execSQL("DELETE FROM users WHERE name = 'Wendy'");

            //Used to query from database, loops through all the results of a particular query
//            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE age < 18", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name = 'Christine' AND age > 18", null);
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%i%' AND age > 18", null);

            myDatabase.execSQL("DELETE FROM users WHERE name = 'Christine'");   //Put LIMIT 1 after DELETE or UPDATE statements
            //myDatabase.execSQL("DELETE FROM users WHERE name = 'Christine' LIMIT 1");   //Cannot do this in SQLite
            myDatabase.execSQL("UPDATE users SET age = 2 WHERE name = 'Christine'");

            //Deal with column indices in Android
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();
            while(c != null) {
                Log.i("UserResults - name", c.getString(nameIndex));
                Log.i("UserResults - age", Integer.toString(c.getInt(ageIndex)));
                Log.i("UserResults - id", Integer.toString(c.getInt(idIndex)));

                c.moveToNext();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);

            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))");

            eventsDB.execSQL("INSERT INTO events (event, year) VALUES ('End of WW2', 1945)");

            eventsDB.execSQL("INSERT INTO events (event, year) VALUES ('Wham split up', 1986)");

            Cursor c = eventsDB.rawQuery("SELECT * FROM events", null);

            int eventIndex = c.getColumnIndex("event");
            int yearIndex = c.getColumnIndex("year");

            c.moveToFirst();

            while(c != null) {
                Log.i("Results - event", c.getString(eventIndex));
                Log.i("Results - year", Integer.toString(c.getInt(yearIndex)));

                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
