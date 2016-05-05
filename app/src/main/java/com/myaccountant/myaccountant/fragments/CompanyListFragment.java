package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.myaccountant.myaccountant.R;

/**
 * Created by user on 11/10/2015.
 */
public class CompanyListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.company_list_layout,container,false);
        ListView listView=(ListView)rootView.findViewById(R.id.company_list);

        return rootView;
    }
}
