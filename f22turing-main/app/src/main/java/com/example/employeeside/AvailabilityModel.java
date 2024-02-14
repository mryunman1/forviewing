package com.example.employeeside;

import java.io.Serializable;

public class AvailabilityModel implements Serializable {
    private int AID;
    private int EID;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public AvailabilityModel(int AID, int EID, String monday, String tuesday, String wednesday,
                             String thursday, String friday, String saturday, String sunday) {
        this.AID = AID;
        this.EID = EID;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public AvailabilityModel(){

    }
    @Override
    public String toString() {
        return
                "AID=" + AID +
                        "EID=" + EID +
                        ", Availability= " + '\'' + "monday " + monday + '\'' +
                        ", tuesday =" + tuesday + '\'' +
                        ", wednesday =" + wednesday + '\'' +
                        ", thursday =" + thursday + '\'' +
                        ", friday =" + friday + '\'' +
                        ", saturday =" + saturday + '\'' +
                        ", sunday =" + sunday + '\'' +
                        '}';
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public int getEID() {
        return EID;
    }

    public void setEID(int EID) {
        this.EID = EID;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
}
