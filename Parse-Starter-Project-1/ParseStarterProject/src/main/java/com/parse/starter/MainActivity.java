/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

//ActionBarActivity is deprecated
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    EditText usernameField;
    EditText passwordField;
    TextView changeSignUpModeTextView;
    Button signUpButton;
    ImageView logo;
    RelativeLayout relativeLayout;

    Boolean signUpModeActive;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            signUpOrLogin(v);
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        //We want our textView to be the only thing clicked
        if(v.getId() == R.id.changeSignupMode) {
            if (signUpModeActive == true) {
                signUpModeActive = false;
                changeSignUpModeTextView.setText("Sign Up");
                signUpButton.setText("Log In");
            } else {    //Log in mode
                signUpModeActive = true;
                changeSignUpModeTextView.setText("Log In");
                signUpButton.setText("Sign Up");
            }
        } else if (v.getId() == R.id.logo || v.getId() == R.id.relativeLayout) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void signUpOrLogin(View view) {
        if(signUpModeActive == true) {
            ParseUser user = new ParseUser();
            user.setUsername(String.valueOf(usernameField.getText()));
            user.setPassword(String.valueOf(passwordField.getText()));

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null) {
                        Log.i("AppInfo", "Signup Successful");

                        showUserList();
                    } else {
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            ParseUser.logInInBackground(String.valueOf(usernameField.getText()), String.valueOf(passwordField.getText()), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if(parseUser != null) {
                        Log.i("AppInfo", "Login Successful");
                    } else {
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void showUserList() {
        Intent i = new Intent(getApplicationContext(), UserList.class);
        startActivity(i);
    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      if(ParseUser.getCurrentUser() != null) {
          showUserList();
      }

      signUpModeActive = true;

      usernameField = (EditText) findViewById(R.id.username);
      passwordField = (EditText) findViewById(R.id.password);
      changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignupMode);
      signUpButton = (Button) findViewById(R.id.signUpButton);
      logo = (ImageView) findViewById(R.id.logo);
      relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

      changeSignUpModeTextView.setOnClickListener(this);
      logo.setOnClickListener(this);
      relativeLayout.setOnClickListener(this);

      usernameField.setOnKeyListener(this);
      passwordField.setOnKeyListener(this);

//      ParseUser.logOut();   //Logs the user out
//
//      if (ParseUser.getCurrentUser() != null) {
//          Log.i("currentUser", "User logged in");
//      } else {
//          Log.i("currentUser", "Not logged in");
//      }

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
