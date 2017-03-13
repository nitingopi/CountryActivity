package com.example.nitin.countryactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by quest on 18/1/17.
 */
public class MyDatabaseHandler extends SQLiteOpenHelper {

    MyDatabaseHandler(Context c)
    {
        super(c, "testdb2", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "create table countrytable (countryid Integer primary key, countryname text, currency text )";
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists countrytable");
        onCreate(db);
    }

    public String addCountryDetails(String name,String currency)
    {
        SQLiteDatabase obj = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("countryname", name);
        contentValues.put("currency", currency);
        obj.insert("countrytable", null, contentValues);
        obj.close();
        Log.i("database", "inserted country " + name + " currency " + currency);
        return "Inserted successfully";
    }
    public ArrayList getAllCountryDetails()
    {
        SQLiteDatabase obj = this.getReadableDatabase();
        String query = "Select * from countrytable";
        Cursor rs = obj.rawQuery(query, null);
        ArrayList list = new ArrayList();


        String countryStr;
        //Map<String, String> map ;//= new HashMap<String, String>();
        if(rs != null)
        {
            rs.moveToFirst();
            if(rs.isFirst())
            {
                do{
                    int id = Integer.parseInt(rs.getString(0));
                    String countryname = rs.getString(1);
                    String currency = rs.getString(2);
                    Log.i("Database", id + " " + countryname + " " +currency);

                    String [] countryArray = {rs.getString(0),countryname, currency };
                    list.add(countryArray);

                }while(rs.moveToNext());
            }
        }
        return list;

    }
    public void deleteAllCountries()
    {
        SQLiteDatabase obj = this.getWritableDatabase();

        obj.delete("countrytable",null,null );
        obj.close();
    }

    public String getItemById(int id)
    {
        SQLiteDatabase obj = this.getReadableDatabase();
        String query = "Select * from countrytable where countryid = "+id;
        Cursor rs = obj.rawQuery(query, null);
        String currency= "";
        if(rs != null) {
            rs.moveToFirst();
            if (rs.isFirst()) {
                currency = rs.getString(2);
                Log.i("DB : " ,currency);
            }
        }
        return currency;


    }
    public String updateCountryByid(int id, String name, String currency)
    {
        String backMsg = "";
        SQLiteDatabase obj = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("countryname", name);
        values.put("currency", currency);
        obj.update("countrytable", values, "countryid" + " = ?",
                new String[]{String.valueOf(id) });
        backMsg = "Updated successfully...";
        return backMsg;
    }

}
