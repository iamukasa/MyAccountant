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
import com.myaccountant.myaccountant.adapters.NonCurrentAssetsRowLayoutOneAdapter;
import com.myaccountant.myaccountant.data.CurrentAssets;
import com.myaccountant.myaccountant.data.NonCurrentAssets;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
public class NonCurrentAssetListFragment extends Fragment {
    SnackBar snackBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.non_current_asset_list_layout,container,false);
        ListView listView=(ListView)rootView.findViewById(R.id.non_current_assets_list);
        RelativeLayout parent=(RelativeLayout)rootView.findViewById(R.id.non_current_assets_list_parent);
        snackBar=SnackBar.make(getActivity());

        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<NonCurrentAssets> items=helper.getAll(NonCurrentAssets.class);

        listView.setAdapter(new NonCurrentAssetsRowLayoutOneAdapter(getActivity(), items));

        snackBar.text("TOTAL KSH." + totalNonCurrentAssets(items));
        snackBar.textColor(Color.WHITE);
        snackBar.actionText("DISMISS");
        snackBar.actionTextColor(getResources().getColor(R.color.blue));
        snackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar snackBar, int i) {
                snackBar.dismiss();
            }
        });
        //snackBar.duration(10000);
        snackBar.show(parent);

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    public double totalNonCurrentAssets(List<NonCurrentAssets> items){
        double total=0;
        ArrayList<NonCurrentAssets> listItems=(ArrayList)items;
        for(NonCurrentAssets list:listItems){
            total+=list.getCost();
        }
        return total;
    }
}
