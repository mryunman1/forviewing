package com.example.employeeside;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ViewEmployee extends AppCompatActivity {
    TextView employeeFName, employeeLName, employeeEmail, employeePhone, employeeOpening,employeeClosing,employeeStatus;
    ImageButton backButton;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_employee_screen);
        employeeFName = findViewById(R.id.employeeFName);
        employeeLName = findViewById(R.id.employeeLName);
        employeeEmail = findViewById(R.id.employeeEmail);
        employeePhone = findViewById(R.id.employeePhone);
        employeeOpening = findViewById(R.id.employeeOpening);
        employeeClosing = findViewById(R.id.employeeClosing);
        employeeStatus = findViewById(R.id.employeeStatus);
        backButton = findViewById(R.id.imageButton);
        Bundle bundle = getIntent().getExtras();
        EmployeeModel employee = (EmployeeModel) getIntent().getSerializableExtra("Viewing");

        employeeFName.setText(employee.getfName());
        employeeLName.setText(employee.getlName());
        employeeEmail.setText(employee.getEmail());
        employeePhone.setText(employee.getPhoneNo());
        if(employee.isStatus()){employeeStatus.setText("Deactivated");}
        else{employeeStatus.setText("Active");}

        if(employee.getTraining().equals("All Day")){
            employeeOpening.setText("Trained");
            employeeClosing.setText("Trained");
        }
        else if(employee.getTraining().equals("Morning")){
            employeeOpening.setText("Trained");
            employeeClosing.setText("Needs Training");
        }
        else if(employee.getTraining().equals("Night")){
            employeeOpening.setText("Needs Training");
            employeeClosing.setText("Trained");
        }
        else{
            employeeClosing.setText("Needs Training");
            employeeOpening.setText("Needs Training");
        }

        backButton.setOnClickListener(v-> {

                    i = new Intent(ViewEmployee.this,activity_main_code.class);
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
                i = new Intent(ViewEmployee.this, MainActivity.class);
//                Toast.makeText(this, "This is the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
//                Toast.makeText(this, "Need to go to Add employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(ViewEmployee.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(ViewEmployee.this, activity_main_code.class);
//                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
                i = new Intent(ViewEmployee.this, ScheduleView.class);
//            Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;

            default:
                break;
        }

        return true;
    }
}
