package com.myaccountant.myaccountant.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.adapters.AssetsViewPagerAdapter;
import com.myaccountant.myaccountant.fragments.CurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentAssetsFragment;

public class AssetsActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerSlidingTabStrip pagerSlidingTabStrip;
    AssetsViewPagerAdapter assetsViewPagerAdapter;
    String selectedTab="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        selectedTab="currentAssets";

        getSupportActionBar().setTitle("Assets");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        pagerSlidingTabStrip=(PagerSlidingTabStrip)findViewById(R.id.pager_title_strip);
        assetsViewPagerAdapter=new AssetsViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(assetsViewPagerAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);

        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    selectedTab="nonCurrentAssets";
                }else if(position==0){
                    selectedTab="currentAssets";
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
        getMenuInflater().inflate(R.menu.menu_assets, menu);
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
            if(selectedTab.equalsIgnoreCase("currentAssets")){
                Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
                intent.putExtra("category","currentAssets");
                startActivity(intent);
            }else{
                Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
                intent.putExtra("category", "nonCurrentAssets");
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
