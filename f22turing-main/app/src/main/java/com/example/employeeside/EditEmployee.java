package com.example.employeeside;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditEmployee extends AppCompatActivity {
    EditText editFName, editLName, editEmail, editPhone;
    ImageButton editButton, backButton;
    Switch deactivateSwitch;
    EmployeeDBAssist employeeDB;
    Intent i;
    Boolean s;
    Spinner openTrain, closeTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_employee_screen);

        editFName = findViewById(R.id.editFName);
        editLName = findViewById(R.id.editLName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editButton = findViewById(R.id.editButton);
        backButton = findViewById(R.id.backButton);
        openTrain = findViewById(R.id.spinnerOpening);
        closeTrain = findViewById(R.id.spinnerClosing);
        deactivateSwitch = findViewById(R.id.employeeStatusSwitch2);


        Bundle bundle = getIntent().getExtras();
        EmployeeModel employee = (EmployeeModel) getIntent().getSerializableExtra("Editing");
        employeeDB = new EmployeeDBAssist(EditEmployee.this);

        editFName.setText(employee.getfName());
        editLName.setText(employee.getlName());
        editEmail.setText(employee.getEmail());
        editPhone.setText(employee.getPhoneNo());

        if(employee.getTraining().equals("Morning") || employee.getTraining().equals("All Day")){
            openTrain.setSelection(1);
        }
        if(employee.getTraining().equals("Night") || employee.getTraining().equals("All Day")){
            closeTrain.setSelection(1);
        }

        backButton.setOnClickListener(v-> {

                    i = new Intent(EditEmployee.this,activity_main_code.class);
                    startActivity(i);
                }

        );
        if (employee.isStatus()){
            deactivateSwitch.setChecked(true);
            s = true;
        }
        else {
            deactivateSwitch.setChecked(false);
            s = false;
        }
        deactivateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditEmployee.this);
                    alertDialogBuilder.setMessage("Do you wish to deactivate " + editFName.getText().toString() + " " + editLName.getText().toString() + "? This will remove them from future scheduled days and you won't be able to schedule them until they are active again.")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    s = true;
                                    employeeDB.deleteSchedule(employee.getEID(), LocalDate.now());
                                    //Intent intent = new Intent(EditEmployee.this, activity_main_code.class);
                                    //startActivity(intent);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    deactivateSwitch.setChecked(false);
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else{s = false;}
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckAllFields()){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditEmployee.this);
                    alertDialogBuilder.setMessage("Do you wish to update " + editFName.getText().toString())
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String training;
                                    training = "None";
                                    if(openTrain.getSelectedItem().toString().equals("Trained")){
                                        training = "Morning";
                                    }
                                    if(closeTrain.getSelectedItem().toString().equals("Trained")){
                                        training = "Night";
                                    }
                                    if(openTrain.getSelectedItem().toString().equals("Trained") && closeTrain.getSelectedItem().toString().equals("Trained")){
                                        training = "All Day";
                                    }
                                    if (!s){
                                        employeeDB.updateOne(employee.getEID(), editFName.getText().toString(), editLName.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString(),false, training);
                                    }
                                    else if (s){
                                        employeeDB.updateOne(employee.getEID(), editFName.getText().toString(), editLName.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString(),true, training);
                                    }

                                    //employeeDB.updateOne(employee.getEID(), editFName.getText().toString(), editLName.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString(),false, training);

                                    Intent intent = new Intent(EditEmployee.this, activity_main_code.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

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
                i = new Intent(EditEmployee.this, MainActivity.class);
//                Toast.makeText(this, "This is the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
//                Toast.makeText(this, "Need to go to Add employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(EditEmployee.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(EditEmployee.this, activity_main_code.class);
//                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
                i = new Intent(EditEmployee.this, ScheduleView.class);
//            Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
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
        Matcher matcher = email.matcher(editEmail.getText().toString());
        if (editFName.length() == 0) {
            editFName.setError("This field is required");
            isFalse = true;
        }

        if (editLName.length() == 0) {
            editLName.setError("This field is required");
            isFalse = true;
        }

        if (editEmail.length() == 0) {
            editEmail.setError("Email is required");
            isFalse = true;
        }
        if (!matcher.matches() & !editEmail.getText().toString().isEmpty()) {
            editEmail.setError("Email format is incorrect");
            isFalse = true;
        }

        if (editPhone.length() == 0) {
            editPhone.setError("Password is required");
            isFalse = true;
        }
        if (isFalse == true){
            return false;
        }
        // after all validation return true.
        return true;
    }
}
