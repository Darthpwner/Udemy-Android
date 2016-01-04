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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

//ActionBarActivity is deprecated
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ParseUser.logOut();   //Logs the user out

      if (ParseUser.getCurrentUser() != null) {
          Log.i("currentUser", "User logged in");
      } else {
          Log.i("currentUser", "Not logged in");
      }

//      ParseUser.logInInBackground("Matthew", "myPass", new LogInCallback() {
//          @Override
//          public void done(ParseUser parseUser, ParseException e) {
//              //parseUser object has been returned
//              if(parseUser != null) {
//                  Log.i("logIn", "Successful");
//              } else {  //Can fail if data is inputted incorrectly OR no Internet
//                  Log.i("logIn", "Unsuccessful");
//                  e.printStackTrace();
//              }
//          }
//      });

//      ParseUser user = new ParseUser();
//
//      //Required
//      user.setUsername("Matthew Lin");
//      user.setPassword("myPass");
//
//      //Optional
//      user.setEmail("hello");
//
//      user.put("phone number", 911);
//
//      //Verifies if sign up was successful
//      user.signUpInBackground(new SignUpCallback() {
//          @Override
//          public void done(ParseException e) {
//              if(e == null) {
//                  Log.i("signUp", "Successful");
//              } else {
//                  Log.i("signUp", "Failed");
//                  e.printStackTrace();
//              }
//          }
//      });

//      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//      query.whereEqualTo("username", "Matthew");
//      query.setLimit(1);
//
//      query.findInBackground(new FindCallback<ParseObject>() {
//          @Override
//          public void done(List<ParseObject> objects, ParseException e) {
//              if(e == null) {
//                  if(objects.size() > 0) {
//                      objects.get(0).put("score", 69);   //ParseObject so you can do the usual stuff
//                      objects.get(0).saveInBackground();
//                  }
//              }
//          }
//      });

//      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//
//      query.whereEqualTo("username", "Matthew");
//      query.setLimit(1);    //Make sure we don't return unnecessary users
//
//      query.findInBackground(new FindCallback<ParseObject>() {
//          @Override
//          public void done(List<ParseObject> objects, ParseException e) {
//              if(e == null) {
//                  Log.i("findInBackground", "Retrieved" + objects.size() + " results");
//
//                  for(ParseObject object: objects) {    //Loops through objects list, which contains ParseObjects
//                      Log.i("findInBackgroundUser", String.valueOf(object.get("username")));
//
//                  }
//              }
//          }
//      });



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
//      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//
//      query.getInBackground("0j5g5c7tda", new GetCallback<ParseObject>() {
//          @Override
//          public void done(ParseObject parseObject, ParseException e) {
//              if(e == null) {
//                  parseObject.put("score", 200);
//                  parseObject.put("username", "Matthew");
//                  parseObject.saveInBackground();
//              }
//          }
//      });

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
