package com.jobs.workbook.entites.user;

import java.util.ArrayList;
import java.util.Collections;

public class AnnualEarnings implements Comparable {
    int year;
    Long value;
    ArrayList<MonthlyEarnings> monthlyEarnings;
    int count;

    public AnnualEarnings(int year, Long value) {
        this.year = year;
        this.value = value;
        this.monthlyEarnings = new ArrayList<>();
        this.count = 1;
        for (int i = 0; i <= 11; i++) {
            monthlyEarnings.add(new MonthlyEarnings(i, 0l));
        }

    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getValue() {
        return value;
    }



    public void setValue(Long value) {
        this.value = value;
    }

    public ArrayList<MonthlyEarnings> getMonthlyEarnings() {
        Collections.sort(monthlyEarnings);
        return monthlyEarnings;
    }

    public void setMonthlyEarnings(ArrayList<MonthlyEarnings> monthlyEarnings) {
        this.monthlyEarnings = monthlyEarnings;
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
        int compareage = ((AnnualEarnings) o).getYear();
        return compareage - this.year;
    }
}
