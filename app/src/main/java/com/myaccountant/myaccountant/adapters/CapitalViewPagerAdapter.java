package com.myaccountant.myaccountant.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myaccountant.myaccountant.fragments.CompanyListFragment;
import com.myaccountant.myaccountant.fragments.PartnershipListFragment;
import com.myaccountant.myaccountant.fragments.SoleProprietorshipListFragment;

/**
 * Created by user on 11/11/2015.
 */
public class CapitalViewPagerAdapter extends FragmentStatePagerAdapter {

    String[] pageTitles={"SOLE PROPRIETORSHIP","PARTNERSHIP","COMPANY"};

    public CapitalViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new SoleProprietorshipListFragment();
        }else if(position==1){
            return new PartnershipListFragment();
        }else if(position==2){
            return new CompanyListFragment();
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
