package com.example.ruchir.demotest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<DayModel> dayModels;

    public List<DayModel> getDayModels() {
        return dayModels;
    }

    public ListAdapter(List<DayModel> dayModels) {
        this.dayModels = dayModels;
    }

    @Override
    public int getCount() {
        return dayModels.size();
    }

    @Override
    public Object getItem(int position) {
        return dayModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        TextView textView = view.findViewById(R.id.textList);
        CheckBox checkbox = view.findViewById(R.id.checkbox);
        textView.setText(dayModels.get(position).getDay());
        if (dayModels.get(position).getSelected()) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayModels.get(position).setSelected(!dayModels.get(position).getSelected());
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
