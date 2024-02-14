package com.example.employeeside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class add_employee_screen_code extends AppCompatActivity {

    ImageButton addEmployeesBtn, backBtn2;
    EditText employeeFNameField, employeeLNameField, employeeEmailField, employeePhoneField;
    Switch employeeStatusSwitch;
    EmployeeDBAssist employeeDB;
    Intent i;

    boolean isAllFieldsChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee_screen);


        addEmployeesBtn = findViewById(R.id.addEmployeeBtn);
        backBtn2 = findViewById(R.id.backBtn2);
        employeeFNameField = findViewById(R.id.employeeFNameField);
        employeeLNameField = findViewById(R.id.employeeLNameField);
        employeeEmailField = findViewById(R.id.employeeEmailField);
        employeePhoneField = findViewById(R.id.employeePhoneField);
        employeeStatusSwitch = findViewById(R.id.employeeStatusSwitch);
        employeeDB = new EmployeeDBAssist(add_employee_screen_code.this);

//        listeners

        addEmployeesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked == true){
                    EmployeeModel employeeModel;
                    try{
                        employeeModel = new EmployeeModel(-1, employeeFNameField.getText().toString(), employeeLNameField.getText().toString(), employeeEmailField.getText().toString(), employeePhoneField.getText().toString(),employeeStatusSwitch.isChecked(), "None");
//                        Toast.makeText(add_employee_screen_code.this, employeeModel.toString(), Toast.LENGTH_SHORT).show();
                        EmployeeDBAssist employeeDBAssist = new EmployeeDBAssist(add_employee_screen_code.this);
                        boolean success = employeeDBAssist.addOne(employeeModel);
                    }
                    catch (Exception e){
                        Toast.makeText(add_employee_screen_code.this, "Error Creating Employee", Toast.LENGTH_SHORT).show();
                    }
                    i = new Intent(add_employee_screen_code.this,activity_main_code.class);
                    startActivity(i);

                }

            }
        });
        backBtn2.setOnClickListener(v-> {
                    i = new Intent(add_employee_screen_code.this, activity_main_code.class);
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
                i = new Intent(add_employee_screen_code.this, MainActivity.class);
//                Toast.makeText(this, "Need to go to the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
                Toast.makeText(this, "This is the Add Employee page", Toast.LENGTH_SHORT).show();
//                i = new Intent(activity_main_code.this, add_employee_screen_code.class);
//                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(add_employee_screen_code.this, activity_main_code.class);
                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
            i = new Intent(add_employee_screen_code.this, ScheduleView.class);
//                Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
            startActivity(i);
                break;

            default:
                break;
        }

        return true;
    }


    private boolean CheckAllFields() {
        boolean isFalse = false;
        String regex = "^(.+)@(.+)$";
        Pattern email = Pattern.compile(regex);
        Matcher matcher = email.matcher(employeeEmailField.getText().toString());
        if (employeeFNameField.length() == 0) {
            employeeFNameField.setError("This field is required");
             isFalse = true;
        }

        if (employeeLNameField.length() == 0) {
            employeeLNameField.setError("This field is required");
            isFalse = true;
        }

        if (employeeEmailField.length() == 0) {
            employeeEmailField.setError("Email is required");
            isFalse = true;
        }
        if (!matcher.matches() & !employeeEmailField.getText().toString().isEmpty()) {
            employeeEmailField.setError("Email format is incorrect");
            isFalse = true;
        }

        if (employeePhoneField.length() == 0) {
            employeePhoneField.setError("Password is required");
            isFalse = true;
        }
        if (isFalse == true){
            return false;
        }
        // after all validation return true.
        return true;
    }

}
