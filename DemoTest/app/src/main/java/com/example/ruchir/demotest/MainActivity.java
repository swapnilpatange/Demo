package com.example.ruchir.demotest;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListDialog.DialogListener {

    private TextView txtcurrentDate, dayList, recharge;
    private List<DayModel> dayModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setListener();
        initObjects();
        showDatePicker(Calendar.getInstance().getTimeInMillis());
    }

    private void initObjects() {

        Calendar calendar = Calendar.getInstance();
        txtcurrentDate.setText(
                getDateFormat(calendar.getTimeInMillis()));
        dayModels = new ArrayList<>();
        dayModels.add(new DayModel("Monday", false));
        dayModels.add(new DayModel("Tuesday", false));
        dayModels.add(new DayModel("Wednesday", false));
        dayModels.add(new DayModel("Thrusday", false));
        dayModels.add(new DayModel("Friday", false));
        dayModels.add(new DayModel("Saturday", false));
        dayModels.add(new DayModel("Sunday", false));
    }

    private void setListener() {


        recharge.setOnClickListener(this);
        txtcurrentDate.setOnClickListener(this);
        dayList.setOnClickListener(this);
    }

    private void initializeViews() {

        txtcurrentDate = findViewById(R.id.currentDate);
        dayList = findViewById(R.id.dayList);
        recharge = findViewById(R.id.recharge);
    }

    private void showDatePicker(long currentDate) {
        DialogFragment dialogFragment = new DatePickerFragment(currentDate, txtcurrentDate);
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String getDateFormat(long time) {
        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(time));
        return dateString;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.currentDate:
                String date[] = txtcurrentDate.getText().toString().split("/");
                showDatePicker(getDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0])));
                break;
            case R.id.dayList:

                break;
            case R.id.recharge:
                ListDialog listDialog = new ListDialog(this, this,dayModels);
                listDialog.show();
                break;
        }
    }

    @Override
    public void getDaySelected(String daySelected,List<DayModel> dayModels) {
        recharge.setText(daySelected);
        this.dayModels=dayModels;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        long currentDate;
        String type;
        TextView txtCurrentDate;

        public DatePickerFragment(long currentDate, TextView txtCurrentDate) {
            this.type = type;
            this.txtCurrentDate = txtCurrentDate;
            this.currentDate = currentDate;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(currentDate);
            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.CustomDatePickerDialogTheme, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            txtCurrentDate.setText(day + "/" + (month + 1) + "/" + year);
        }

    }

    private long getDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);//Year,Mounth -1,Day
        return c.getTimeInMillis();
    }
}
