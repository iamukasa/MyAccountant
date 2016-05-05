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
import com.myaccountant.myaccountant.adapters.ExpensesRowLayoutOneAdapter;
import com.myaccountant.myaccountant.data.Expenses;
import com.myaccountant.myaccountant.data.NonCurrentLiabilities;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
public class ExpensesListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.expenses_list_layout,container,false);
        ListView listView=(ListView)rootView.findViewById(R.id.expenses_list);
        RelativeLayout parent=(RelativeLayout)rootView.findViewById(R.id.expenses_list_parent);
        SnackBar snackBar=SnackBar.make(getActivity());

        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<Expenses> items=helper.getAll(Expenses.class);

        listView.setAdapter(new ExpensesRowLayoutOneAdapter(getActivity(),items));

        snackBar.text("TOTAL KSH."+getTotal((ArrayList)items));
        snackBar.textColor(Color.WHITE);
        snackBar.actionText("DISMISS");
        snackBar.actionTextColor(getResources().getColor(R.color.fbutton_color_alizarin));
        snackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar snackBar, int i) {
                snackBar.dismiss();
            }
        });
        snackBar.show(parent);

        return rootView;
    }
    public double getTotal(ArrayList<Expenses> items){
        double total=0;
        for(Expenses list:items){
            total+=list.getAmount();
        }

        return total;
    }
}
