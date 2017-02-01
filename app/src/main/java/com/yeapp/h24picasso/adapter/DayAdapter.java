package com.yeapp.h24picasso.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeapp.h24picasso.R;

import java.util.ArrayList;


public class DayAdapter extends BaseAdapter {

    private Context context;
    private int lastPosition = -1;
    private ArrayList<Pair<String, ArrayList<String>>> coppie;
    private boolean animate = true;

    public DayAdapter(Context context) {
        super();
        this.context = context;
        coppie = new ArrayList<Pair<String, ArrayList<String>>>();
    }

    @Override
    public int getCount() {
        return coppie.size();
    }

    @Override
    public Object getItem(int position) {
        return coppie.get(position);
    }

    public void addDays(ArrayList<Pair<String, ArrayList<String>>> giorni, boolean includeBase){
        coppie.clear();
        for(int i=0 ; i< (includeBase ? giorni.size(): giorni.size()-1);i++){
//                addDay(giorni.get(i));
            coppie.add(giorni.get(i));
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.row_days, parent, false);
        }
        TextView etichetta = (TextView) convertView.findViewById(R.id.rowEtichetta);
        TextView num1 = (TextView) convertView.findViewById(R.id.rowNum1);
        TextView num2 = (TextView) convertView.findViewById(R.id.rowNum2);
        TextView num3 = (TextView) convertView.findViewById(R.id.rowNum3);

        etichetta.setText(coppie.get(position).first);
        num1.setText(coppie.get(position).second.get(0));
        num2.setText(coppie.get(position).second.get(1));
        num3.setText(coppie.get(position).second.get(2));

        if ((lastPosition < position) && animate) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bouncing_down);
            convertView.startAnimation(animation);
        }
        lastPosition = position > lastPosition ? position : lastPosition;
        return convertView;
    }
}