package com.simple_gtd_01.model;

import com.simple_gtd_01.model.DatabaseContract.Entry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Tasks.db";
	private static final String SQL_CREATE_STATEMENT = "CREATE TABLE" + 
			Entry.TABLE_NAME + " (" + Entry.COLUMN_ID + "INTEGER PRIMARY KEY" + 
			Entry.COLUMN_OBJECTIVE + " TEXT," +
			Entry.COLUMN_STATE + " INTEGER)";
	private static final String SQL_DELETE_STATEMENT = "DROP TABLE IF EXISTS " +
			Entry.TABLE_NAME;
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_STATEMENT);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_STATEMENT);
		onCreate(db);
		
	}
	
	
}
