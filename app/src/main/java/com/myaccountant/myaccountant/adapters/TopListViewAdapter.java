package com.myaccountant.myaccountant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;

import java.util.ArrayList;

/**
 * Created by user on 11/8/2015.
 */
public class TopListViewAdapter extends ArrayAdapter<String> {

    Context context;

    String[] rowString={"business info","sales","purchases","expenses","other income","progress report"};
    int[] rowImages={R.mipmap.ic_business_info,R.mipmap.ic_sales,R.mipmap.ic_purchase,R.mipmap.ic_expense,R.mipmap.ic_other_income,R.mipmap.ic_progress_report};

    public TopListViewAdapter(Context context){
        super(context, R.layout.top_list_view_layout);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.top_list_view_layout,parent,false);
        ImageView rowImageView=(ImageView)rowView.findViewById(R.id.row_image);
        TextView rowTextView=(TextView)rowView.findViewById(R.id.row_text);

        rowImageView.setImageResource(rowImages[position]);
        rowTextView.setText(rowString[position]);

        return rowView;
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return rowString.length;
    }
}
