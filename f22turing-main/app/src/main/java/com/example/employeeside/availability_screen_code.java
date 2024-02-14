package com.example.employeeside;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class availability_screen_code extends AppCompatActivity {
    private Spinner mondaySpinner, tuesdaySpinner, wednesdaySpinner, thursdaySpinner, fridaySpinner, saturdaySpinner, sundaySpinner;
    private String mondayChoice, tuesdayChoice, wednesdayChoice, thursdayChoice, fridayChoice, saturdayChoice, sundayChoice;
//    private static final boolean [] choices = new boolean[6];
    private ImageButton confirmation, backButton;
    EmployeeDBAssist employeeDB;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability_screen);
        mondaySpinner = findViewById(R.id.mondaySpinner);
        tuesdaySpinner = findViewById(R.id.tuesdaySpinner);
        wednesdaySpinner = findViewById(R.id.wednesdaySpinner);
        thursdaySpinner = findViewById(R.id.thursdaySpinner);
        fridaySpinner = findViewById(R.id.fridaySpinner);
        saturdaySpinner = findViewById(R.id.saturdaySpinner);
        sundaySpinner = findViewById(R.id.sundaySpinner);
        confirmation = findViewById(R.id.confirm);

        backButton = findViewById(R.id.backBtn);

//        Bundle bundle = getIntent().getExtras();
//        AvailabilityModel available = (AvailabilityModel) getIntent().getSerializableExtra("Days");
        EmployeeModel employee = (EmployeeModel) getIntent().getSerializableExtra("Available");
        employeeDB = new EmployeeDBAssist(availability_screen_code.this);

        //setting elements in spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.availableTimes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.weekendavailableTimes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //monday
        mondaySpinner.setAdapter(adapter);
        //tuesday
        tuesdaySpinner.setAdapter(adapter);

        //wednesday
        wednesdaySpinner.setAdapter(adapter);

        //thursday
        thursdaySpinner.setAdapter(adapter);

        //friday
        fridaySpinner.setAdapter(adapter);

        //saturday
        saturdaySpinner.setAdapter(adapter2);

        //sunday
        sundaySpinner.setAdapter(adapter2);
        List<String>availList = employeeDB.getAvail(employee.getEID());
        if(availList.size() != 0){
            mondayChoice = availList.get(0);
            mondaySpinner.setSelection(getIndex(mondaySpinner, mondayChoice));

            tuesdayChoice = availList.get(1);
            tuesdaySpinner.setSelection(getIndex(tuesdaySpinner, tuesdayChoice));

            wednesdayChoice = availList.get(2);
            wednesdaySpinner.setSelection(getIndex(wednesdaySpinner, wednesdayChoice));

            thursdayChoice = availList.get(3);
            thursdaySpinner.setSelection(getIndex(thursdaySpinner, thursdayChoice));

            fridayChoice = availList.get(4);
            fridaySpinner.setSelection(getIndex(fridaySpinner, fridayChoice));

            saturdayChoice = availList.get(5);
            saturdaySpinner.setSelection(getIndex(saturdaySpinner, saturdayChoice));

            sundayChoice = availList.get(6);
            sundaySpinner.setSelection(getIndex(sundaySpinner, sundayChoice));
        }
        else{
            Toast.makeText(this, "No availability assigned for " + employee.getfName() + " create one.", Toast.LENGTH_SHORT).show();
        }

        backButton.setOnClickListener(v-> {
                    Intent i = new Intent(availability_screen_code.this, activity_main_code.class);
                    startActivity(i);
                }

        );

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AvailabilityModel availabilityModel;
                try{
                    availabilityModel = new AvailabilityModel(-1, employee.getEID(), mondaySpinner.getSelectedItem().toString(), tuesdaySpinner.getSelectedItem().toString(), wednesdaySpinner.getSelectedItem().toString(), thursdaySpinner.getSelectedItem().toString(), fridaySpinner.getSelectedItem().toString(), saturdaySpinner.getSelectedItem().toString(), sundaySpinner.getSelectedItem().toString());
                    EmployeeDBAssist employeeDBAssist = new EmployeeDBAssist(availability_screen_code.this);
                    employeeDBAssist.updateAvailability(employee.getEID(),mondaySpinner.getSelectedItem().toString(), tuesdaySpinner.getSelectedItem().toString(), wednesdaySpinner.getSelectedItem().toString(), thursdaySpinner.getSelectedItem().toString(), fridaySpinner.getSelectedItem().toString(), saturdaySpinner.getSelectedItem().toString(), sundaySpinner.getSelectedItem().toString());
//                    Toast.makeText(availability_screen_code.this, String.valueOf(employee.getEID()) + " " + mondaySpinner.getSelectedItem().toString()+ " " + tuesdaySpinner.getSelectedItem().toString()+ " " + wednesdaySpinner.getSelectedItem().toString()+ " " + thursdaySpinner.getSelectedItem().toString()+ " " + fridaySpinner.getSelectedItem().toString()+ " " + saturdaySpinner.getSelectedItem().toString()+ " " + sundaySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    employeeDBAssist.addAvailability(availabilityModel);
                }
                catch (Exception e){
                    Toast.makeText(availability_screen_code.this, "Error Setting Availability", Toast.LENGTH_SHORT).show();
                }
                i = new Intent(availability_screen_code.this,activity_main_code.class);
                startActivity(i);
//                Toast.makeText(availability_screen_code.this, String.valueOf(employee.getEID()) + " " + mondaySpinner.getSelectedItem().toString()+ " " + tuesdaySpinner.getSelectedItem().toString()+ " " + wednesdaySpinner.getSelectedItem().toString()+ " " + thursdaySpinner.getSelectedItem().toString()+ " " + fridaySpinner.getSelectedItem().toString()+ " " + saturdaySpinner.getSelectedItem().toString()+ " " + sundaySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
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
                i = new Intent(availability_screen_code.this, MainActivity.class);
//                Toast.makeText(this, "This is the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
//                Toast.makeText(this, "Need to go to Add employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(availability_screen_code.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(availability_screen_code.this, activity_main_code.class);
//                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
                i = new Intent(availability_screen_code.this, ScheduleView.class);
//            Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;

            default:
                break;
        }

        return true;
    }

    private String getAvailableDay(String availabilityString){
        if(availabilityString == null){
            return "Unavailable";
        } else{
            return availabilityString;
        }
    }
    private int getIndex(Spinner spinner, String availableString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(availableString)){
                index = i;
            }
        }
        return index;
    }

}
