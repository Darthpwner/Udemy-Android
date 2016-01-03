package matthewallenlinsoftware.notesmatt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.HashMap;


public class EditYourNote extends Activity implements TextWatcher {

    int noteId;
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent i = getIntent();
        noteId = i.getIntExtra("noteId", -1);

        if(noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId));

        }
        editText.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_your_note, menu);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        MainActivity.notes.set(noteId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("matthewallenlinsoftware.notesmatt", Context.MODE_PRIVATE);

        if(MainActivity.set == null) {
            MainActivity.set = new HashMap<String>();
        } else {
            MainActivity.set.clear();
        }

        MainActivity.set.clear();
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().putString("notes", set).apply();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
