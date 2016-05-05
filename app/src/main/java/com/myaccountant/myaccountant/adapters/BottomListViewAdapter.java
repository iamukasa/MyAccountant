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
public class BottomListViewAdapter extends ArrayAdapter<String> {
    Context context;

    String[] rowString={"FAQs","feedback","log out"};
    int[] rowImages={R.mipmap.ic_faqs,R.mipmap.ic_feedback,R.mipmap.ic_login_out};

    public BottomListViewAdapter(Context context){
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
