package com.vatsa.litapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vatsa.litapp.Helpers.MainDBHelper;

public class DBPostsHelper extends MainDBHelper {
    public DBPostsHelper(Context context) {
        super(context);
    }


    public Boolean insertPosts(String postid, String category, String description, String postimage, String publisher) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("postid", postid);  ///
        contentValues.put("category", category);  ///
        contentValues.put("description", description);  ///
        contentValues.put("postimage", postimage);  ///
        contentValues.put("publisher", publisher);  ///
        long result = DB.insert("Posts", null, contentValues); ///
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updatePosts(String postid, String category, String description, String postimage, String publisher) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("postid", postid);  ///
        contentValues.put("category", category);  ///
        contentValues.put("description", description);  ///
        contentValues.put("postimage", postimage);  ///
        contentValues.put("publisher", publisher);  ///
        Cursor cursor = DB.rawQuery("Select * from Posts where postid = ?", new String[]{postid}); ///
        if (cursor.getCount() > 0) {
            long result = DB.update("Posts", contentValues, "postid=?", new String[]{postid}); ///
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletePosts(String postid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Posts where postid = ?", new String[]{postid}); ///
        if (cursor.getCount() > 0) {
            long result = DB.delete("Posts","postid=?", new String[]{postid});  ///
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
        Cursor cursor = DB.rawQuery("Select * from Posts", null);  ///
        return cursor;
    }
}
