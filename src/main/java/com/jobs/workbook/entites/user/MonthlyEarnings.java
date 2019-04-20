package com.jobs.workbook.entites.user;

import java.text.DateFormatSymbols;

public class MonthlyEarnings implements Comparable {
    int id;
    String month;
    Long value;
    int count;

    public MonthlyEarnings(int id, Long value) {
        this.id = id;
        this.value = value;
        this.count = 0;
        this.month = this.getMonthForInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    @Override
    public int compareTo(Object o) {
        int compareTo = ((MonthlyEarnings) o).getId();
        return id - compareTo;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
}
