package com.myaccountant.myaccountant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.helpers.Helper;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ConsultantList extends AppCompatActivity {
    private ListView consultant_lv;
    private TextView empty_consultant_tv;
    String[] usernames;
    private List<ParseUser> mConsultantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_list);

        consultant_lv = (ListView) findViewById(R.id.consultant_lv);
        empty_consultant_tv = (TextView) findViewById(R.id.empty_consultant_tv);

        consultant_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Helper.setUserForChat(mConsultantList.get(position));
                Intent intent = new Intent(ConsultantList.this, ActualChat.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ParseQuery<ParseUser> consultantQuery = ParseUser.getQuery();
        consultantQuery.whereEqualTo("userType", 1);
        consultantQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> consultants, ParseException e) {
                if (e == null) {
                    mConsultantList = consultants;
                    usernames = new String[consultants.size()];
                    int i = 0;
                    for (ParseUser consultant : consultants) {
                        usernames[i] = consultant.getUsername();
                    }
                    if (consultants.isEmpty()) {
                        empty_consultant_tv.setText("No consultants available");
                    } else {
                        consultant_lv.setAdapter(new ArrayAdapter<String>(ConsultantList.this, android.R.layout.simple_list_item_1, usernames));
                    }
                } else {

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items      the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consultant_list, menu);
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
