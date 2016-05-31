package com.example.sqlitenotetext;

import java.io.Serializable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper
{
	public MyOpenHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table note(_id integer primary key autoincrement, title text, content text)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}
}
