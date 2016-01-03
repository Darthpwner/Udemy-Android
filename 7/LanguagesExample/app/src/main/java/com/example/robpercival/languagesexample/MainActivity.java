package com.example.robpercival.languagesexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textView;

    public void showAlert() {

        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.robpercival.languagesexample", Context.MODE_PRIVATE);

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Which Language Do You Want?")
                .setMessage("Do you want English or Spanish?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("language", "english").apply();
                        textView.setText("english");
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("language", "spanish").apply();
                        textView.setText("spanish");
                    }
                })
                .show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.robpercival.languagesexample", Context.MODE_PRIVATE);

        String chosenLanguage = sharedPreferences.getString("language", "");

        if (chosenLanguage == "") {

            showAlert();

        } else {

            textView.setText(chosenLanguage);

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
        if (id == R.id.changeLanguage) {

            showAlert();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
