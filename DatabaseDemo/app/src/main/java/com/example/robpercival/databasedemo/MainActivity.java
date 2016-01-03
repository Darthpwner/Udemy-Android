package com.example.robpercival.databasedemo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);


            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");

            //eventsDB.execSQL("INSERT INTO newUsers (name, age) VALUES ('Kirsten', 21)");

            //eventsDB.execSQL("INSERT INTO newUsers (name, age) VALUES ('Ralphie', 1)");

            eventsDB.execSQL("DELETE FROM newUsers WHERE id = 1");

            Cursor c = eventsDB.rawQuery("SELECT * FROM newUsers", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while (c != null) {

                Log.i("UserResults - name", c.getString(nameIndex));
                Log.i("UserResults - age", Integer.toString(c.getInt(ageIndex)));
                Log.i("UserResults - id", Integer.toString(c.getInt(idIndex)));

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
