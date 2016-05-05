package com.myaccountant.myaccountant.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.adapters.NonCurrentLiabilityRowLayoutOneAdapter;
import com.myaccountant.myaccountant.data.CurrentLiability;
import com.myaccountant.myaccountant.data.NonCurrentAssets;
import com.myaccountant.myaccountant.data.NonCurrentLiabilities;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
public class NonCurrentLiabilityListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.non_current_liability_list_layout,container,false);
        ListView listView=(ListView)rootView.findViewById(R.id.non_current_liability_list);
        RelativeLayout parent=(RelativeLayout)rootView.findViewById(R.id.non_current_liability_list_parent);
        SnackBar snackBar=SnackBar.make(getActivity());

        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<NonCurrentLiabilities> items=helper.getAll(NonCurrentLiabilities.class);

        listView.setAdapter(new NonCurrentLiabilityRowLayoutOneAdapter(getActivity(),items));

        snackBar.text("TOTAL KSH."+getTotal((ArrayList)items));
        snackBar.textColor(Color.WHITE);
        snackBar.actionText("DISMISS");
        snackBar.actionTextColor(getResources().getColor(R.color.blue));
        snackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar snackBar, int i) {
                snackBar.dismiss();
            }
        });
        snackBar.show(parent);

        return rootView;
    }
    public double getTotal(ArrayList<NonCurrentLiabilities> items){
        double total=0;
        for(NonCurrentLiabilities list:items){
            total+=list.getAmount();
        }

        return total;
    }
}
