package com.example.employeeside;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScheduleWeekendEditor extends AppCompatActivity {

    ImageView backButton;
    TextView dayEditing;
    Spinner Morning1, Morning2, Morning3;
    ImageButton confirmButton;
    ArrayList<String> arrayList_morning, arrayList_night;
    ArrayAdapter<String> arrayAdapter_morning, arrayAdapter_night;
    EmployeeDBAssist employeeDB;
    List<EmployeeModel> allEmployees;
    List<AvailabilityModel> allAvailabilities;
    Switch busySwitch;
    Intent i;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_weekend_editor);

        backButton = findViewById(R.id.schedBackBtn4);
        dayEditing = findViewById(R.id.dayEditing4);
        Morning1 = findViewById(R.id.spinner7);
        Morning2 = findViewById(R.id.spinner8);
        Morning3 = findViewById(R.id.spinner9);
        confirmButton = findViewById(R.id.editButton3);
        busySwitch = findViewById(R.id.busySwitch2);

        employeeDB = new EmployeeDBAssist(ScheduleWeekendEditor.this);
        allEmployees = employeeDB.getAllEmployees();
        allAvailabilities = employeeDB.getAllAvailable();
        arrayList_morning = new ArrayList<>();
        arrayList_night = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        String date = (String) getIntent().getSerializableExtra("Date");
        dayEditing.setText(date);
        String day = (String) getIntent().getSerializableExtra("Day");
        arrayList_morning.add("No employee selected");
        arrayList_night.add("No employee selected");


        for(EmployeeModel i : allEmployees){
            for(AvailabilityModel x : allAvailabilities){
                if(x.getEID() == i.getEID()){
                    if(day.equals("SATURDAY") && !i.isStatus()){
                        if(x.getSaturday().equals("All Day")){
                            if(i.getTraining().equals("All Day")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :TO:TC");
                            }
                            else if(i.getTraining().equals("Morning")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :TO");
                            }
                            else if(i.getTraining().equals("Night")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :TC");
                            }
                            else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                        }
                    }
                    if(day.equals("SUNDAY") && !i.isStatus()){
                        if(x.getSunday().equals("All Day")){
                            if(i.getTraining().equals("All Day")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :TO:TC");
                            }
                            else if(i.getTraining().equals("Morning")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :TO");
                            }
                            else if(i.getTraining().equals("Night")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :TC");
                            }
                            else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                        }
                    }
                }
            }

        }
        ScheduleModel sm = employeeDB.getDateModel(date);
        EmployeeModel em;
        em = employeeDB.getEmployeeModel(sm.getEID1());
        if(em.isStatus()){
            if(sm.getEID1() != 0){
                arrayList_morning.add(em.getEID() + " " + em.getfName() + " " + em.getlName());
            }
        }
        em = employeeDB.getEmployeeModel(sm.getEID2());
        if(em.isStatus()){
            if(sm.getEID2() != 0){
                arrayList_morning.add(em.getEID() + " " + em.getfName() + " " + em.getlName());
            }
        }
        em = employeeDB.getEmployeeModel(sm.getEID3());
        if(em.isStatus()){
            if(sm.getEID3() != 0){
                arrayList_morning.add(em.getEID() + " " + em.getfName() + " " + em.getlName());
            }
        }
        em = employeeDB.getEmployeeModel(sm.getEID4());
        if(em.isStatus()){
            if(sm.getEID4() != 0){
                arrayList_night.add(em.getEID() + " " + em.getfName() + " " + em.getlName());
            }
        }
        em = employeeDB.getEmployeeModel(sm.getEID5());
        if(em.isStatus()){
            if(sm.getEID5() != 0){
                arrayList_night.add(em.getEID() + " " + em.getfName() + " " + em.getlName());
            }
        }
        em = employeeDB.getEmployeeModel(sm.getEID6());
        if(em.isStatus()){
            if(sm.getEID6() != 0){
                arrayList_night.add(em.getEID() + " " + em.getfName() + " " + em.getlName());
            }
        }

        arrayAdapter_morning = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_morning);
        arrayAdapter_night = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_night);
        Morning1.setAdapter(arrayAdapter_morning);
        Morning2.setAdapter(arrayAdapter_morning);
        Morning3.setAdapter(arrayAdapter_morning);

        //sets default value
        ScheduleModel dateModel = employeeDB.getDateModel(date);
        if(dateModel != null){
            Morning1.setSelection(GetIndex(dateModel.getEID1(), arrayList_morning));
            Morning2.setSelection(GetIndex(dateModel.getEID2(), arrayList_morning));
            Morning3.setSelection(GetIndex(dateModel.getEID3(), arrayList_morning));
        }
        //if morn3 and night3 are empty defaults unbusy
        if((dateModel.getEID3() == 0) & (dateModel.getEID6() == 0)){
            busySwitch.setChecked(false);
            Morning3.setVisibility(View.GONE);
        }
        else{busySwitch.setChecked(true);}
        //switch
        busySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScheduleWeekendEditor.this);
                    alertDialogBuilder.setMessage("Are you sure you want to remove " + Morning3.getSelectedItem().toString() + " from the schedule?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Morning3.setVisibility(View.GONE);
                                    Morning3.setSelection(0);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                    busySwitch.setChecked(true);
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                    //Morning3.setVisibility(View.GONE);
                    //Morning3.setSelection(0);
                }
                if(b){
                    Morning3.setVisibility(View.VISIBLE);
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> dupeCheck = new ArrayList<Integer>();
                if(!Morning1.getSelectedItem().toString().equals("No employee selected")) {
                    dupeCheck.add(GetID(Morning1.getSelectedItem().toString()));
                }
                if(!Morning2.getSelectedItem().toString().equals("No employee selected")) {
                    dupeCheck.add(GetID(Morning2.getSelectedItem().toString()));
                }
                if(!Morning3.getSelectedItem().toString().equals("No employee selected")) {
                    dupeCheck.add(GetID(Morning3.getSelectedItem().toString()));
                }
                if(CheckForDupes(dupeCheck)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleWeekendEditor.this);
                    builder.setMessage("There are duplicate employees inserted")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                if(!CheckForDupes(dupeCheck)){
                    ScheduleModel scheduleModel;
                    int Morn1 = 0;
                    int Morn2 = 0;
                    int Morn3 = 0;
                    int Ni1 = 0;
                    int Ni2 = 0;
                    int Ni3 = 0;
                    if (Morning1.getSelectedItem() != null) {
                        Morn1 = GetID(Morning1.getSelectedItem().toString());
                    }
                    if (Morning2.getSelectedItem() != null) {
                        Morn2 = GetID(Morning2.getSelectedItem().toString());
                    }
                    if (Morning3.getSelectedItem() != null) {
                        Morn3 = GetID(Morning3.getSelectedItem().toString());
                    }
                    boolean trainCheckMorn, trainCheckNight;
                    trainCheckMorn = false;
                    if (employeeDB.getTraining(Morn1).equals("All Day")){
                        trainCheckMorn = true;
                    }
                    if (employeeDB.getTraining(Morn2).equals("All Day")){
                        trainCheckMorn = true;
                    }
                    if (employeeDB.getTraining(Morn3).equals("All Day")){
                        trainCheckMorn = true;
                    }
                    if (employeeDB.getTraining(Morn1).equals("Morning")|employeeDB.getTraining(Morn2).equals("Morning")|employeeDB.getTraining(Morn3).equals("Morning") && employeeDB.getTraining(Morn1).equals("Night")|employeeDB.getTraining(Morn2).equals("Night")|employeeDB.getTraining(Morn3).equals("Night")){
                        trainCheckMorn = true;
                    }

                    if(trainCheckMorn) {
                        try {
                            scheduleModel = new ScheduleModel(date, Morn1, Morn2, Morn3, Ni1, Ni2, Ni3);
                            EmployeeDBAssist employeeDBAssist = new EmployeeDBAssist(ScheduleWeekendEditor.this);
                            employeeDBAssist.updateSchedule(date, Morn1, Morn2, Morn3, Ni1, Ni2, Ni3);
                            employeeDBAssist.addDate(scheduleModel);


                        } catch (Exception e) {
                            Toast.makeText(ScheduleWeekendEditor.this, "Error Setting Schedule", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(ScheduleWeekendEditor.this, ScheduleView.class);
                        startActivity(intent);
                    }
                    String trainMessage;
                    if(!trainCheckMorn){
                        trainMessage = "Full day shift should have at least one employee with opening training and one with closing training.";
                        AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleWeekendEditor.this);
                        builder.setMessage(trainMessage)

                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }



                }
            }
        });



        backButton.setOnClickListener(v-> {

                    Intent i = new Intent(ScheduleWeekendEditor.this,ScheduleView.class);
                    startActivity(i);
                }

        );

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
                i = new Intent(ScheduleWeekendEditor.this, MainActivity.class);
//                Toast.makeText(this, "Need to go to the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
                Toast.makeText(this, "This is the Add Employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(ScheduleWeekendEditor.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(ScheduleWeekendEditor.this, activity_main_code.class);
                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
                i = new Intent(ScheduleWeekendEditor.this, ScheduleView.class);
//                Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;

            default:
                break;
        }

        return true;
    }

    public int GetID(String str){
        try{
            String parts[] = str.split(" ");
            return Integer.valueOf(parts[0]);
        }
        catch (Exception e){
            return 0;
        }
    }
    public int GetIndex(int ID, ArrayList<String> list){
        int counter = 0;
        for(String loop : list){
            if(GetID(loop) == ID){
                return counter;
            }
            counter++;
        }
        return 0;
    }
    public boolean CheckForDupes(List<Integer> list){
        Set<Integer> set = new HashSet<>();
        for (int i : list){
            if(set.add(i) == false){
                return true;
            }
        }
        return false;
    }
}
