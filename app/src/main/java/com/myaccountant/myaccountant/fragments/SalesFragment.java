package com.myaccountant.myaccountant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.activities.FrameActivity;
import com.myaccountant.myaccountant.data.Sales;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import net.redwarp.library.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import info.hoang8f.widget.FButton;

/**
 * Created by user on 11/8/2015.
 */
public class SalesFragment extends Fragment {

    MaterialEditText name,description,quantity,amount;
    Button saveButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.add_sales_layout,container,false);

        name=(MaterialEditText)rootView.findViewById(R.id.add_sales_name);
        description=(MaterialEditText)rootView.findViewById(R.id.add_sales_description);
        quantity=(MaterialEditText)rootView.findViewById(R.id.add_sales_quantity);
        amount=(MaterialEditText)rootView.findViewById(R.id.add_sales_amount);
        saveButton=(Button)rootView.findViewById(R.id.save_btn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper=new DatabaseHelper(getActivity());
                Sales data=new Sales();
                data.setDescription(description.getText().toString());
                data.setName(name.getText().toString());
                data.setTime(getTime());
                data.setDate(getDate());
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setQuantity(Double.parseDouble(quantity.getText().toString()));
                helper.save(data);
                Toast.makeText(getActivity(),"saved",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), FrameActivity.class);
                intent.putExtra("category", "sales");
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }
    public String getDate(){
        String date="";
        Calendar calendar= Calendar.getInstance();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        date=dateFormat.format(calendar.getTime());

        return date;
    }
    public String getTime(){
        String time="";
        Calendar calendar= Calendar.getInstance();
        DateFormat dateFormat=new SimpleDateFormat("h:mm:a");
        time=dateFormat.format(calendar.getTime());

        return time;
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.commit();
    }
}
