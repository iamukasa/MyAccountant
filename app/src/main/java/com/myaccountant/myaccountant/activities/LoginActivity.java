package com.myaccountant.myaccountant.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.helpers.Helper;
import com.myaccountant.myaccountant.helpers.MyAccountantApplication;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    protected EditText username_et, password_et;
    protected Button login_button;
    protected ProgressBar login_progressbar;
    private String username, password;
    private TextView create_account_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_et = (EditText) findViewById(R.id.username_et);
        password_et = (EditText) findViewById(R.id.password_et);
        login_button = (Button) findViewById(R.id.login_button);
        login_progressbar = (ProgressBar) findViewById(R.id.login_progressbar);
        login_progressbar.setVisibility(View.INVISIBLE);
        create_account_tv = (TextView) findViewById(R.id.create_account_tv);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_et.getText().toString().trim();
                password = password_et.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()){
                    Helper.showErrorDialog("Error","Please fill in all the fields", LoginActivity.this);
                }else{
                    login_progressbar.setVisibility(View.VISIBLE);
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            login_progressbar.setVisibility(View.INVISIBLE);
                            if (e == null) {
                                // Hooray! The currentUser is logged in.
                                MyAccountantApplication.updateParseInstallation(parseUser);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                // Signup failed. Look at the ParseException to see what happened.
                                Helper.showErrorDialog("Login Error",e.getMessage(), LoginActivity.this);
                            }
                        }
                    });

                }
            }
        });

        create_account_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
