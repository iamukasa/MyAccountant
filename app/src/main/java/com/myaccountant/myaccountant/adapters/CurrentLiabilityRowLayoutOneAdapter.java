package com.myaccountant.myaccountant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.CurrentLiability;
import com.myaccountant.myaccountant.data.NonCurrentLiabilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/15/2015.
 */
public class CurrentLiabilityRowLayoutOneAdapter extends BaseAdapter {
    Context context;
    List<?> items;
    ArrayList<CurrentLiability> listItems;

    public CurrentLiabilityRowLayoutOneAdapter(Context context,List<?> items){
        this.context=context;
        this.items=items;
        listItems=(ArrayList<CurrentLiability>)items;
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
        View rowView=inflater.inflate(R.layout.row_layout_one,parent,false);

        TextView name=(TextView)rowView.findViewById(R.id.row_one_name_text_view);
        TextView category=(TextView)rowView.findViewById(R.id.row_one_category_text_view);
        TextView description=(TextView)rowView.findViewById(R.id.row_one_description_text_view);
        TextView date=(TextView)rowView.findViewById(R.id.row_one_date_text_view);
        TextView time=(TextView)rowView.findViewById(R.id.row_one_time_text_view);
        ImageView image=(ImageView)rowView.findViewById(R.id.row_one_image_view);

        name.setText(listItems.get(position).getName());
        category.setText(listItems.get(position).getCategory());
        description.setText(listItems.get(position).getAmount()+"");
        date.setText(listItems.get(position).getDate());
        time.setText(listItems.get(position).getTime());
        image.setImageDrawable(getDrawable(listItems.get(position).getName().substring(0,1).toUpperCase()));

        return rowView;
    }
    public TextDrawable getDrawable(String letter){
        ColorGenerator color=ColorGenerator.MATERIAL;
        TextDrawable textDrawable=TextDrawable.builder().buildRound(letter,color.getRandomColor());

        return textDrawable;
    }
}
