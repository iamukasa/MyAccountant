package com.myaccountant.myaccountant.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.helpers.ParseConstants;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class BioDataActivity extends AppCompatActivity {
    private TextView name_tv, size_tv,location_tv,structure_tv, description_tv;
    private ListView partner_lv;
    private String name, size, location, structure, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_data);

        getSupportActionBar().setTitle("Bio Data");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));

        name_tv = (TextView) findViewById(R.id.name_tv);
        size_tv = (TextView) findViewById(R.id.size_tv);
        location_tv = (TextView) findViewById(R.id.location_tv);
        structure_tv = (TextView) findViewById(R.id.structure_tv);
        description_tv = (TextView) findViewById(R.id.description_tv);
        partner_lv = (ListView) findViewById(R.id.partner_lv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseConstants.CLASS_BUSINESS_INFO);
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    name = object.getString("name");
                    name_tv.setText("Name: " + name);

                    size = object.getString("size");
                    size_tv.setText("Size: " + size);

                    location = object.getString("location");
                    location_tv.setText("Location: " + location);

                    structure = object.getString("structure");
                    structure_tv.setText("Structure: " + structure);

                    description = object.getString("description");
                    description_tv.setText("Description: " + description);

                } else {

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bio_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
            intent.putExtra("category","bioData");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
