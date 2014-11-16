package com.simple_gtd_01.model;

import java.util.TreeMap;

import com.simple_gtd_01.model.DatabaseContract.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TaskDao {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = {Entry.COLUMN_ID, Entry.COLUMN_OBJECTIVE, Entry.COLUMN_STATE};
	
	public TaskDao(Context context){
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public void insertTask(Task task){
		ContentValues values = new ContentValues();
		values.put(Entry.COLUMN_ID, task.getId());
		values.put(Entry.COLUMN_OBJECTIVE, task.getTaskObjective());
		values.put(Entry.COLUMN_STATE, task.getTaskState().ordinal());
		database.insert(Entry.TABLE_NAME, null, values);
	}
	
	public TreeMap<Integer, Task> getAllTasks(){
		TreeMap<Integer, Task> tasks = new TreeMap<Integer,Task>();
		Cursor cursor = database.query(Entry.TABLE_NAME, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Task task = new Task();
			task.setTaskObjective(cursor.getString(cursor.getColumnIndex(Entry.COLUMN_OBJECTIVE)));
			task.setTaskId(cursor.getInt(cursor.getColumnIndex(Entry.COLUMN_ID)));
			task.setTaskState(TaskState.values()[cursor.getInt(cursor.getColumnIndex(Entry.COLUMN_STATE))]);
			tasks.put(task.getId(), task);
			cursor.moveToNext();
		}
		cursor.close();
		return tasks;
	}

}
