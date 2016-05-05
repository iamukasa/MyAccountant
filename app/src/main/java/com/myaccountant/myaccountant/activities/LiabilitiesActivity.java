package com.myaccountant.myaccountant.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.adapters.LiabilitiesViewPagerAdapter;
import com.myaccountant.myaccountant.fragments.CurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.CurrentLiabilityFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentLiabilityFragment;

public class LiabilitiesActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerSlidingTabStrip pagerSlidingTabStrip;
    String selectedTab="currentLiabilities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liabilities);

        getSupportActionBar().setTitle("Liabilities");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        pagerSlidingTabStrip=(PagerSlidingTabStrip)findViewById(R.id.pager_title_strip);

        viewPager.setAdapter(new LiabilitiesViewPagerAdapter(getSupportFragmentManager()));
        pagerSlidingTabStrip.setViewPager(viewPager);

        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    selectedTab="currentLiabilities";
                }else if(position==1){
                    selectedTab="nonCurrentLiabilities";
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liabilities, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            if(selectedTab.equalsIgnoreCase("currentLiabilities")){
                //Toast.makeText(getApplicationContext(),selectedTab,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
                intent.putExtra("category", "currentLiabilities");
                startActivity(intent);
            }else if(selectedTab.equalsIgnoreCase("nonCurrentLiabilities")){
                //Toast.makeText(getApplicationContext(),selectedTab,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
                intent.putExtra("category", "nonCurrentLiabilities");
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
