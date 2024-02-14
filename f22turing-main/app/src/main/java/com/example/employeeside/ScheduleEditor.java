package com.example.employeeside;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleEditor extends AppCompatActivity {

    ImageView backButton;
    TextView dayEditing;
    Spinner Morning1, Morning2, Morning3, Night1, Night2, Night3;
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
        setContentView(R.layout.schedule_editor);

        backButton = findViewById(R.id.schedBackBtn3);
        dayEditing = findViewById(R.id.dayEditing);
        Morning1 = findViewById(R.id.spinner);
        Morning2 = findViewById(R.id.spinner2);
        Morning3 = findViewById(R.id.spinner3);
        Night1 = findViewById(R.id.spinner4);
        Night2 = findViewById(R.id.spinner5);
        Night3 = findViewById(R.id.spinner6);
        confirmButton = findViewById(R.id.editButton2);
        busySwitch = findViewById(R.id.busySwitch);

        employeeDB = new EmployeeDBAssist(ScheduleEditor.this);
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
                    if(day.equals("MONDAY")){
                        if(!i.isStatus()) {
                            if (x.getMonday().equals("All Day")) {

                                if (i.getTraining().equals("All Day") || i.getTraining().equals("Morning")) {
                                    arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                } else {
                                    arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());
                                }

                                if (i.getTraining().equals("All Day") || i.getTraining().equals("Night")) {
                                    arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                } else {
                                    arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());
                                }
                            } else {
                                if (x.getMonday().equals("Opening")) {
                                    if (i.getTraining().equals("All Day") || i.getTraining().equals("Morning")) {
                                        arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                    } else {
                                        arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());
                                    }
                                }
                                if (x.getMonday().equals("Closing")) {
                                    if (i.getTraining().equals("All Day") || i.getTraining().equals("Night")) {
                                        arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                    } else {
                                        arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());
                                    }
                                }
                            }
                        }
                    }
                    if(day.equals("TUESDAY") && !i.isStatus()){
                        if(x.getTuesday().equals("All Day")){
                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}

                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                        }
                        else {
                            if (x.getTuesday().equals("Opening")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                    arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                            if (x.getTuesday().equals("Closing")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                    arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                        }
                    }
                    if(day.equals("WEDNESDAY") && !i.isStatus()){
                        if(x.getWednesday().equals("All Day")){
                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}

                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                        }
                        else {
                            if (x.getWednesday().equals("Opening")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                    arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                            if (x.getWednesday().equals("Closing")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                    arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                        }
                    }
                    if(day.equals("THURSDAY") && !i.isStatus()){
                        if(x.getThursday().equals("All Day")){
                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}

                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                        }
                        else {
                            if (x.getThursday().equals("Opening")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                    arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                            if (x.getThursday().equals("Closing")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                    arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                        }
                    }
                    if(day.equals("FRIDAY") && !i.isStatus()){
                        if(x.getFriday().equals("All Day")){
                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}

                            if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                            }
                            else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                        }
                        else {
                            if (x.getFriday().equals("Opening")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Morning")){
                                    arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_morning.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
                            if (x.getFriday().equals("Closing")) {
                                if(i.getTraining().equals("All Day") || i.getTraining().equals("Night")){
                                    arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName() + " :T");
                                }
                                else{arrayList_night.add(i.getEID() + " " + i.getfName() + " " + i.getlName());}
                            }
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
        Night1.setAdapter(arrayAdapter_night);
        Night2.setAdapter(arrayAdapter_night);
        Night3.setAdapter(arrayAdapter_night);

        //sets default value
        ScheduleModel dateModel = employeeDB.getDateModel(date);
        if(dateModel != null){
            Morning1.setSelection(GetIndex(dateModel.getEID1(), arrayList_morning));
            Morning2.setSelection(GetIndex(dateModel.getEID2(), arrayList_morning));
            Morning3.setSelection(GetIndex(dateModel.getEID3(), arrayList_morning));
            Night1.setSelection(GetIndex(dateModel.getEID4(), arrayList_night));
            Night2.setSelection(GetIndex(dateModel.getEID5(), arrayList_night));
            Night3.setSelection(GetIndex(dateModel.getEID6(), arrayList_night));
        }
        //if morn3 and night3 are empty defaults unbusy
        if((dateModel.getEID3() == 0) & (dateModel.getEID6() == 0)){
            busySwitch.setChecked(false);
            Morning3.setVisibility(View.GONE);
            Night3.setVisibility(View.GONE);
        }
        else {busySwitch.setChecked(true);}
        //switch
        busySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScheduleEditor.this);
                    alertDialogBuilder.setMessage("Are you sure you want to remove " + Morning3.getSelectedItem().toString() + " and " + Night3.getSelectedItem().toString() + " from the schedule?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Morning3.setVisibility(View.GONE);
                                    Morning3.setSelection(0);
                                    Night3.setVisibility(View.GONE);
                                    Night3.setSelection(0);
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

                   // Morning3.setVisibility(View.GONE);
                   // Morning3.setSelection(0);
                   // Night3.setVisibility(View.GONE);
                   // Night3.setSelection(0);
                }
                if(b){
                    Morning3.setVisibility(View.VISIBLE);
                    Night3.setVisibility(View.VISIBLE);
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
                if(!Night1.getSelectedItem().toString().equals("No employee selected")) {
                    dupeCheck.add(GetID(Night1.getSelectedItem().toString()));
                }
                if(!Night2.getSelectedItem().toString().equals("No employee selected")) {
                    dupeCheck.add(GetID(Night2.getSelectedItem().toString()));
                }
                if(!Night3.getSelectedItem().toString().equals("No employee selected")) {
                    dupeCheck.add(GetID(Night3.getSelectedItem().toString()));
                }
                if(CheckForDupes(dupeCheck)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleEditor.this);

                    builder.setMessage("There are duplicate employees inserted");
                    Set<Integer> dupeSet = returnDupes(dupeCheck);
                    String dupeMessage = "";
                    for (int c : dupeSet){
                        if(!dupeMessage.equals("")){
                            dupeMessage = dupeMessage + ", ";
                        }
                        dupeMessage = dupeMessage + employeeDB.getEmployeeModel(c).getfName();
                    }
                    builder.setMessage("There are duplicate employees inserted: " + dupeMessage)

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
                    if (Night1.getSelectedItem() != null) {
                        Ni1 = GetID(Night1.getSelectedItem().toString());
                    }
                    if (Night2.getSelectedItem() != null) {
                        Ni2 = GetID(Night2.getSelectedItem().toString());
                    }
                    if (Night3.getSelectedItem() != null) {
                        Ni3 = GetID(Night3.getSelectedItem().toString());
                    }
                    boolean trainCheckMorn, trainCheckNight;
                    trainCheckMorn = false;
                    trainCheckNight = false;
                    if (employeeDB.getTraining(Morn1).equals("Morning") || employeeDB.getTraining(Morn1).equals("All Day")){
                        trainCheckMorn = true;
                    }
                    if (employeeDB.getTraining(Morn2).equals("Morning") || employeeDB.getTraining(Morn2).equals("All Day")){
                        trainCheckMorn = true;
                    }
                    if (employeeDB.getTraining(Morn3).equals("Morning") || employeeDB.getTraining(Morn3).equals("All Day")){
                        trainCheckMorn = true;
                    }
                    if (employeeDB.getTraining(Ni1).equals("Night") || employeeDB.getTraining(Ni1).equals("All Day")){
                        trainCheckNight = true;
                    }
                    if (employeeDB.getTraining(Ni2).equals("Night") || employeeDB.getTraining(Ni2).equals("All Day")){
                        trainCheckNight = true;
                    }
                    if (employeeDB.getTraining(Ni3).equals("Night") || employeeDB.getTraining(Ni3).equals("All Day")){
                        trainCheckNight = true;
                    }
                    if(trainCheckMorn && trainCheckNight) {
                        try {
                            scheduleModel = new ScheduleModel(date, Morn1, Morn2, Morn3, Ni1, Ni2, Ni3);
                            EmployeeDBAssist employeeDBAssist = new EmployeeDBAssist(ScheduleEditor.this);
                            employeeDBAssist.updateSchedule(date, Morn1, Morn2, Morn3, Ni1, Ni2, Ni3);
                            employeeDBAssist.addDate(scheduleModel);


                        } catch (Exception e) {
                            Toast.makeText(ScheduleEditor.this, "Error Setting Schedule", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(ScheduleEditor.this, ScheduleView.class);
                        startActivity(intent);
                    }
                    String trainMessage;
                    trainMessage = "";
                    if(!trainCheckMorn){
                        trainMessage = "Morning shift has no trained employees";
                    }
                    if(!trainCheckNight){
                        trainMessage = "Afternoon Shift has no trained employees";
                    }
                    if(!trainCheckMorn && !trainCheckNight){
                        trainMessage = "There are no trained employees scheduled";
                    }
                    if(!trainMessage.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleEditor.this);
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

                    i = new Intent(ScheduleEditor.this,ScheduleView.class);
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
                i = new Intent(ScheduleEditor.this, MainActivity.class);
//                Toast.makeText(this, "Need to go to the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
                Toast.makeText(this, "This is the Add Employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(ScheduleEditor.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(ScheduleEditor.this, activity_main_code.class);
                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
            i = new Intent(ScheduleEditor.this, ScheduleView.class);
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
    public Set<Integer> returnDupes(List<Integer> list){
        Set<Integer> set = new HashSet<>();
        Set<Integer> returnSet = new HashSet<>();
        for (int i : list){
            if(set.add(i) == false){
                returnSet.add(i);
            }
        }
        return returnSet;
    }
}
