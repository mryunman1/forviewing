package com.example.employeeside;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private final boolean isExported;
    private final EmployeeDBAssist dbAssist;
    private final LocalDate selectedDate;


    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, boolean isExported, EmployeeDBAssist dbAssist, LocalDate selectedDate)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.isExported = isExported;
        this.dbAssist = dbAssist;
        this.selectedDate = selectedDate;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        String year = yearFromDate(selectedDate);
        String month = monthFromDate(selectedDate);
        if(monthFromDate(selectedDate).toString().length() == 1){
            month = "0" + monthFromDate(selectedDate);
        }
        String dayText = daysOfMonth.get(position);
        if(dayText.length() == 1){
            dayText = "0" + dayText;
        }
        String date = yearFromDate(selectedDate) + "-" + month + "-" +dayText;
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        ScheduleModel daySched = this.dbAssist.getDateModel(date);
        if(this.isExported == true){
            if(!this.dbAssist.doesDateExist(date)){
                holder.dayOfMonth.setTextColor(Color.RED);
            }
            else{
                LocalDate dt = LocalDate.parse(date);
                if(dt.getDayOfWeek().toString().equals("SATURDAY") || dt.getDayOfWeek().toString().equals("SUNDAY")){
                    if (daySched.getEID1() == 0 || daySched.getEID2() == 0) {
                        holder.dayOfMonth.setTextColor(Color.RED);
                    }
                }
                else {
                    if (daySched.getEID1() == 0 || daySched.getEID2() == 0 || daySched.getEID4() == 0 || daySched.getEID5() == 0) {
                        holder.dayOfMonth.setTextColor(Color.RED);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText);
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }
    private String monthFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return date.format(formatter);
    }
    private String yearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return date.format(formatter);
    }
}

