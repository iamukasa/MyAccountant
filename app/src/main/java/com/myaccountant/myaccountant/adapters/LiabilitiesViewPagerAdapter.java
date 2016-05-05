package com.myaccountant.myaccountant.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myaccountant.myaccountant.fragments.CurrentLiabilityFragment;
import com.myaccountant.myaccountant.fragments.CurrentLiabilityListFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentLiabilityFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentLiabilityListFragment;

/**
 * Created by user on 11/11/2015.
 */
public class LiabilitiesViewPagerAdapter extends FragmentStatePagerAdapter {

    String[] pageTitles={"CURRENT LIABILITY","NON CURRENT LIABILITY"};

    public LiabilitiesViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new CurrentLiabilityListFragment();
        }else if(position==1){
            return new NonCurrentLiabilityListFragment();
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
