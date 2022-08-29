package com.vatsa.litapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vatsa.litapp.Helpers.MainDBHelper;

public class DBUserHelper extends MainDBHelper {
    public DBUserHelper(Context context) {
        super(context);
    }

    public Boolean insertUsers(String id, String bio, String fullname, String imageurl, String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);  ///
        contentValues.put("bio", bio);  ///
        contentValues.put("fullname", fullname);  ///
        contentValues.put("imageurl", imageurl);  ///
        contentValues.put("username", username);  ///
        long result = DB.insert("Users", null, contentValues); ///
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateUsers(String id, String bio, String fullname, String imageurl, String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);  ///
        contentValues.put("bio", bio);  ///
        contentValues.put("fullname", fullname);  ///
        contentValues.put("imageurl", imageurl);  ///
        contentValues.put("username", username);  ///
        Cursor cursor = DB.rawQuery("Select * from Users where id = ?", new String[]{id}); ///
        if (cursor.getCount() > 0) {
            long result = DB.update("Users", contentValues, "id=?", new String[]{id}); ///
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteUsers(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users where id = ?", new String[]{id}); ///
        if (cursor.getCount() > 0) {
            long result = DB.delete("Users","id=?", new String[]{id});  ///
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users", null);  ///
        return cursor;
    }
}

