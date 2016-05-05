package com.myaccountant.myaccountant.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myaccountant.myaccountant.fragments.CurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.CurrentAssetsListFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentAssetListFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentAssetsFragment;

/**
 * Created by user on 11/11/2015.
 */
public class AssetsViewPagerAdapter extends FragmentStatePagerAdapter {

    String[] pageTitles={"CURRENT ASSETS","NON CURRENT ASSETS"};

    public AssetsViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new CurrentAssetsListFragment();
        }else if(position==1){
            return new NonCurrentAssetListFragment();
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
