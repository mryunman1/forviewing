package com.example.employeeside;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    List<EmployeeModel> allEmployees;
//    List<AvailabilityModel> allAvailabilitys;
    Context context;

    public RecycleViewAdapter(List<EmployeeModel> allEmployees, Context context) {
//        this.allAvailabilitys = allAvailabilitys;
        this.allEmployees = allEmployees;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_employee, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        EmployeeModel employee;
//        AvailabilityModel available = null;
        employee = allEmployees.get(position);
        holder.employeeID.setText(String.valueOf(allEmployees.get(position).getEID()));
        holder.firstName.setText(allEmployees.get(position).getfName());
        holder.lastName.setText(allEmployees.get(position).getlName());
        if (allEmployees.get(position).isStatus()){holder.empStatus.setText("Status: Deactivated");}
        else{holder.empStatus.setText("Status: Active");}
        holder.employee = employee;
//        if(position < allAvailabilitys.size()){
//            available = allAvailabilitys.get(position);
//            employee = allEmployees.get(position);
//            holder.employeeID.setText(String.valueOf(allEmployees.get(position).getEID()));
//            holder.firstName.setText(allEmployees.get(position).getfName());
//            holder.lastName.setText(allEmployees.get(position).getlName());
//            holder.employee = employee;
//        } else{
//            employee = allEmployees.get(position);
//            holder.employeeID.setText(String.valueOf(allEmployees.get(position).getEID()));
//            holder.firstName.setText(allEmployees.get(position).getfName());
//            holder.lastName.setText(allEmployees.get(position).getlName());
//            holder.employee = employee;
//        }

        EmployeeModel finalEmployee = employee;
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditEmployee.class);
                intent.putExtra("Editing", finalEmployee);
                context.startActivity(intent);
            }
        });
        EmployeeModel finalEmployee1 = employee;
//        AvailabilityModel finalAvailable = available;
        holder.availabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, availability_screen_code.class);
                intent.putExtra("Available", finalEmployee1);
//                if(finalAvailable!= null){
//                    intent.putExtra("Days", finalAvailable);
//                }
                context.startActivity(intent);
            }
        });
        EmployeeModel finalEmployee2 = employee;
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewEmployee.class);
                intent.putExtra("Viewing", finalEmployee2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allEmployees.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstName;
        TextView lastName;
        TextView employeeID;
        TextView empStatus;
        ImageButton editButton;
        ImageButton availabilityButton;
        EmployeeModel employee;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.fNameView);
            lastName = itemView.findViewById(R.id.lNameView);
            employeeID = itemView.findViewById(R.id.eIDView);
            empStatus = itemView.findViewById(R.id.StatusFlag);
            editButton = itemView.findViewById(R.id.imageButton2);
            availabilityButton = itemView.findViewById(R.id.imageButton4);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

}