package com.example.employeeside;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDBAssist extends SQLiteOpenHelper {
    //employee table columns
    public static final String EMPLOYEE_TABLE = "EMPLOYEE_TABLE";

    public static final String COLUMN_EMPLOYEE_F_NAME = "EMPLOYEE_F_NAME";
    public static final String COLUMN_EMPLOYEE_L_NAME = "EMPLOYEE_L_NAME";
    public static final String COLUMN_EMPLOYEE_EMAIL = "EMPLOYEE_EMAIL";
    public static final String COLUMN_EMPLOYEE_PHONENO = "EMPLOYEE_PHONENO";
    public static final String COLUMN_EMPLOYEE_STATUS = "EMPLOYEE_STATUS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TRAINING = "TRAINING";
    // availability columns
    public static final String AVAILABILITY_TABLE = "AVAILABILITY_TABLE";
    public static final String COLUMN_AID = "AID";
    public static final String EMPLOYEE_ID = "EID";
    public static final String COLUMN_MONDAY = "MONDAY";
    public static final String COLUMN_TUESDAY = "TUESDAY";
    public static final String COLUMN_WEDNESDAY = "WEDNESDAY";
    public static final String COLUMN_THURSDAY = "THURSDAY";
    public static final String COLUMN_FRIDAY = "FRIDAY";
    public static final String COLUMN_SATURDAY = "SATURDAY";
    public static final String COLUMN_SUNDAY = "SUNDAY";
    //Database for schedule
    public static final String SCHEDULE_TABLE = "SCHEDULE_TABLE";
    public static final String DATE = "DATE";
    public static final String EID_1 = "EID_1";
    public static final String EID_2 = "EID_2";
    public static final String EID_3 = "EID_3";
    public static final String EID_4 = "EID_4";
    public static final String EID_5 = "EID_5";
    public static final String EID_6 = "EID_6";

    public EmployeeDBAssist(@Nullable Context context) {
        super(context, "employee.db", null, 1);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }
    //when database is accessed, create new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + EMPLOYEE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMPLOYEE_F_NAME + " TEXT, " + COLUMN_EMPLOYEE_L_NAME + " TEXT, "
                + COLUMN_EMPLOYEE_EMAIL + " TEXT, " + COLUMN_EMPLOYEE_PHONENO + " TEXT, "
                + COLUMN_EMPLOYEE_STATUS + " BOOL, " + COLUMN_TRAINING + " TEXT );";
        String createAvailTable = "CREATE TABLE " + AVAILABILITY_TABLE +
                " (" + COLUMN_AID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MONDAY + " TEXT, " + COLUMN_TUESDAY + " TEXT, " +
                EMPLOYEE_ID + " INTEGER, " +
                COLUMN_WEDNESDAY + " TEXT, " + COLUMN_THURSDAY +
                " TEXT, " + COLUMN_FRIDAY + " TEXT, " + COLUMN_SATURDAY +
                " TEXT, " + COLUMN_SUNDAY + " TEXT, " +
                " FOREIGN KEY (" + EMPLOYEE_ID + ") REFERENCES " + EMPLOYEE_TABLE + "(" + COLUMN_ID +  ") ON DELETE CASCADE); ";
        String createSchedule = "CREATE TABLE " + SCHEDULE_TABLE + " ("  +
                DATE + " TEXT PRIMARY KEY, " + EID_1 + " INTEGER, " + EID_2 + " INTEGER, " + EID_3 + " INTEGER, " +
                EID_4 + " INTEGER, " + EID_5 + " INTEGER, " + EID_6 + " INTEGER );";

        Log.d("TAG", createTable);
        Log.d("TAG", createAvailTable);
        Log.d("TAG", createSchedule);
        db.execSQL(createTable);
        db.execSQL(createAvailTable);
        db.execSQL(createSchedule);
    }
    //version number change, prevention of crashing from older apps
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<String> getAvail(int employeeID){
        List<String>availList = new ArrayList();
        String empID = String.valueOf(employeeID);
        String getEidInstance = "SELECT * FROM " + AVAILABILITY_TABLE + " WHERE " + EMPLOYEE_ID + " = " + empID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getEidInstance, null);

        if (cursor.moveToFirst()){
            do{
                String monday = cursor.getString(1);
                String tuesday = cursor.getString(2);
                String wednesday = cursor.getString(4);
                String thursday = cursor.getString(5);
                String friday = cursor.getString(6);
                String saturday = cursor.getString(7);
                String sunday = cursor.getString(8);

                availList.add(monday);
                availList.add(tuesday);
                availList.add(wednesday);
                availList.add(thursday);
                availList.add(friday);
                availList.add(saturday);
                availList.add(sunday);



            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return availList;
    }

    public String getTraining(int employeeID){
        String trainStatus;
        trainStatus = "None";
        String empID = String.valueOf(employeeID);
        String getEidInstance = "SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + COLUMN_ID + " = " + empID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getEidInstance, null);

        if (cursor.moveToFirst()){
            do{
                String training = cursor.getString(6);

                trainStatus = training;



            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return trainStatus;
    }

    public boolean addAvailability(AvailabilityModel availabilityModel){
        String value = String.valueOf(availabilityModel.getEID());
        if(!doesEIDExist(value)){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(EMPLOYEE_ID, availabilityModel.getEID());
            cv.put(COLUMN_MONDAY, availabilityModel.getMonday());
            cv.put(COLUMN_TUESDAY, availabilityModel.getTuesday());
            cv.put(COLUMN_WEDNESDAY, availabilityModel.getWednesday());
            cv.put(COLUMN_THURSDAY, availabilityModel.getThursday());
            cv.put(COLUMN_FRIDAY, availabilityModel.getFriday());
            cv.put(COLUMN_SATURDAY, availabilityModel.getSaturday());
            cv.put(COLUMN_SUNDAY, availabilityModel.getSunday());


            long insert = db.insert(AVAILABILITY_TABLE,null , cv);
            return insert != -1;

        } else{
            return true;
        }

    }
    // this function is to check whether or not our EID already exists
    public boolean doesEIDExist(String value){
        String checkEidInstance = "SELECT * FROM " + AVAILABILITY_TABLE + " WHERE " + EMPLOYEE_ID + " = ?";
        String[] whereArgs = {value};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(checkEidInstance, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }
    public boolean updateAvailability(int EmpID, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday){
        String EIDs = String.valueOf(EmpID);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MONDAY, monday);
        cv.put(COLUMN_TUESDAY, tuesday);
        cv.put(COLUMN_WEDNESDAY, wednesday);
        cv.put(COLUMN_THURSDAY, thursday);
        cv.put(COLUMN_FRIDAY, friday);
        cv.put(COLUMN_SATURDAY, saturday);
        cv.put(COLUMN_SUNDAY, sunday);

        Log.d("TAG", "Update Employee # " + EIDs);
        return db.update("AVAILABILITY_TABLE", cv,EMPLOYEE_ID + " = ?", new String[]{EIDs}) > 0;
    }
    public boolean addOne(EmployeeModel employeeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EMPLOYEE_F_NAME, employeeModel.getfName());
        cv.put(COLUMN_EMPLOYEE_L_NAME, employeeModel.getlName());
        cv.put(COLUMN_EMPLOYEE_EMAIL, employeeModel.getEmail());
        cv.put(COLUMN_EMPLOYEE_PHONENO, employeeModel.getPhoneNo());
        cv.put(COLUMN_EMPLOYEE_STATUS, employeeModel.isStatus());
        cv.put(COLUMN_TRAINING, employeeModel.getTraining());

        long insert = db.insert(EMPLOYEE_TABLE,null , cv);
        if(insert == -1){
            return false;
        }
        else
            return true;
    }
    public boolean deleteOne(int EmpIDs){
        //find employee in database, delete then return true
        //if employee not in database then return false
        String EIDs = String.valueOf(EmpIDs);
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("TAG", "Update Employee # " + EIDs);
        return db.delete("EMPLOYEE_TABLE",COLUMN_ID + " = ?", new String[]{EIDs}) > 0;
    }
    public boolean updateOne(int EmpID, String fName, String lName, String email, String pNum, boolean status ,String training){
        String EIDs = String.valueOf(EmpID);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EMPLOYEE_F_NAME, fName);
        cv.put(COLUMN_EMPLOYEE_L_NAME, lName);
        cv.put(COLUMN_EMPLOYEE_EMAIL, email);
        cv.put(COLUMN_EMPLOYEE_PHONENO, pNum);
        cv.put(COLUMN_EMPLOYEE_STATUS, status);
        cv.put(COLUMN_TRAINING, training);

        Log.d("TAG", "Update Employee # " + EIDs);
        return db.update("EMPLOYEE_TABLE", cv,COLUMN_ID + " = ?", new String[]{EIDs}) > 0;
    }
    public List<EmployeeModel> getAllEmployees(){
        List<EmployeeModel> returnList = new ArrayList();
        //getting employee.db data
        String queryString =  "SELECT * FROM " + EMPLOYEE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //if there is a first value in array iterate through data
            do {
                int employeeID = cursor.getInt(0);
                String employeeFName = cursor.getString(1);
                String employeeLName = cursor.getString(2);
                String employeeEmail = cursor.getString(3);
                String employeePhoneNo = cursor.getString(4);
                boolean employeeStatus = cursor.getInt(5) == 1 ? true: false;
                String employeeTraining = cursor.getString(6);

                EmployeeModel em = new EmployeeModel(employeeID, employeeFName, employeeLName, employeeEmail, employeePhoneNo, employeeStatus, employeeTraining);
//                employeeListAll.add(em);
                returnList.add(em);
            }while(cursor.moveToNext());
        }
        else{
            //there is an empty database

        }
        cursor.close();
        db.close();
        return returnList;
    }
    public List<AvailabilityModel>getAllAvailable(){
        List<AvailabilityModel> returnList = new ArrayList();
        //getting employee.db data
        String queryString = "SELECT * FROM " + AVAILABILITY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                int aid = cursor.getInt(0);
                String monday = cursor.getString(1);
                String tuesday = cursor.getString(2);
                int eid = cursor.getInt(3);
                String wednesday = cursor.getString(4);
                String thursday = cursor.getString(5);
                String friday = cursor.getString(6);
                String saturday = cursor.getString(7);
                String sunday = cursor.getString(8);

                AvailabilityModel av = new AvailabilityModel(aid,eid, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
                returnList.add(av);


            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
    public ScheduleModel getDateModel(String date){
        ScheduleModel returnDate = new ScheduleModel();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        String queryString =  "SELECT * FROM " + SCHEDULE_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //if there is a first value in array iterate through data
            do {
                String dbDate = cursor.getString(0);
                if(dbDate.equals(date)) {
                    int eid1 = cursor.getInt(1);
                    int eid2 = cursor.getInt(2);
                    int eid3 = cursor.getInt(3);
                    int eid4 = cursor.getInt(4);
                    int eid5 = cursor.getInt(5);
                    int eid6 = cursor.getInt(6);


                    returnDate = new ScheduleModel(date, eid1, eid2, eid3, eid4, eid5, eid6);
                }
//                employeeListAll.add(em);
            }while(cursor.moveToNext());
        }
        else{
            int eid1 = 0;
            int eid2 = 0;
            int eid3 = 0;
            int eid4 = 0;
            int eid5 = 0;
            int eid6 = 0;


            returnDate = new ScheduleModel(date, eid1, eid2, eid3, eid4, eid5, eid6);
            //there is an empty database

        }
        cursor.close();
        db.close();

        return returnDate;
    }
    public EmployeeModel getEmployeeModel(int ID){
        if(ID == 0){
            return new EmployeeModel(0, "", "","","",false,"");
        }
        EmployeeModel returnModel = new EmployeeModel();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        String queryString =  "SELECT * FROM " + EMPLOYEE_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //if there is a first value in array iterate through data
            do {
                int EID = cursor.getInt(0);
                if(EID == ID) {
                    String eid1 = cursor.getString(1);
                    String eid2 = cursor.getString(2);
                    String eid3 = cursor.getString(3);
                    String eid4 = cursor.getString(4);
                    boolean eid5 = (cursor.getInt(5)==1);
                    String eid6 = cursor.getString(6);


                    returnModel = new EmployeeModel(ID, eid1, eid2, eid3, eid4, eid5, eid6);
                }
//                employeeListAll.add(em);
            }while(cursor.moveToNext());
        }
        else{
            //there is an empty database

        }
        cursor.close();
        db.close();

        return returnModel;
    }
    public void deleteSchedule(int EmpIDs, LocalDate today){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "SELECT * FROM " + SCHEDULE_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        ContentValues cv = new ContentValues();
        LocalDate date;
        if(cursor.moveToFirst()){
            //if there is a first value in array iterate through data
            do {
                date = LocalDate.parse(cursor.getString(0));
                if(today.isBefore(date)) {
                    if (cursor.getInt(1) == EmpIDs) {
                        cv.put(EID_1, 0);
                    } else {
                        cv.put(EID_1, cursor.getInt(1));
                    }
                    if (cursor.getInt(2) == EmpIDs) {
                        cv.put(EID_2, 0);
                    }
                    if (cursor.getInt(3) == EmpIDs) {
                        cv.put(EID_3, 0);
                    }
                    if (cursor.getInt(4) == EmpIDs) {
                        cv.put(EID_4, 0);
                    }
                    if (cursor.getInt(5) == EmpIDs) {
                        cv.put(EID_5, 0);
                    }
                    if (cursor.getInt(6) == EmpIDs) {
                        cv.put(EID_6, 0);
                    }
                }
                else {
                    cv.put(EID_1, cursor.getInt(1));
                }
                db.update(SCHEDULE_TABLE, cv, DATE + " = ?", new String[]{date.toString()});
            }while(cursor.moveToNext());
        }
        else{
            //there is an empty database

        }
        cursor.close();
        db.close();
//        String deleteSched = "UPDATE " + SCHEDULE_TABLE + " SET " +
//                EID_1 + " = REPLACE(" + EID_1 + ", " + EmpIDs + ", 0) ";
//        db.execSQL(deleteSched);
    }
    public boolean doesDateExist(String value){
        String checkEidInstance = "SELECT * FROM " + SCHEDULE_TABLE + " WHERE " + DATE + " = ?";
        String[] whereArgs = {value};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(checkEidInstance, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }
    public boolean addDate(ScheduleModel scheduleModel){
        String value = String.valueOf(scheduleModel.getDate());
        if(!doesDateExist(value)){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(DATE, scheduleModel.getDate());
            cv.put(EID_1, scheduleModel.getEID1());
            cv.put(EID_2, scheduleModel.getEID2());
            cv.put(EID_3, scheduleModel.getEID3());
            cv.put(EID_4, scheduleModel.getEID4());
            cv.put(EID_5, scheduleModel.getEID5());
            cv.put(EID_6, scheduleModel.getEID6());



            long insert = db.insert(SCHEDULE_TABLE,null , cv);
            return insert != -1;

        } else{
            return true;
        }

    }
    public boolean updateSchedule(String date, int EID1, int EID2, int EID3, int EID4, int EID5, int EID6){
        String EIDs = String.valueOf(date);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EID_1, EID1);
        cv.put(EID_2, EID2);
        cv.put(EID_3, EID3);
        cv.put(EID_4, EID4);
        cv.put(EID_5, EID5);
        cv.put(EID_6, EID6);

        Log.d("TAG", "Update Employee # " + EIDs);
        return db.update("SCHEDULE_TABLE", cv,DATE + " = ?", new String[]{EIDs}) > 0;
    }
}
