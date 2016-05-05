package com.myaccountant.myaccountant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.Sales;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/12/2015.
 */
public class SalesRowLayoutTwoAdapter extends BaseAdapter {

    Context context;
    List<?> items;
    ArrayList<Sales> salesList;

    public SalesRowLayoutTwoAdapter(Context context,List<?> items){
        this.context=context;
        this.items=items;
        salesList=(ArrayList)items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.row_layout_two,parent,false);

        TextView name=(TextView)rowView.findViewById(R.id.row_two_name_text_view);
        TextView description=(TextView)rowView.findViewById(R.id.row_two_description_text_view);
        ImageView image=(ImageView)rowView.findViewById(R.id.row_two_image_view);
        TextView date=(TextView)rowView.findViewById(R.id.row_two_date_text_view);
        TextView time=(TextView)rowView.findViewById(R.id.row_two_time_text_view);

        name.setText(salesList.get(position).getName());
        description.setText("Ksh." + salesList.get(position).getAmount());
        description.setTextColor(Color.parseColor("#3cbe71"));
        image.setImageDrawable(getDrawable(salesList.get(position).getName().substring(0, 1).toUpperCase()));
        date.setText(salesList.get(position).getDate());
        time.setText(salesList.get(position).getTime());

        return rowView;
    }
    public TextDrawable getDrawable(String letter){
        ColorGenerator color=ColorGenerator.MATERIAL;
        TextDrawable textDrawable=TextDrawable.builder().buildRound(letter,color.getRandomColor());

        return textDrawable;
    }

}
