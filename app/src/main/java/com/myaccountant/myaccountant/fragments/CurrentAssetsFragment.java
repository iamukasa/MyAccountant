package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.CurrentAssets;
import com.myaccountant.myaccountant.data.OpeningInventory;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import org.angmarch.views.NiceSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by user on 11/11/2015.
 */
public class CurrentAssetsFragment extends Fragment{
    NiceSpinner category;
    MaterialEditText name,description,amount;
    Button saveButton;

    LinkedList<String> dataSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.current_assets_layout,container,false);

        dataSet=new LinkedList<>(Arrays.asList("Cash and Bank","Debtors","Inventory","Prepaid Expenses","Other"));

        category=(NiceSpinner)rootView.findViewById(R.id.current_assets_category_spinner);
        name=(MaterialEditText)rootView.findViewById(R.id.current_assets_name);
        description=(MaterialEditText)rootView.findViewById(R.id.current_assets_description);
        amount=(MaterialEditText)rootView.findViewById(R.id.current_assets_worth);
        saveButton=(Button)rootView.findViewById(R.id.save_btn);

        category.attachDataSource(dataSet);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper=new DatabaseHelper(getActivity());
                CurrentAssets data=new CurrentAssets();
                data.setCategory(category.getText().toString());
                data.setName(name.getText().toString());
                data.setTime(getTime());
                data.setDate(getDate());
                data.setDescription(description.getText().toString());
                data.setWorth(Double.parseDouble(amount.getText().toString()));
                helper.save(data);
                Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                if(category.getText().toString().equalsIgnoreCase("Inventory")){
                    List<OpeningInventory> items=helper.getAll(OpeningInventory.class);
                    ArrayList<OpeningInventory> openingInventory=(ArrayList)items;
                    if(openingInventory.isEmpty()){
                        OpeningInventory openingData=new OpeningInventory();
                        openingData.setCategory(category.getText().toString());
                        openingData.setName(name.getText().toString());
                        openingData.setTime(getTime());
                        openingData.setDate(getDate());
                        openingData.setDescription(description.getText().toString());
                        openingData.setWorth(Double.parseDouble(amount.getText().toString()));
                        helper.save(openingData);
                    }
                }
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
