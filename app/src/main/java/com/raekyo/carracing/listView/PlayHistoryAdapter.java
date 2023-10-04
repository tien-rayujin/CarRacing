package com.raekyo.carracing.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.raekyo.carracing.R;

import java.util.ArrayList;

public class PlayHistoryAdapter extends BaseAdapter
{
    Context context;
    ArrayList<PlayHistory> arrayList;
    int layout;
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public PlayHistoryAdapter(Context context, ArrayList<PlayHistory> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        ImageView imgCarHistory = (ImageView) view.findViewById(R.id.imgCarHistory);
        TextView txtCarRound = (TextView) view.findViewById(R.id.txtCarRound);
        TextView txtCarCurrentMoney = (TextView) view.findViewById(R.id.txtCarCurrentMoney);

        PlayHistory playHistory = arrayList.get(i);

        imgCarHistory.setImageResource(playHistory.getCar_img());
        txtCarRound.setText(playHistory.getRound());
        txtCarCurrentMoney.setText(playHistory.getMoney());

        return view;
    }
}
