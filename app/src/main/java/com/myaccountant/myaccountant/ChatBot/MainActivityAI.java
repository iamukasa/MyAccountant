package com.myaccountant.myaccountant.ChatBot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;

import java.lang.reflect.Field;
import java.util.Locale;


public class MainActivityAI extends ActionBarActivity{

    private static final String FRAGMENT_DIALOG_LOG_TAG = "BrainLoggerDialog";

    private ListView chatListView;
    private static ChatArrayAdapter adapter;
    private EditText chatEditText;
    private BrainLoggerDialog dialog;
    private ResponseReceiver mMessageReceiver;
    TextToSpeech tts;
    static final int SET_SOUNDS = 1;
    SharedPreferences mAIRvingSettings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainai);
        setToolBar();
        mAIRvingSettings =
                getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorOne = mAIRvingSettings.edit();
        editorOne.putString(Constants.PREF_SOUND, "true");
        editorOne.commit();


        FragmentManager fm = getFragmentManager();

//        if (savedInstanceState == null) {
//            Log.d("MainActivity", "onCreate savedInstanceState null");
            adapter = new ChatArrayAdapter(getApplicationContext());

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

        chatListView = (ListView) findViewById(R.id.chat_listView);
        chatListView.setAdapter(adapter);

        chatEditText = (EditText) findViewById(R.id.chat_editText);
        chatEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    final String question = chatEditText.getText().toString();
                    adapter.add(new ChatMessage(false, question));
                    chatEditText.setText("");

                    Intent brainIntent = new Intent(MainActivityAI.this, BrainService.class);
                    brainIntent.setAction(BrainService.ACTION_QUESTION);
                    brainIntent.putExtra(BrainService.EXTRA_QUESTION, question);
                    startService(brainIntent);

                    return true;
                }

                return false;
            }
        });

        //hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdminDetails);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
            setUpActionbar();
            getOverflowMenu();
            toolbar.setBackgroundColor(getResources().getColor(R.color.green));
            toolbar.setTitleTextColor(getResources().getColor(R.color.white_pure));
        }

    }

    private void setUpActionbar() {
        if(getSupportActionBar()!=null){
            ActionBar bar = getSupportActionBar();
            bar.setTitle(getResources().getString(R.string.app_name));
            bar.setHomeButtonEnabled(false);
            bar.setDisplayShowHomeEnabled(false);
            bar.setDisplayHomeAsUpEnabled(false);
            bar.setDisplayShowTitleEnabled(true);
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }


    }
    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);

            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

            if(menuKeyField != null) {

                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
                menuKeyField.isSynthetic();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Register mMessageReceiver to receive messages.
        IntentFilter intentFilter = new IntentFilter(
                Constants.BROADCAST_ACTION_BRAIN_STATUS);
        intentFilter.addAction(Constants.BROADCAST_ACTION_BRAIN_ANSWER);
        intentFilter.addAction(Constants.BROADCAST_ACTION_LOGGER);

        mMessageReceiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, intentFilter);

        if (dialog != null && ChatBotApplication.isBrainLoaded()) {

            dialog.loadLog();
            dialog.setPositiveButtonEnabled(true);
        }
    }

    @Override
    protected void onPause() {
        // Unregister since the activity is not visible
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_log) {
            FragmentManager fm = getFragmentManager();
            dialog = new BrainLoggerDialog();
            dialog.show(fm, FRAGMENT_DIALOG_LOG_TAG);
            return true;
        }
        if(id==R.id.sound_setting){
            MainActivityAI.this.showDialog(SET_SOUNDS);
        }

        return super.onOptionsItemSelected(item);
    }


    // Broadcast receiver for receiving status updates from the IntentService
    private class ResponseReceiver extends BroadcastReceiver {

        private ResponseReceiver() {
        }

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equalsIgnoreCase(Constants.BROADCAST_ACTION_BRAIN_STATUS)) {

                int status = intent.getIntExtra(Constants.EXTRA_BRAIN_STATUS, 0);
                switch (status) {

                    case Constants.STATUS_BRAIN_LOADING:
                        Toast.makeText(MainActivityAI.this, "brain loading", Toast.LENGTH_SHORT).show();
                        if (dialog != null) {
                            dialog.show(getFragmentManager(), FRAGMENT_DIALOG_LOG_TAG);
                        }
                        break;

                    case Constants.STATUS_BRAIN_LOADED:
                        Toast.makeText(MainActivityAI.this, "brain loaded", Toast.LENGTH_SHORT).show();
                        if (dialog != null) {
                            dialog.setPositiveButtonEnabled(true);
                        }
                        break;

                }
            }

            if (intent.getAction().equalsIgnoreCase(Constants.BROADCAST_ACTION_BRAIN_ANSWER)) {
                final String answer = intent.getStringExtra(Constants.EXTRA_BRAIN_ANSWER);
                adapter.add(new ChatMessage(true, answer));
                adapter.notifyDataSetChanged();
                final String det=mAIRvingSettings.getString(Constants.PREF_SOUND,null);

                if(  det != null & det.equals("true")){
                    tts = new TextToSpeech(MainActivityAI.this, new TextToSpeech.OnInitListener() {

                        @Override
                        public void onInit(int status) {
                            // TODO Auto-generated method stub
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.UK);
                                tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null);
                            }

                        }
                    });

                }

            }

            if (intent.getAction().equalsIgnoreCase(Constants.BROADCAST_ACTION_LOGGER)) {

                String info = intent.getStringExtra(Constants.EXTENDED_LOGGER_INFO);
                if (info != null) {
                    Log.i("EXTENDED_LOGGER_INFO", info);
                    if (dialog != null) {
                        dialog.addLine(info);
                    }
                }

            }


        }
    }
    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id) {
            case SET_SOUNDS:
                final LayoutInflater inflater2 =
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View layoutset =
                        inflater2.inflate(R.layout.dialog_sounds,
                                (ViewGroup) findViewById(R.id.dia_sounds));

                final CheckBox g = (CheckBox) layoutset.findViewById(R.id.checkBoxSounds);
                if(mAIRvingSettings.contains(Constants.PREF_SOUND)
                        &
                        mAIRvingSettings.getString(Constants.PREF_SOUND,null).equals("true")){
                    g.setChecked(true);
                }



                g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                        if (isChecked) {
                            final SharedPreferences.Editor editor22 = mAIRvingSettings.edit();
                            editor22.putString(Constants.PREF_SOUND, "true");
                            editor22.commit();

                            g.setChecked(true);

                        }
                        else if (!isChecked) {
                            final SharedPreferences.Editor editor22 = mAIRvingSettings.edit();
                            editor22.putString(Constants.PREF_SOUND, "false");
                            editor22.commit();
                            g.setChecked(false);




                        }


                    }
                });

                final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setView(layoutset);

                builder2.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int which) {
                                MainActivityAI.this.removeDialog(SET_SOUNDS);



                            }
                        });
                final AlertDialog lDialog1 = builder2.create();
                return lDialog1;


        }
        return null;
    }


}
