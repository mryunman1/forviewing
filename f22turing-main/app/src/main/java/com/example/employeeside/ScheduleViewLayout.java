package com.example.employeeside;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ScheduleViewLayout extends AppCompatActivity {
    EmployeeDBAssist employeeDB;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15,
            tv16, tv17, tv18, tv19, tv20, tv21, tv22, tv23, tv24, tv25, tv26, tv27, tv28, tv29, tv30, tv31,
            tv32, tv33, tv34, tv35, tv36, tv37, tv38, tv39, tv40, tv41, tv42;
    ConstraintLayout scheduleLayout;
    TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.schedule_view_layout);
        Bundle bundle = getIntent().getExtras();
        LocalDate date = (LocalDate) getIntent().getSerializableExtra("Date");
        String day = date.withDayOfMonth(1).getDayOfWeek().toString();
        LocalDate dayCount = date.withDayOfMonth(1);
        scheduleLayout = findViewById(R.id.scheduleLayout);
        Month month = date.getMonth();
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv5 = findViewById(R.id.textView5);
        tv6 = findViewById(R.id.textView6);
        tv7 = findViewById(R.id.textView7);
        tv8 = findViewById(R.id.textView8);
        tv9 = findViewById(R.id.textView9);
        tv10 = findViewById(R.id.textView10);
        tv11 = findViewById(R.id.textView11);
        tv12 = findViewById(R.id.textView12);
        tv13 = findViewById(R.id.textView13);
        tv14 = findViewById(R.id.textView14);
        tv15 = findViewById(R.id.textView15);
        tv16 = findViewById(R.id.textView16);
        tv17 = findViewById(R.id.textView17);
        tv18 = findViewById(R.id.textView18);
        tv19 = findViewById(R.id.textView19);
        tv20 = findViewById(R.id.textView20);
        tv21 = findViewById(R.id.textView21);
        tv22 = findViewById(R.id.textView22);
        tv23 = findViewById(R.id.textView23);
        tv24 = findViewById(R.id.textView24);
        tv25 = findViewById(R.id.textView25);
        tv26 = findViewById(R.id.textView26);
        tv27 = findViewById(R.id.textView27);
        tv28 = findViewById(R.id.textView28);
        tv29 = findViewById(R.id.textView29);
        tv30 = findViewById(R.id.textView30);
        tv31 = findViewById(R.id.textView31);
        tv32 = findViewById(R.id.textView32);
        tv33 = findViewById(R.id.textView33);
        tv34 = findViewById(R.id.textView34);
        tv35 = findViewById(R.id.textView35);
        tv36 = findViewById(R.id.textView36);
        tv37 = findViewById(R.id.textView37);
        tv38 = findViewById(R.id.textView38);
        tv39 = findViewById(R.id.textView39);
        tv40 = findViewById(R.id.textView40);
        tv41 = findViewById(R.id.textView41);
        tv42 = findViewById(R.id.textView42);


        int count = 1;
        if (day.equals("TUESDAY")) {
            count = 2;
        }
        if (day.equals("WEDNESDAY")) {
            count = 3;
        }
        if (day.equals("THURSDAY")) {
            count = 4;
        }
        if (day.equals("FRIDAY")) {
            count = 5;
        }
        if (day.equals("SATURDAY")) {
            count = 6;
        }
        if (day.equals("SUNDAY")) {
            count = 7;
        }
        if (count == 1) {
            tv1.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
            count++;
        }
        if (count == 2) {
            tv2.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
            count++;
        }
        if (count == 3) {
            tv3.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
            count++;
        }
        if (count == 4) {
            tv4.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
            count++;
        }
        if (count == 5) {
            tv5.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
            count++;
        }
        if (count == 6) {
            tv6.setText(weekendShifts(dayCount));
            dayCount = dayCount.plusDays(1);
            count++;
        }
        tv7.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv8.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv9.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv10.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv11.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv12.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv13.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv14.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv15.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv16.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv17.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv18.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv19.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv20.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv21.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv22.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv23.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv24.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv25.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv26.setText(dayShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv27.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        tv28.setText(weekendShifts(dayCount));
        dayCount = dayCount.plusDays(1);
        if (month.equals(dayCount.getMonth())) {
            tv29.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv30.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv31.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv32.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv33.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv34.setText(weekendShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv35.setText(weekendShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv36.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv37.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv38.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv39.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv40.setText(dayShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv41.setText(weekendShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }
        if (month.equals(dayCount.getMonth())) {
            tv42.setText(weekendShifts(dayCount));
            dayCount = dayCount.plusDays(1);
        }

        //takeScreenShot(scheduleLayout, "Schedule");


    }


    public String dayShifts(LocalDate date) {
        EmployeeDBAssist employeeDB = new EmployeeDBAssist(ScheduleViewLayout.this);
        ScheduleModel sm = employeeDB.getDateModel(date.toString());
        String shifts;
        shifts = date.toString() + "\nAM: " + employeeDB.getEmployeeModel(sm.getEID1()).getfName()
                + ",  " + employeeDB.getEmployeeModel(sm.getEID2()).getfName()
                + ",  " + employeeDB.getEmployeeModel(sm.getEID3()).getfName()
                + "\nPM: " + employeeDB.getEmployeeModel(sm.getEID4()).getfName()
                + ",  " + employeeDB.getEmployeeModel(sm.getEID5()).getfName()
                + ",  " + employeeDB.getEmployeeModel(sm.getEID6()).getfName()  ;


        return shifts;
    }

    public String weekendShifts(LocalDate date) {
        EmployeeDBAssist employeeDB = new EmployeeDBAssist(ScheduleViewLayout.this);
        ScheduleModel sm = employeeDB.getDateModel(date.toString());
        String shifts;
        shifts = date.toString() + "\nAll Day:\n" + employeeDB.getEmployeeModel(sm.getEID1()).getfName() + " "
                + "\n" + employeeDB.getEmployeeModel(sm.getEID2()).getfName() + " "
                + "\n" + employeeDB.getEmployeeModel(sm.getEID3()).getfName() + " ";


        return shifts;
    }

    private static File takeScreenShot(View view, String fileName) {
        try {
            String dirPath = Environment.getDownloadCacheDirectory().toString();
            File fileDir = new File(dirPath);

            String path = dirPath + "/" + fileName + ".png";

            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            File imageFile = new File(path);

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return imageFile;
        }catch (IOException e){
            return null;
        }
    }

 //   private static final int REQUEST_EXTERNAL
}
