package com.preghealth.lmnop.pregnancyhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Chitkara Neetu on 5/15/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    String name;
    String email;
    ArrayList<Contacts> clist;
    static SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, "contacts", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="Create table contacts ( id integer primary key autoincrement,name text, email text);";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addName(String name, String email){
        db=this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email",email);
        db.insert("contacts", null, cv);
        db.close();


    }

    public ArrayList<Contacts> getnames(){

      db=this.getReadableDatabase();

        String all = "Select * from contacts";
       Cursor cursor = db.rawQuery(all,null);
        clist = new ArrayList<Contacts>();

        if (cursor.moveToFirst())

            do {
           Contacts c = new Contacts();
            c.setName(cursor.getString(1));
            c.setEmail(cursor.getString(2));
            clist.add(c);}  while(cursor.moveToNext());

    return clist;}


    public int  deleterecord(String a, String b){
    db=this.getWritableDatabase();
    int deleteentries =db.delete("contacts","name=? AND email=?",new String[]{a,b});

    return deleteentries;
    }
}
