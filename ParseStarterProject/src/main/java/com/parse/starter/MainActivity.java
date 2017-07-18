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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signup;
    Button signin;
    Boolean methodname;

    public void showUserList() {

        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    public void signin(View view) {
        methodname = true;
        if (username.getText().toString().matches("") || password.getText().toString().matches("")) {
            Toast.makeText(this, "Enter both username and password", Toast.LENGTH_SHORT).show();
        }
        // signup.setVisibility(View.INVISIBLE);
        //signin.setVisibility(View.INVISIBLE);
        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i("Login", "Successful");
                    Toast.makeText(MainActivity.this, "Signin successful::Username is: " + ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_SHORT).show();
                    showUserList();
                } else {
                    //Log.i("Login","Failed:"+e.toString());
                    Toast.makeText(MainActivity.this, "Signin failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void signUporIn(View view) {


        methodname = false;


        if (username.getText().toString().matches("") || password.getText().toString().matches("")) {
            Toast.makeText(this, "Enter both username and password", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser user = new ParseUser();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                        showUserList();

                    } else {
                        Toast.makeText(MainActivity.this, "Signup failed" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

            signin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);


        //ParseUser.logOut();
        if (ParseUser.getCurrentUser() != null) {
            showUserList();
            Log.i("Current user :->>>", "User logged in:" + ParseUser.getCurrentUser().getUsername());
        } else {
            Log.i("current user", "Not loggedIn");
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}






























//    query.whereEqualTo("username","don");
//    query.setLimit(1);


        //--------------------------------------------- code to collect all the data in a class from the parse server
 /*   query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {

        if (e == null) {

          Log.i("find in background", "retreived" + objects.size() + " objects");
          if (objects.size() > 0) {
            for (ParseObject object : objects) {
              if (object.getInt("score") > 50) {
                object.put("score", object.getInt("score") + 50);
                object.saveInBackground();
              }
              Log.i("findinbackgroundResult", Integer.toString(object.getInt("score")));
              // Log.i("findinbackgroundResult",object.getString("score"));
            }
          } else {
            Log.i("Error:", e.toString());
          }
        }
      }
    });
*/

//-------------------------------code to put data into the parse server db
    /*
    ParseObject score = new ParseObject("Score");
    score.put("username","Sharath");
    score.put("score",90);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          Log.i("save in background","successful sharath");

        }
        else
        {
          Log.i("save in background","Failed: error:-> "+ e.toString());
        }
      }
    });
*/


//--------------------------------------- code to update & get the data wrt id from parse server
/*    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
    query.getInBackground("eNVZ7pJ3Ai", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null && object!=null){

          object.put("score",999);
          object.saveInBackground();
          Log.i("obj value",object.getString("username"));
          Log.i("obj value",Integer.toString(object.getInt("score")));
        }
      }
    });

  }*/



//    @Override
//    public boolean onKey(View view, int i, KeyEvent keyEvent) {
//
//        if (i == KeyEvent.KEYCODE_ENTER) {
//            if (methodname) {
//                signin(view);
//            } else {
//                signUporIn(view);
//            }
//        }
//        return false;
//    }

