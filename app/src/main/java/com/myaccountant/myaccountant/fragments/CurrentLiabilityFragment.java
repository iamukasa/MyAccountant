package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.CurrentLiability;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import net.redwarp.library.database.DatabaseHelper;

import org.angmarch.views.NiceSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by user on 11/11/2015.
 */
public class CurrentLiabilityFragment extends Fragment{
    NiceSpinner category;
    MaterialEditText name,description,amount;
    Button saveButton;

    List<String> dataSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.current_liability_layout,container,false);

        dataSet=new LinkedList<>(Arrays.asList("Account Payable", "Bank Overdraft", "Short Bank Loans", "Unearned Revenue", "Accrued Expenses","Other"));

        category=(NiceSpinner)rootView.findViewById(R.id.current_liability_category_spinner);
        name=(MaterialEditText)rootView.findViewById(R.id.current_liability_name_editText);
        description=(MaterialEditText)rootView.findViewById(R.id.current_liability_description);
        amount=(MaterialEditText)rootView.findViewById(R.id.current_liability_amount);
        saveButton=(Button)rootView.findViewById(R.id.save_btn);

        category.attachDataSource(dataSet);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper=new DatabaseHelper(getActivity());
                CurrentLiability data=new CurrentLiability();
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setCategory(category.getText().toString());
                data.setDate(getDate());
                data.setDescription(description.getText().toString());
                data.setTime(getTime());
                data.setName(name.getText().toString());
                helper.save(data);
                Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
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
}
