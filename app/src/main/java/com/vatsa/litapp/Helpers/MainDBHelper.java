package com.vatsa.litapp.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDBHelper extends SQLiteOpenHelper {

    public MainDBHelper(Context context) {
        super(context, "Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table CloseFriends(friendid TEXT primary key, name TEXT, description TEXT)");
        DB.execSQL("create Table Users(id TEXT primary key, bio TEXT, fullname TEXT, imageurl TEXT, username TEXT)");
        DB.execSQL("create Table Posts(postid TEXT primary key, category TEXT, description TEXT, postimage TEXT, publisher TEXT)"); ///
        DB.execSQL("create Table Notifications(postid TEXT, userid TEXT, ispost INT, texti TEXT, primary key(postid, userid))"); ///
        DB.execSQL("create Table Comments(commentid TEXT primary key, comment TEXT, publisher TEXT)"); ///

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) { }
}
