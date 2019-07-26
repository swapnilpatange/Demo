package com.example.ruchir.demotest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListDialog extends Dialog implements View.OnClickListener {
    private ListView listView;
    private TextView everyDay, weekDay, weekend, apply;
    private DialogListener dialogListener;
    private List<DayModel> dayModels;

    public ListDialog(@NonNull Context context, DialogListener dialogListener, List<DayModel> dayModels) {
        super(context);
        this.dialogListener = dialogListener;
        this.dayModels = dayModels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_dialog);
        initializeView();
        setListener();
        setAdapter();
    }

    private void setListener() {
        everyDay.setOnClickListener(this);
        weekDay.setOnClickListener(this);
        weekend.setOnClickListener(this);
        apply.setOnClickListener(this);

    }

    private void setAdapter() {


        listView.setAdapter(new ListAdapter(dayModels));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                List<DayModel> dayModels = ((ListAdapter) listView.getAdapter()).getDayModels();
                dayModels.get(position).setSelected(!dayModels.get(position).getSelected());
                ((ListAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    private void initializeView() {

        listView = findViewById(R.id.list);
        everyDay = findViewById(R.id.everyDay);
        weekDay = findViewById(R.id.weekDay);
        weekend = findViewById(R.id.weekend);
        apply = findViewById(R.id.apply);
    }

    @Override
    public void onClick(View view) {

        List<DayModel> dayModels = ((ListAdapter) listView.getAdapter()).getDayModels();
        switch (view.getId()) {

            case R.id.everyDay:

                for (int i = 0; i < dayModels.size(); i++) {
                    dayModels.get(i).setSelected(true);
                }
                ((ListAdapter) listView.getAdapter()).notifyDataSetChanged();
                break;
            case R.id.weekDay:
                for (int i = 0; i < dayModels.size(); i++) {
                    if (i >= 5)
                        dayModels.get(i).setSelected(false);
                    else
                        dayModels.get(i).setSelected(true);
                }
                ((ListAdapter) listView.getAdapter()).notifyDataSetChanged();
                break;
            case R.id.weekend:

                for (int i = 0; i < dayModels.size(); i++) {
                    if (i >= 5)
                        dayModels.get(i).setSelected(true);
                    else
                        dayModels.get(i).setSelected(false);
                }
                ((ListAdapter) listView.getAdapter()).notifyDataSetChanged();
                break;
            case R.id.apply:
                String daySelected = "";
                for (int i = 0; i < dayModels.size(); i++) {
                    if (dayModels.get(i).getSelected())
                        daySelected = daySelected + dayModels.get(i).getDay();
                }
                dialogListener.getDaySelected(daySelected, dayModels);
                dismiss();
                break;
        }
    }

    interface DialogListener {
        void getDaySelected(String daySelected, List<DayModel> dayModels);
    }
}
