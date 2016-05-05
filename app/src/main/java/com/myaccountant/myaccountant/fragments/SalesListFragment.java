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
import com.myaccountant.myaccountant.adapters.RowLayoutTwoAdapter;
import com.myaccountant.myaccountant.adapters.SalesRowLayoutTwoAdapter;
import com.myaccountant.myaccountant.data.Purchases;
import com.myaccountant.myaccountant.data.Sales;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
public class SalesListFragment extends Fragment {
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.sales_list_layout,container,false);
        RelativeLayout parent=(RelativeLayout)rootView.findViewById(R.id.sales_list_parent);
        SnackBar snackBar=SnackBar.make(getActivity());

        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<Sales> allSales=helper.getAll(Sales.class);

        listView=(ListView)rootView.findViewById(R.id.sales_list);
        listView.setAdapter(new SalesRowLayoutTwoAdapter(getActivity(),allSales));

        snackBar.text("TOTAL KSH."+getTotal((ArrayList)allSales));
        snackBar.textColor(Color.WHITE);
        snackBar.actionText("DISMISS");
        snackBar.actionTextColor(getResources().getColor(R.color.fbutton_color_emerald));
        snackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar snackBar, int i) {
                snackBar.dismiss();
            }
        });
        snackBar.show(parent);

        return rootView;
    }
    public double getTotal(ArrayList<Sales> items){
        double total=0;
        for(Sales list:items){
            total+=list.getAmount();
        }

        return total;
    }
}
