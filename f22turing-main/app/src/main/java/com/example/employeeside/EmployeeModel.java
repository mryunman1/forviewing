package com.example.employeeside;

import java.io.Serializable;

//git commit from android studio is working
public class EmployeeModel implements Serializable {
    private int EID;
    private String fName;
    private String lName;
    private String email;
    private String phoneNo;
    private boolean status;
    private String training;

    //constructor
    public EmployeeModel(int EID, String fName, String lName, String email, String phoneNo, boolean status, String training) {
        this.EID = EID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.status = status;
        this.training = training;
    }
    public EmployeeModel(){
    }
    //to string method


    @Override
    public String toString() {
        return
                "EID=" + EID +
                ", name='" + fName + ' ' + lName + '\'' +
                ", email=" + email +
                ", cell phone=" + phoneNo +
                ", status=" + status +
                '}';
    }

    // getters and setters
    public int getEID() {
        return EID;
    }

    public void setEID(int EID) {
        this.EID = EID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTraining(){return training;}

    public void setTraining(String training) {
        this.training = training;
    }
}
