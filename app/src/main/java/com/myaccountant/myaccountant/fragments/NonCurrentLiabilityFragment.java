package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.NonCurrentLiabilities;
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
public class NonCurrentLiabilityFragment extends Fragment{
    NiceSpinner category;
    MaterialEditText name,description,amount,interestRate,outstandingAmount;
    Button saveButton;

    List<String> dataSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.non_current_liability_layout,container,false);

        dataSet=new LinkedList<>(Arrays.asList("Loan", "Other"));

        category=(NiceSpinner)rootView.findViewById(R.id.non_current_liability_category_spinner);
        name=(MaterialEditText)rootView.findViewById(R.id.non_current_liability_name);
        description=(MaterialEditText)rootView.findViewById(R.id.non_current_liability_description);
        interestRate=(MaterialEditText)rootView.findViewById(R.id.non_current_liability_interest_rate);
        outstandingAmount=(MaterialEditText)rootView.findViewById(R.id.non_current_liability_outstanding_amount);
        amount=(MaterialEditText)rootView.findViewById(R.id.non_current_liability_amount);
        saveButton=(Button)rootView.findViewById(R.id.save_btn);

        category.attachDataSource(dataSet);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper=new DatabaseHelper(getActivity());
                NonCurrentLiabilities data=new NonCurrentLiabilities();
                data.setTime(getTime());
                data.setName(name.getText().toString());
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setCategory(category.getText().toString());
                data.setDate(getDate());
                data.setDescription(description.getText().toString());
                data.setInterestRate(Double.parseDouble(interestRate.getText().toString()));
                data.setOutstandingAmount(Double.parseDouble(outstandingAmount.getText().toString()));
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
