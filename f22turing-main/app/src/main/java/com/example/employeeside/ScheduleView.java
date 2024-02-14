package com.example.employeeside;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleView extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    ImageView backBtn, exportButton;
    Intent i;
    boolean isExported;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.isExported = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_screen);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
        exportButton = findViewById(R.id.exportButton);
        backBtn = findViewById(R.id.schedBackBtn);
        backBtn.setOnClickListener(v-> {
                    i = new Intent(ScheduleView.this, MainActivity.class);
                    startActivity(i);
                }

        );
        exportButton.setOnClickListener(v-> {
            this.isExported = true;
            setMonthView();

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnHome:
                i = new Intent(ScheduleView.this, MainActivity.class);
//                Toast.makeText(this, "Need to go to the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
                Toast.makeText(this, "This is the Add Employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(ScheduleView.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(ScheduleView.this, activity_main_code.class);
                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
//            i = new Intent(add_employee_screen_code.this, page2_activity.class);
//                Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
//            startActivity(i);
                break;

            default:
                break;
        }

        return true;
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        EmployeeDBAssist dbAssist = new EmployeeDBAssist(ScheduleView.this);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, isExported, dbAssist, selectedDate);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
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

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }


    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            //String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            String message = "Selected Date " + dayText + " " + monthFromDate(selectedDate);
            String month = monthFromDate(selectedDate);
            if(monthFromDate(selectedDate).toString().length() == 1){
                month = "0" + monthFromDate(selectedDate);
            }
            if(dayText.length() == 1){
                dayText = "0" + dayText;
            }
            String date = yearFromDate(selectedDate) + "-" + month + "-" +dayText;

            LocalDate dt = LocalDate.parse(date);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            if((dt.getDayOfWeek().toString() == "SATURDAY") || (dt.getDayOfWeek().toString() == "SUNDAY")){
                Intent intent = new Intent(ScheduleView.this, ScheduleWeekendEditor.class);
                intent.putExtra("Date", date);
                intent.putExtra("Day", dt.getDayOfWeek().toString());
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(ScheduleView.this, ScheduleEditor.class);
                intent.putExtra("Date", date);
                intent.putExtra("Day", dt.getDayOfWeek().toString());
                startActivity(intent);
            }
        }

    }

}