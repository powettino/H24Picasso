package com.yeapp.h24picasso.adapter;

import android.content.Context;
import android.util.Log;
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
import com.yeapp.h24picasso.utils.Constants;

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
            coppie.add(giorni.get(i));
        }
    }

    public void clear(){
        coppie.clear();
        lastPosition=-1;
        animate=true;
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

        etichetta.setText((coppie.get(position).first).substring(0,3));
        num1.setText(Constants.Numero.getName(coppie.get(position).second.get(0)));
        num2.setText(Constants.Numero.getName(coppie.get(position).second.get(1)));
        num3.setText(Constants.Numero.getName(coppie.get(position).second.get(2)));
        if ((coppie.size()==position+1)) {
            LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.contenitore);
            ll.setBackgroundResource(0);
        }
//        if ((lastPosition < position) && animate) {
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
//            convertView.startAnimation(animation);
//        }

        lastPosition = position > lastPosition ? position : lastPosition;
        return convertView;
    }
}