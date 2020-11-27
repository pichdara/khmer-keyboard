package com.example.khmer_keyboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }




    //to return the single instance of the database

    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //open database
    public void open(){
        this.db = openHelper.getWritableDatabase();

    }

    //close database
    public void close(){
        if (db!= null)
        {
            this.db.close();
        }

    }

    //query and return from database

    public List<String> getSuggestion(StringBuffer word, boolean isAutoComplete){
        String query;
        if (isAutoComplete){
            query = "SELECT Word FROM Table1 WHERE Word LIKE  '"+word+'%'+"' ORDER BY Priority DESC LIMIT 3";
        }
        else {
            query = "SELECT Word2 FROM NextWord WHERE Word1 ='"+word+"' ORDER BY Priority DESC LIMIT 3";
        }
        c=db.rawQuery(query , new String[]{});
        List<String> data = new ArrayList<>();
        while (c.moveToNext()){
            String def = c.getString(0);
            data.add(def);
        }
        return data;

    }

    void updatePrio(String word){
        c=db.rawQuery("SELECT Priority FROM Table1 WHERE Word ='"+word+"' LIMIT 1" , new String[]{});
        int oldPrio = 0;
        while (c.moveToNext()){
            oldPrio = c.getInt(0);
        }
        oldPrio++;
        ContentValues cv = new ContentValues();
        cv.put("Priority", oldPrio);

        db.update("Table1",cv,"Word = '"+word+"'", new String[]{});
    }


}
