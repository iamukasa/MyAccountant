package com.myaccountant.myaccountant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.activities.AssetsActivity;
import com.myaccountant.myaccountant.activities.BioDataActivity;
import com.myaccountant.myaccountant.activities.CapitalActivity;
import com.myaccountant.myaccountant.activities.LiabilitiesActivity;

import info.hoang8f.widget.FButton;

/**
 * Created by user on 11/11/2015.
 */
public class BusinessInfoFragment extends Fragment {

    FButton bioData,assets,liabilities,capital;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.business_info_layout,container,false);

        bioData=(FButton)rootView.findViewById(R.id.bio_data_btn);
        assets=(FButton)rootView.findViewById(R.id.assets_btn);
        liabilities=(FButton)rootView.findViewById(R.id.liabilities_btn);
        capital=(FButton)rootView.findViewById(R.id.capital_btn);

        bioData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), BioDataActivity.class);
                startActivity(intent);
            }
        });
        assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AssetsActivity.class);
                startActivity(intent);
            }
        });
        liabilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LiabilitiesActivity.class);
                startActivity(intent);
            }
        });
        capital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CapitalActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
