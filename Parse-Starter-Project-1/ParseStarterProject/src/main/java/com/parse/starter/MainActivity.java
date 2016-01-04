/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

//ActionBarActivity is deprecated
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      /*
      ParseObject score = new ParseObject("Score"); //We are creating a general class called Score, and we are also creating a particular object of that class called "score"
    //Parse checks to see if the Score class exists and creates it if it doesn't exist

      score.put("username", "Matthew"); //Puts variable "username" with value "Matthew"
      score.put("score", 50);  //Puts variable "score" with value 100

      score.put("username", "Christine");
      score.put("score", 100);
      //score.saveInBackground(); //Sends it to the Parse server
      score.saveEventually(new SaveCallback() {
          //done is called when we ask if it saved successfully or not
          @Override
          public void done(ParseException e) {
              if(e == null) {
                  Log.i("SaveInBackground", "Successful");
              } else {
                  Log.i("SaveInBackground", "Failed");
                  e.printStackTrace();
              }
          }
      });   //Tries to save right away but if Internet is not there, it will save it later
*/
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

      query.getInBackground("0j5g5c7tda", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject parseObject, ParseException e) {
              if(e == null) {
                  parseObject.put("score", 200);
                  parseObject.saveInBackground();
              }
          }
      });

      ParseAnalytics.trackAppOpenedInBackground(getIntent()); //This has to be at the end!
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
