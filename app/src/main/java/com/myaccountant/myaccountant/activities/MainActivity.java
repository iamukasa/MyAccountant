package com.myaccountant.myaccountant.activities;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;

import com.myaccountant.myaccountant.ChatBot.BrainLoggerDialog;
import com.myaccountant.myaccountant.ChatBot.MainActivityAI;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.chatmodule.MainActivityChatModule;
import com.myaccountant.myaccountant.data.Account;
import com.myaccountant.myaccountant.helpers.NetworkHandler;
//import com.parse.LogOutCallback;
import com.parse.ParseUser;
import com.rey.material.app.Dialog;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.EditText;

import net.redwarp.library.database.DatabaseHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    ImageView business_info,sales,purchases,expenses,other_income,progress_report,faqs,feedback, logout;
    TextView createAccount;
    ProgressDialog pd;
    ProgressBar home_progress_bar;

    private BrainLoggerDialog dialog;
    private static final String FRAGMENT_DIALOG_LOG_TAG = "BrainLoggerDialog";




    private DialogInterface.OnClickListener mDialogListener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(MainActivity.this, MakePayment.class);
            switch (which){
                case 0:
                    intent.putExtra("limit", 30*60);
                    break;
                case 1:
                    intent.putExtra("limit", 60*60);
                    break;
                case 2:
                    intent.putExtra("limit", 60*60*2);
                    break;
                case 3:
                    intent.putExtra("limit", 60*60*4);
                    break;
                case 4:
                    intent.putExtra("limit", 60*60*6);
                    break;
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getFragmentManager();

//        if (savedInstanceState == null) {
//            Log.d("MainActivity", "onCreate savedInstanceState null");
////            adapter = new ChatArrayAdapter(getApplicationContext());
//
//            dialog = new BrainLoggerDialog();
//            if (!ChatBotApplication.isBrainLoaded()) {
//                dialog.show(fm, FRAGMENT_DIALOG_LOG_TAG);
//            } else {
//                dialog.setPositiveButtonEnabled(true);
//            }
//
//        } else {
//            Log.d("MainActivity", "onCreate savedInstanceState NOT null");
//            dialog = (BrainLoggerDialog) fm.findFragmentByTag(FRAGMENT_DIALOG_LOG_TAG);
//        }

        pd=new ProgressDialog(this);
        //init views
        business_info=(ImageView)findViewById(R.id.business_info);
        sales=(ImageView)findViewById(R.id.sales);
        purchases=(ImageView)findViewById(R.id.purchases);
        expenses=(ImageView)findViewById(R.id.expenses);
        other_income=(ImageView)findViewById(R.id.other_income);
        progress_report=(ImageView)findViewById(R.id.progress_report);
        faqs=(ImageView)findViewById(R.id.faqs);
        feedback=(ImageView)findViewById(R.id.feedback);
        logout =(ImageView)findViewById(R.id.logout);
        createAccount=(TextView)findViewById(R.id.createAccountText);
        home_progress_bar = (ProgressBar) findViewById(R.id.home_progress_bar);
        home_progress_bar.setVisibility(View.INVISIBLE);


//        createAccountDialog();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            navigateToLogin();
        } else {
            //Logged in
        }

        //event listeners
        business_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FrameActivity.class);
                intent.putExtra("category","businessInfo");
                startActivity(intent);
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FrameActivity.class);
                intent.putExtra("category","sales");
                startActivity(intent);
            }
        });
        purchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameActivity.class);
                intent.putExtra("category", "purchases");
                startActivity(intent);
            }
        });
        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameActivity.class);
                intent.putExtra("category", "expenses");
                startActivity(intent);
            }
        });
        other_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameActivity.class);
                intent.putExtra("category", "otherIncome");
                startActivity(intent);
            }
        });
        progress_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameActivity.class);
                intent.putExtra("category", "progressReport");
                startActivity(intent);
            }
        });
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivityAI.class);
                intent.putExtra("category", "faqs");
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityChatModule.class);
                intent.putExtra("category", "feedback");
                startActivity(intent);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Time Limit");
                builder.setItems(R.array.hours, mDialogListener);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                home_progress_bar.setVisibility(View.VISIBLE);
//                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        home_progress_bar.setVisibility(View.INVISIBLE);
//                        if(e == null) {
//                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                        }else {
//                            Helper.showErrorDialog("Logout Error", e.getMessage(), MainActivity.this);
//                        }
//                    }
//                });
//            }
//        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
    public void loginDialog(){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login_layout);
        final EditText email=(EditText)dialog.findViewById(R.id.login_email);
        final EditText password=(EditText)dialog.findViewById(R.id.login_password);
        dialog.positiveAction("LOGIN");
        dialog.negativeAction("CANCEL");
        dialog.setTitle("LOGIN");
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
        dialog.positiveActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper=new DatabaseHelper(MainActivity.this);
                List<Account> items=helper.getAll(Account.class);
                ArrayList<Account> userAccount=(ArrayList)items;
                for(Account account:userAccount){
                    if(account.getEmail().equals(email.getText().toString())){
                        if(account.getPassword().equals(password.getText().toString())){
                            dialog.dismissImmediately();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"incorrect details",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.negativeActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismissImmediately();
            }
        });
    }
    public void createAccountDialog(){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.create_account_layout);
        final EditText email=(EditText)dialog.findViewById(R.id.create_account_email);
        final EditText password=(EditText)dialog.findViewById(R.id.create_account_password);
        CheckBox showPassword=(CheckBox)dialog.findViewById(R.id.create_account_show_password);
        dialog.positiveAction("CREATE");
        //dialog.negativeAction("CANCEL");
        dialog.neutralAction("LOGIN");
        dialog.setCancelable(false);
        dialog.setTitle("CREATE ACCOUNT");
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
        dialog.neutralActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismissImmediately();
                loginDialog();
            }
        });
        dialog.positiveActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
                Account data = new Account();
                List<Account> account = helper.getAll(Account.class);
                if (account.isEmpty()) {
                    data.setEmail(email.getText().toString());
                    data.setPassword(password.getText().toString());
                    helper.save(data);
                    try {
                        String params = "email=" + URLEncoder.encode(email.getText().toString(),"UTF-8")+"&password="+URLEncoder.encode(password.getText().toString(),"UTF-8");
                        new CreateAccount().execute("www.hasofgroup.com/myaccount/logout", params);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "account created successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismissImmediately();
                } else {
                    Toast.makeText(getApplicationContext(), "account already created", Toast.LENGTH_SHORT).show();
                    dialog.dismissImmediately();
                }
            }
        });
        dialog.negativeActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismissImmediately();
            }
        });
    }
    private class CreateAccount extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute(){
            pd.setTitle("Login");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.setMessage("Logging you into My Accountant");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return NetworkHandler.excutePost(params[0],params[1]);
        }
        @Override
        protected void onPostExecute(String result){
            Log.v("RESULT",result);
            pd.hide();
        }
    }
}
