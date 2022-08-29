package com.vatsa.litapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vatsa.litapp.Helpers.MainDBHelper;

public class DBNotificationsHelper extends MainDBHelper {
    public DBNotificationsHelper(Context context) {
        super(context);
    }

    public Boolean insertNotifications(String postid, String userid, int ispost, String texti) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("postid", postid);  ///
        contentValues.put("userid", userid);  ///
        contentValues.put("ispost", ispost);  ///
        contentValues.put("texti", texti);  ///
        long result = DB.insert("Notifications", null, contentValues); ///
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateNotifications(String postid, String userid, int ispost, String texti) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("postid", postid);  ///
        contentValues.put("userid", userid);  ///
        contentValues.put("ispost", ispost);  ///
        contentValues.put("texti", texti);  ///
        Cursor cursor = DB.rawQuery("Select * from Notifications where postid = ? and userid = ?", new String[]{postid, userid}); ///
        if (cursor.getCount() > 0) {
            long result = DB.update("Notifications", contentValues, "postid=? and userid = ?", new String[]{postid, userid}); ///
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteNotifications(String postid, String userid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notifications where postid = ? and userid = ?", new String[]{postid, userid}); ///
        if (cursor.getCount() > 0) {
            long result = DB.delete("Notifications","postid = ? and userid = ?", new String[]{postid, userid});  ///
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
        Cursor cursor = DB.rawQuery("Select * from Notifications", null);  ///
        return cursor;
    }
}
