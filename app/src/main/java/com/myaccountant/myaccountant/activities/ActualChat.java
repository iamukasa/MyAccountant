package com.myaccountant.myaccountant.activities;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.custom.CustomActivity;
import com.myaccountant.myaccountant.model.Conversation;
import com.myaccountant.myaccountant.helpers.Const;
import com.myaccountant.myaccountant.helpers.Helper;
import com.myaccountant.myaccountant.helpers.ParseConstants;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class ActualChat is the Activity class that holds main chat screen. It shows
 * all the conversation messages between two users and also allows the currentUser to
 * send and receive messages.
 */
public class ActualChat extends CustomActivity {

    /**
     * The Conversation list.
     */
    private ArrayList<Conversation> convList;

    /**
     * The chat adapter.
     */
    private ChatAdapter adp;

    /**
     * The Editext to compose the message.
     */
    private EditText txt;

    /**
     * The currentUser name of friendUsername.
     */
    private String friendUsername;

    /**
     * The date of last message in conversation.
     */
    private Date lastMsgDate;

    /**
     * Flag to hold if the activity is running or not.
     */
    private boolean isRunning;

    /**
     * The handler.
     */
    private static Handler handler;
    ParseUser currentUser = ParseUser.getCurrentUser(), friend;

    protected String message;
    private Toolbar toolbar;

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_chat);


        convList = new ArrayList<Conversation>();
        ListView list = (ListView) findViewById(R.id.list);
        adp = new ChatAdapter();
        list.setAdapter(adp);
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); //Makes it scroll to bottom when new message is received.
        list.setStackFromBottom(true);

        txt = (EditText) findViewById(R.id.txt);
        txt.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        setTouchNClick(R.id.btnSend);


        friend = Helper.getUserForChat();
        friendUsername = friend.getUsername();
        getSupportActionBar().setTitle(friendUsername);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler = new Handler();
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
        loadConversationList();
    }


    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    /* (non-Javadoc)
     * @see com.socialshare.custom.CustomFragment#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btnSend) {
            sendMessage();
        }

    }

    /**
     * Call this method to Send message to opponent. It does nothing if the text
     * is empty otherwise it creates a Parse object for ActualChat message and send it
     * to Parse server.
     */
    private void sendMessage() {
        if (txt.length() == 0)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);

        message = txt.getText().toString();
        final Conversation c = new Conversation(message, new Date(),
                currentUser.getUsername());
        c.setStatus(Conversation.STATUS_SENDING);
        convList.add(c);
        adp.notifyDataSetChanged();
        txt.setText(null);

        ParseObject po = new ParseObject(ParseConstants.CLASS_CHAT);
        po.put("sender", currentUser);
        po.put("receiver", friend);
        po.put("conversationId", currentUser.getEmail() + friendUsername);
        po.put("message", message);
        po.saveEventually(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                if (e == null) {
                    c.setStatus(Conversation.STATUS_SENT);
                    if (!friend.getBoolean("online")) {
                        sendPushNotification(friend);
                    }
                } else
                    c.setStatus(Conversation.STATUS_FAILED);
                adp.notifyDataSetChanged();
            }
        });

    }

    private void sendPushNotification(ParseUser friend) {
        ParseQuery<ParseInstallation> query = ParseInstallation.getQuery();
        query.whereEqualTo(ParseConstants.KEY_USER_ID, friend.getObjectId());

        ParsePush push = new ParsePush();
        push.setQuery(query);
        push.setMessage(friend.getUsername() + ": " + message);
        push.sendInBackground();
    }

//    private void sendNormalNotification(String message) {
//        ParseObject notifications = new ParseObject(ParseConstants.CLASS_NOTIFICATIONS);
//        notifications.put("recepient", friend);
//        notifications.put("sender", ParseUser.getCurrentUser());
//        notifications.put("notificationType", Const.NOTIFICATION_TYPE_RECEIVED_MESSAGE);
//        notifications.put("message", ParseUser.getCurrentUser().getUsername() + ": " + message);
//        notifications.put("requestId", friend.getUsername() + ParseUser.getCurrentUser().getUsername() + "message_received");
//        notifications.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//
//                } else {
//                    Helper.makeToast(ActualChat.this, e.getMessage());
//                }
//            }
//        });
//    }

    /**
     * Load the conversation list from Parse server and save the date of last
     * message that will be used to load only recent new messages
     */
    private void loadConversationList() {
        ParseQuery<ParseObject> q = ParseQuery.getQuery(ParseConstants.CLASS_CHAT);
        if (convList.size() == 0) {
            // load all messages...
            ArrayList<ParseUser> al = new ArrayList<>();
            al.add(friend);
            al.add(currentUser);
            q.whereContainedIn("sender", al);
            q.whereContainedIn("receiver", al);
        } else {
            // load only newly received message..
            if (lastMsgDate != null)
                q.whereGreaterThan("createdAt", lastMsgDate);
            q.whereEqualTo("sender", friend);
            q.whereEqualTo("receiver", currentUser);
        }
        q.orderByDescending("createdAt");
        q.setLimit(30);
        q.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> li, ParseException e) {
                if (li != null && li.size() > 0) {
                    for (int i = li.size() - 1; i >= 0; i--) {
                        ParseObject po = li.get(i);
                        Conversation c = null;
                        try {
                            c = new Conversation(po
                                    .getString("message"), po.getCreatedAt(), po
                                    .getParseUser("sender").fetchIfNeeded().getUsername());
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        convList.add(c);
                        if (lastMsgDate == null
                                || lastMsgDate.before(c.getDate()))
                            lastMsgDate = c.getDate();
                        adp.notifyDataSetChanged();
                    }
                }
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (isRunning)
                            loadConversationList();
                    }
                }, 1000);
            }
        });

    }

    /**
     * The Class ChatAdapter is the adapter class for ActualChat ListView. This
     * adapter shows the Sent or Receieved ActualChat message in each list item.
     */
    private class ChatAdapter extends BaseAdapter {

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() {
            return convList.size();
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Conversation getItem(int arg0) {
            return convList.get(arg0);
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView(int pos, View v, ViewGroup arg2) {
            Conversation c = getItem(pos);
            if (c.isSent())
                v = getLayoutInflater().inflate(R.layout.chat_item_sent, null);
            else
                v = getLayoutInflater().inflate(R.layout.chat_item_rcv, null);

            TextView lbl = (TextView) v.findViewById(R.id.lbl1);
            lbl.setText(DateUtils.getRelativeDateTimeString(ActualChat.this, c
                            .getDate().getTime(), DateUtils.SECOND_IN_MILLIS,
                    DateUtils.DAY_IN_MILLIS, 0));

            lbl = (TextView) v.findViewById(R.id.lbl2);
            lbl.setText(c.getMsg());

            lbl = (TextView) v.findViewById(R.id.lbl3);
            if (c.isSent()) {
                if (c.getStatus() == Conversation.STATUS_SENT)
                    lbl.setText("Delivered");
                else if (c.getStatus() == Conversation.STATUS_SENDING)
                    lbl.setText("Sending...");
                else
                    lbl.setText("Failed");
            } else
                lbl.setText("");

            return v;
        }

    }

    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
