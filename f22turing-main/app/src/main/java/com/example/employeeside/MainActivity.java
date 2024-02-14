package com.example.employeeside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button employees_btn, schedule_btn;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        employees_btn = findViewById(R.id.employees_btn);
        schedule_btn = findViewById(R.id.schedule_btn);

        employees_btn.setOnClickListener(v-> {

                i = new Intent(MainActivity.this, activity_main_code.class);
                startActivity(i);
            }
        );
        schedule_btn.setOnClickListener(v-> {

                    Intent i = new Intent(MainActivity.this, ScheduleView.class);
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
//                i = new Intent(this, MainActivity.class);
                Toast.makeText(this, "This is the home page", Toast.LENGTH_SHORT).show();
//                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
//                Toast.makeText(this, "Need to go to Add employee page", Toast.LENGTH_SHORT).show();
                i = new Intent(MainActivity.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
                i = new Intent(MainActivity.this, activity_main_code.class);
//                Toast.makeText(this, "Need to go to View Employees page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
            i = new Intent(MainActivity.this, ScheduleView.class);
//            Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
            startActivity(i);
            break;

            default:
                break;
        }

        return true;
    }
}

