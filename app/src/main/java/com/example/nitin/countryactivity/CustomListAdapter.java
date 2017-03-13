package com.example.nitin.countryactivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by quest on 18/1/17.
 */
public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemcountry;
    private final Integer[] imgid;
    private final String[] itemCurrency;
    private final String[] itemidd;

    public CustomListAdapter(Activity context, String[] itemcountry, String[] itemCurrency, String[] countryId, Integer[] imgid) {
        super(context, R.layout.activity_country, itemcountry);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemcountry=itemcountry;
        this.imgid=imgid;
        this.itemCurrency = itemCurrency;
        this.itemidd = countryId;
    }

    public View getView(final int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_country, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        TextView iddfield = (TextView) rowView.findViewById(R.id.idd);

        txtTitle.setText(itemcountry[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText( itemCurrency[position]);
        iddfield.setText(itemidd[position]);
        return rowView;

    };
}
