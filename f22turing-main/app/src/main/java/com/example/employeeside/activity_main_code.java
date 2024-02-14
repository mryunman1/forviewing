package com.example.employeeside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
//import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import java.util.List;
//make search non-case-sensitive
public class activity_main_code extends AppCompatActivity{
    Intent i;

    ImageButton backBtn, newEmployeeBtn;
    EmployeeDBAssist employeeDB;
    List<EmployeeModel> allEmployees;
    List<AvailabilityModel> allAvailabilities;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeeDB = new EmployeeDBAssist(activity_main_code.this);
        allEmployees = employeeDB.getAllEmployees();
        allAvailabilities = employeeDB.getAllAvailable();
        backBtn = findViewById(R.id.back_button);
        newEmployeeBtn = findViewById(R.id.add_employee_btn);
        searchView = findViewById(R.id.searchView);

        backBtn.setOnClickListener(v-> {

                    i = new Intent(activity_main_code.this,MainActivity.class);
                    startActivity(i);
                }

        );
        newEmployeeBtn.setOnClickListener(v-> {

                    i = new Intent(activity_main_code.this, add_employee_screen_code.class);
                    startActivity(i);
                }

        );
        recyclerView = findViewById(R.id.employeeRecyclerList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Collections.sort(allEmployees, new Comparator<EmployeeModel>() {

            @Override
            public int compare(EmployeeModel o1, EmployeeModel o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.getfName(), o2.getfName());
            }

        });
        mAdapter = new RecycleViewAdapter(allEmployees, activity_main_code.this);
        recyclerView.setAdapter(mAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    public void filter(String newText) {
        List<EmployeeModel> filteredList = new ArrayList<>();
        for (EmployeeModel item : allEmployees){
            if (item.getfName().toLowerCase().startsWith(newText.toLowerCase())|item.getlName().toLowerCase().startsWith(newText.toLowerCase())){
                filteredList.add(item);
            }
        }

        Collections.sort(filteredList, new Comparator<EmployeeModel>() {

            @Override
            public int compare(EmployeeModel o1, EmployeeModel o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.getfName(), o2.getfName());
            }

        });

        mAdapter = new RecycleViewAdapter(filteredList, activity_main_code.this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
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
                i = new Intent(activity_main_code.this, MainActivity.class);
                Toast.makeText(this, "This is the home page", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.menuBtnAddEmployee:
//                Toast.makeText(this, "Need to go to new page", Toast.LENGTH_SHORT).show();
                i = new Intent(activity_main_code.this, add_employee_screen_code.class);
                startActivity(i);
                break;
            case R.id.menuBtnViewEmployees:
//                i = new Intent(activity_main_code.this, activity_main_code.class);
                Toast.makeText(this, "This is the View Employees page", Toast.LENGTH_SHORT).show();
//                startActivity(i);
                break;
            case R.id.menuBtnScheduler:
//              TODO: ADD SCHEDULER INTENT WHEN A PAGE IS CREATED.
            i = new Intent(activity_main_code.this, ScheduleView.class);
//                Toast.makeText(this, "Need to go to Scheduler page", Toast.LENGTH_SHORT).show();
            startActivity(i);
                break;

            default:
                break;
        }

        return true;
    }

}


