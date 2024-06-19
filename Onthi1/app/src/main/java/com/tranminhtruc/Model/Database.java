package com.tranminhtruc.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "dienthoai.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "DIENTHOAI";
    public static final String COL_CODE = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PRICE = "PRICE";
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ("+ COL_CODE + " VARCHAR(50) PRIMARY KEY , " + COL_NAME + " NVARCHAR(50), " + COL_PRICE + " REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TBL_NAME);
        onCreate(db);
    }
    public Cursor QueryData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public int getNumbOfRows(){
        Cursor cursor = QueryData("SELECT * FROM "+ TBL_NAME);
        int numbOfRows = cursor.getCount();
        cursor.close();
        return numbOfRows;
    }
    public void createSampleData(){
        if(getNumbOfRows() == 0 ){
            try{
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES('SP-100','Vertu Constellation',19000)");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES('SP-101','iPhone 5S',18000)");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES('SP-102','Nokia Lumia 925',22000)");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES('SP-103','SamSung Galaxy S4',24000)");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES('SP-104','HTC Desire 600',24000)");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES('SP-105','HKPhone Revo LEAD ',24000)");
            }catch (Exception e){
                Log.e("Error:", e.getMessage().toString());
            }
        }
    }
}
