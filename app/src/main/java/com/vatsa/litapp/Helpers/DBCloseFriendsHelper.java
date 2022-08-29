package com.vatsa.litapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vatsa.litapp.Helpers.MainDBHelper;

public class DBCloseFriendsHelper extends MainDBHelper {
    public DBCloseFriendsHelper(Context context) {
        super(context);
    }

    public Boolean insertclosefriends(String name, String friendid, String description) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("friendid", friendid);
        contentValues.put("description", description);
        long result = DB.insert("CloseFriends", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateclosefriends(String name, String friendid, String description) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("friendid", friendid);
        contentValues.put("description", description);
        Cursor cursor = DB.rawQuery("Select * from CloseFriends where friendid = ?", new String[]{friendid});
        if (cursor.getCount() > 0) {
            long result = DB.update("CloseFriends", contentValues, "friendid=?", new String[]{friendid});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteclosefriends(String friendid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from CloseFriends where friendid = ?", new String[]{friendid});
        if (cursor.getCount() > 0) {
            long result = DB.delete("CloseFriends","friendid=?", new String[]{friendid});
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
        Cursor cursor = DB.rawQuery("Select * from CloseFriends", null);
        return cursor;
    }
}
