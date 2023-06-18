package com.example.mytestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(@Nullable Context context) {
        super(context, "MYDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Here I am writting the value into a sql Table
        String createTable_Charts = "create table myTable(xValues REAL, yValues REAL);";
//        String createTablePieChart = "create table myTablePie(xValues REAL, yValues TEXT);";
        sqLiteDatabase.execSQL(createTable_Charts);
//        sqLiteDatabase.execSQL(createTablePieChart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Delete all items in the table
    public void truncateTable(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + tableName);
    }

    public Boolean insertData(float valueX, float valueY){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("xValues", valueX);
        contentValues.put("yValues", valueY);
        sqLiteDatabase.insert("myTable", null, contentValues);

        return true;

    }

}
