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
import com.myaccountant.myaccountant.adapters.PurchaseRowLayoutTwoAdapter;
import com.myaccountant.myaccountant.data.OtherIncome;
import com.myaccountant.myaccountant.data.Purchases;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
public class PurchaseListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.purchases_list_layout,container,false);
        RelativeLayout parent=(RelativeLayout)rootView.findViewById(R.id.purchase_list_parent);
        SnackBar snackBar=SnackBar.make(getActivity());

        ListView listView=(ListView)rootView.findViewById(R.id.purchase_list);
        DatabaseHelper helper=new DatabaseHelper(getActivity());

        List<Purchases> allPurchases=helper.getAll(Purchases.class);

        listView.setAdapter(new PurchaseRowLayoutTwoAdapter(getActivity(),allPurchases));

        snackBar.text("TOTAL KSH."+getTotal((ArrayList)allPurchases));
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
    public double getTotal(ArrayList<Purchases> items){
        double total=0;
        for(Purchases list:items){
            total+=list.getAmount();
        }

        return total;
    }
}
