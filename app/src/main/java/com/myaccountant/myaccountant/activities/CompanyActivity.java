package com.myaccountant.myaccountant.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Spinner;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.Company;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import net.redwarp.library.database.DatabaseHelper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 11/11/2015.
 */
public class CompanyActivity extends AppCompatActivity{

    Spinner category;
    MaterialEditText name,amount,issue_price,current_price;
    Button saveButton;    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_layout);

        category=(Spinner)findViewById(R.id.company_category_spinner);
        name=(MaterialEditText)findViewById(R.id.company_name);
        amount=(MaterialEditText)findViewById(R.id.company_amount);
        issue_price=(MaterialEditText)findViewById(R.id.company_issue_price);
        current_price=(MaterialEditText)findViewById(R.id.company_current_price);
        saveButton=(Button)findViewById(R.id.save_btn);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(CompanyActivity.this);
                Company data = new Company();
                data.setName(name.getText().toString());
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setCategory(category.getSelectedItem().toString());
                data.setCurrentPrice(Double.parseDouble(current_price.getText().toString()));
                data.setIssuePrice(Double.parseDouble(issue_price.getText().toString()));
                helper.save(data);
                Toast.makeText(CompanyActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
