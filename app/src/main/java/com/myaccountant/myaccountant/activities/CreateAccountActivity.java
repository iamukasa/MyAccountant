package com.myaccountant.myaccountant.activities;

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
import com.myaccountant.myaccountant.helpers.ParseConstants;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccountActivity extends AppCompatActivity {
    protected EditText username_et_create, password_et_create, email_et_create;
    protected Button create_account_button;
    private String username, password, email;
    private ProgressBar create_account_progress;
    private TextView login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username_et_create = (EditText) findViewById(R.id.username_et_create);
        password_et_create= (EditText) findViewById(R.id.password_et_create);
        email_et_create = (EditText) findViewById(R.id.email_et_create);
        login_tv = (TextView) findViewById(R.id.login_tv);
        create_account_button = (Button) findViewById(R.id.create_account_button);
        create_account_progress = (ProgressBar) findViewById(R.id.create_account_progress);
        create_account_progress.setVisibility(View.INVISIBLE);

        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_et_create.getText().toString().trim();
                password = password_et_create.getText().toString().trim();
                email = email_et_create.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Helper.showErrorDialog("Error","Please fill in all the fields", CreateAccountActivity.this);
                }else{
                    create_account_progress.setVisibility(View.VISIBLE);
                    final ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            create_account_progress.setVisibility(View.INVISIBLE);
                            if(e == null) {
                                ParseObject businessInfo = new ParseObject(ParseConstants.CLASS_BUSINESS_INFO);
                                businessInfo.put("user", newUser);
                                businessInfo.saveEventually();

                                MyAccountantApplication.updateParseInstallation(newUser);
                                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }else{
                                Helper.showErrorDialog("Sign up Error", e.getMessage(), CreateAccountActivity.this);
                            }
                        }
                    });
                }
            }
        });

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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
