package com.leonteqsecurity.phishingdetector;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    static String Db_name="AppDatabase.db";
    static int version=1;
    private List<String> credentials;
    public DbHelper(Context context) {
        super(context, Db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE UserEmails(_id INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,email TEXT,password TEXT)");
        db.execSQL("CREATE TABLE emailsInbox(_id INTEGER PRIMARY KEY AUTOINCREMENT,emailsContent TEXT,sender TEXT,Read TEXT,BlackListed TEXT DEFAULT 'no',TimeStamp TEXT,Sent TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public  boolean RegisterUser(String username,String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("Username",username);
        contentValues.put("password",password);
       long response=db.insert("UserEmails",null,contentValues);
       if(response==-1)
       {
           return false;
       }
       else
       {
           return true;
       }



    }
    public  boolean putEmails(String content,String sender,String read,String BlackListed,String TimeStamp,String Sent)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("emailsContent",content);
        contentValues.put("sender",sender);
        contentValues.put("Read",read);
        contentValues.put("BlackListed",BlackListed);
        contentValues.put("TimeStamp",TimeStamp);
        contentValues.put("Sent",Sent);

        long response=db.insert("emailsInbox",null,contentValues);
        if(response==-1)
        {
            return false;
        }
        else
        {
            return true;
        }



    }

    public List<String> getRegisterUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM UserEmails WHERE email=?", new String[] { email });

        List<String> credentials = null;

        if (cursor.moveToFirst()) {
            // The query returned at least one row, so we can extract the data
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            credentials = new ArrayList<>();
            credentials.add(password);
            credentials.add(email);
        }

        cursor.close();
        return credentials;
    }
    public List<String> getAccountEmails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM UserEmails", null);
        List<String> emails = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                emails.add(email);
            } while (cursor.moveToNext());
        }

        cursor.close();
        System.out.println(emails);
        return emails;
    }





}
