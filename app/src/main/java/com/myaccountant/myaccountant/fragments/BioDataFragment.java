package com.myaccountant.myaccountant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.activities.BioDataActivity;
import com.myaccountant.myaccountant.activities.CapitalActivity;
import com.myaccountant.myaccountant.data.BusinessInfo;
import com.myaccountant.myaccountant.helpers.Helper;
import com.myaccountant.myaccountant.helpers.ParseConstants;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;
import com.rey.material.widget.RadioButton;

import net.redwarp.library.database.DatabaseHelper;

import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by user on 11/8/2015.
 */
public class BioDataFragment extends Fragment {

    MaterialEditText name,location;
    RadioButton soleProprietorship,partnership,company,small,mediumSized,large,retail,service,wholesale;
    Button save;

    String structure="";
    String size="";
    String business="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rowView=inflater.inflate(R.layout.bio_data_layout,container,false);

        name=(MaterialEditText)rowView.findViewById(R.id.business_info_name_of_business);
        location=(MaterialEditText)rowView.findViewById(R.id.business_info_location);
        soleProprietorship=(RadioButton)rowView.findViewById(R.id.sole_proprietorship_radio);
        partnership=(RadioButton)rowView.findViewById(R.id.partnership_radio);
        company=(RadioButton)rowView.findViewById(R.id.company_radio);
        small=(RadioButton)rowView.findViewById(R.id.small_radio);
        mediumSized=(RadioButton)rowView.findViewById(R.id.medium_radio);
        large=(RadioButton)rowView.findViewById(R.id.large_radio);
        retail=(RadioButton)rowView.findViewById(R.id.retail_radio);
        service=(RadioButton)rowView.findViewById(R.id.service_radio);
        wholesale=(RadioButton)rowView.findViewById(R.id.wholesale_radio);
        save=(Button)rowView.findViewById(R.id.save_btn);

        soleProprietorship.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    structure="sole proprietorship";
                    partnership.setCheckedImmediately(false);
                    company.setCheckedImmediately(false);
                }
            }
        });
        partnership.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    structure="partnership";
                    soleProprietorship.setCheckedImmediately(false);
                    company.setCheckedImmediately(false);
                }
            }
        });
        company.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    structure="company";
                    partnership.setCheckedImmediately(false);
                    soleProprietorship.setCheckedImmediately(false);
                }
            }
        });
        small.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    size="small";
                    mediumSized.setCheckedImmediately(false);
                    large.setCheckedImmediately(false);
                }
            }
        });
        mediumSized.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    size="medium-sized";
                    small.setCheckedImmediately(false);
                    large.setCheckedImmediately(false);
                }
            }
        });
        large.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    size="large";
                    mediumSized.setCheckedImmediately(false);
                    small.setCheckedImmediately(false);
                }
            }
        });
        retail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    business="retail";
                    service.setCheckedImmediately(false);
                    wholesale.setCheckedImmediately(false);
                }
            }
        });
        service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    business="service";
                    retail.setCheckedImmediately(false);
                    wholesale.setCheckedImmediately(false);
                }
            }
        });
        wholesale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    business="wholesale";
                    service.setCheckedImmediately(false);
                    retail.setCheckedImmediately(false);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatabaseHelper helper = new DatabaseHelper(getActivity());
//                List<BusinessInfo> items = helper.getAll(BusinessInfo.class);
//                if (items.isEmpty()) {
//                    BusinessInfo bioData = new BusinessInfo();
//                    bioData.setDescription(business);
//                    bioData.setSize(size);
//                    bioData.setStructure(structure);
//                    bioData.setLocation(location.getText().toString());
//                    bioData.setName(name.getText().toString());
//                    helper.save(bioData);
//                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "data already exists", Toast.LENGTH_SHORT).show();
//                }

                ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseConstants.CLASS_BUSINESS_INFO);
                query.whereEqualTo("user", ParseUser.getCurrentUser());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject businessInfo, ParseException e) {
                        if (e == null) {
                            businessInfo.put("description", business);
                            businessInfo.put("size", size);
                            businessInfo.put("structure", structure);
                            businessInfo.put("location", location.getText().toString());
                            businessInfo.put("name", name.getText().toString());
                            businessInfo.put("user", ParseUser.getCurrentUser());
                            businessInfo.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), BioDataActivity.class);
                                        getActivity().startActivity(intent);
                                    } else {
                                        Helper.showErrorDialog("Error", e.getMessage(), getActivity());
                                    }
                                }
                            });
                        } else {
                            Helper.showErrorDialog("Error", e.getMessage(), getActivity());
                        }
                    }
                });

            }
        });

        return rowView;
    }
}
