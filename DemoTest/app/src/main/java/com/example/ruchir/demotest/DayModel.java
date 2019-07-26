package com.example.ruchir.demotest;

public class DayModel {
    private String day;

    public void setDay(String day) {
        this.day = day;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getDay() {
        return day;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    private Boolean isSelected;


    public DayModel(String day,Boolean isSelected) {
        this.day=day;
        this.isSelected=isSelected;
    }
}
