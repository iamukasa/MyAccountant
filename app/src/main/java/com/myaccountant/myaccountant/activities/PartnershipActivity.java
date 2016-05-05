package com.myaccountant.myaccountant.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.Partnership;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import net.redwarp.library.database.DatabaseHelper;

public class PartnershipActivity extends AppCompatActivity{

    MaterialEditText name,amount,capitalInterest,drawingInterest,profitSharingRatio;
    Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partnership_layout);

        name=(MaterialEditText)findViewById(R.id.partnership_name);
        amount=(MaterialEditText)findViewById(R.id.partnership_amount);
        capitalInterest=(MaterialEditText)findViewById(R.id.partnership_interest_capital);
        drawingInterest=(MaterialEditText)findViewById(R.id.partnership_drawings);
        profitSharingRatio=(MaterialEditText)findViewById(R.id.partnership_profit_ratio);
        saveButton=(Button)findViewById(R.id.save_btn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(PartnershipActivity.this);
                Partnership data = new Partnership();
                data.setName(name.getText().toString());
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setInterestOnCapital(Double.parseDouble(capitalInterest.getText().toString()));
                data.setInterestOnDrawings(Double.parseDouble(drawingInterest.getText().toString()));
                data.setProfitSharingRatio(Double.parseDouble(profitSharingRatio.getText().toString()));
                helper.save(data);
                Toast.makeText(PartnershipActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
