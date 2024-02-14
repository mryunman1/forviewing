package com.example.employeeside;

import java.io.Serializable;

public class ScheduleModel implements Serializable {
    private String date;
    private int EID1;
    private int EID2;
    private int EID3;
    private int EID4;
    private int EID5;
    private int EID6;

    public ScheduleModel(String date, int EID1, int EID2, int EID3, int EID4, int EID5, int EID6){
        this.date = date;
        this.EID1 = EID1;
        this.EID2 = EID2;
        this.EID3 = EID3;
        this.EID4 = EID4;
        this.EID5 = EID5;
        this.EID6 = EID6;
    }
    public ScheduleModel(){

    }

    public String getDate(){return this.date;}
    public int getEID1(){return this.EID1;}
    public int getEID2(){return this.EID2;}
    public int getEID3(){return this.EID3;}
    public int getEID4(){return this.EID4;}
    public int getEID5(){return this.EID5;}
    public int getEID6(){return this.EID6;}

    public void setDate(String date){this.date = date;}

    public void setEID1(int EID1) {this.EID1 = EID1;}
    public void setEID2(int EID2) {this.EID2 = EID2;}
    public void setEID3(int EID3) {this.EID3 = EID3;}
    public void setEID4(int EID4) {this.EID4 = EID4;}
    public void setEID5(int EID5) {this.EID5 = EID5;}
    public void setEID6(int EID6) {this.EID6 = EID6;}
}