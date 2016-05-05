package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.NonCurrentAssets;
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
public class NonCurrentAssetsFragment extends Fragment {

    NiceSpinner category,depreciationMethod;
    MaterialEditText name,description,cost,depreciationRate,accumulatedDepreciation,netBookValue;
    Button saveButton;

    LinkedList<String> dataSet;
    LinkedList<String> methodDataSet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.non_current_assets_layout,container,false);

        dataSet=new LinkedList<>(Arrays.asList("Land and Buildings", "Motor Vehicles", "Furniture and Fittings", "Machinery", "Intangible Assets","Other"));
        methodDataSet=new LinkedList<>(Arrays.asList("Straight Line","Reducing Balance"));

        category=(NiceSpinner)rootView.findViewById(R.id.non_current_assets_category_spinner);
        depreciationMethod=(NiceSpinner)rootView.findViewById(R.id.non_current_assets_depreciation_method_spinner);
        name=(MaterialEditText)rootView.findViewById(R.id.non_current_assets_name);
        description=(MaterialEditText)rootView.findViewById(R.id.non_current_assets_description);
        cost=(MaterialEditText)rootView.findViewById(R.id.non_current_assets_cost);
        depreciationRate=(MaterialEditText)rootView.findViewById(R.id.non_current_assets_depreciation_rate);
        accumulatedDepreciation=(MaterialEditText)rootView.findViewById(R.id.non_current_assets_accumulated_depreciation);
        netBookValue=(MaterialEditText)rootView.findViewById(R.id.non_current_assets_net_book_value);
        saveButton=(Button)rootView.findViewById(R.id.save_btn);

        category.attachDataSource(dataSet);
        depreciationMethod.attachDataSource(methodDataSet);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper=new DatabaseHelper(getActivity());
                NonCurrentAssets data=new NonCurrentAssets();
                data.setCategory(category.getText().toString());
                data.setDate(getDate());
                data.setDescription(description.getText().toString());
                data.setAccumulatedDepreciation(Double.parseDouble(accumulatedDepreciation.getText().toString()));
                data.setCost(Double.parseDouble(cost.getText().toString()));
                data.setDepreciationRate(Double.parseDouble(depreciationRate.getText().toString()));
                data.setDepreciationRateMethod(depreciationMethod.getText().toString());
                data.setName(name.getText().toString());
                data.setNetBookValue(Double.parseDouble(netBookValue.getText().toString()));
                data.setTime(getTime());
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
