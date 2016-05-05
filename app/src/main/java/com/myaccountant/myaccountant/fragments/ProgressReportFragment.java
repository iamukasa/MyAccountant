package com.myaccountant.myaccountant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.activities.FrameActivity;
import com.rey.material.widget.CheckBox;

/**
 * Created by user on 11/8/2015.
 */
public class ProgressReportFragment extends Fragment {
    CheckBox isCurrent,sfpCurrent,scfCurrent;
    TextView isNext,sfpNext,scfNext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.progress_report_layout,container,false);
        isCurrent=(CheckBox)rootView.findViewById(R.id.income_statement_current);
        sfpCurrent=(CheckBox)rootView.findViewById(R.id.sfp_current);
        isNext=(TextView)rootView.findViewById(R.id.income_statement_next);
        sfpNext=(TextView)rootView.findViewById(R.id.sfp_next);
        scfCurrent=(CheckBox)rootView.findViewById(R.id.scf_current);
        scfNext=(TextView)rootView.findViewById(R.id.scf_next);

        isNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(isCurrent.isChecked()){
                   Intent intent=new Intent(getActivity(), FrameActivity.class);
                   intent.putExtra("category","isReport");
                   startActivity(intent);
               }
            }
        });
        sfpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sfpCurrent.isChecked()){
                    Intent intent=new Intent(getActivity(), FrameActivity.class);
                    intent.putExtra("category","sfpReport");
                    startActivity(intent);
                }
            }
        });
        scfNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scfCurrent.isChecked()){
                    Intent intent=new Intent(getActivity(),FrameActivity.class);
                    intent.putExtra("category","scfReport");
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }
}
