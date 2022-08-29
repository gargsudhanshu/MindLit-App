package com.vatsa.litapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vatsa.litapp.Helpers.MainDBHelper;

public class DBCommentsHelper extends MainDBHelper {
    public DBCommentsHelper(Context context) {
        super(context);
    }


    public Boolean insertComments(String commentid, String comment, String publisher) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("commentid", commentid);  ///
        contentValues.put("comment", comment);  ///
        contentValues.put("publisher", publisher);  ///
        long result = DB.insert("Comments", null, contentValues); ///
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateComments(String commentid, String comment, String publisher) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("commentid", commentid);  ///
        contentValues.put("comment", comment);  ///
        contentValues.put("publisher", publisher);  ///
        Cursor cursor = DB.rawQuery("Select * from Comments where commentid = ?", new String[]{commentid}); ///
        if (cursor.getCount() > 0) {
            long result = DB.update("Comments", contentValues, "commentid=?", new String[]{commentid}); ///
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteComments(String commentid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Comments where commentid = ?", new String[]{commentid}); ///
        if (cursor.getCount() > 0) {
            long result = DB.delete("Comments","commentid = ?", new String[]{commentid});  ///
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
        Cursor cursor = DB.rawQuery("Select * from Comments", null);  ///
        return cursor;
    }
}
