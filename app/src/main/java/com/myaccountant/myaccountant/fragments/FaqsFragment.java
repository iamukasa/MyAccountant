package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;

/**
 * Created by user on 11/8/2015.
 */
public class FaqsFragment extends Fragment {
    TextView question1,question_2,question_3,question_4,question_5,question_6,question_7,question_8,question_10;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.faqs_layout,container,false);



        return rootView;

    }




}
