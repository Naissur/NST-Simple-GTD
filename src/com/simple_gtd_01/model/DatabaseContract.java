package com.simple_gtd_01.model;

import android.provider.BaseColumns;

public final class DatabaseContract {
	public static abstract class Entry implements BaseColumns{
		public static final String TABLE_NAME = "TasksTable";
		public static final String COLUMN_OBJECTIVE = "TaskObjective";
		public static final String COLUMN_STATE = "TaskState";
		public static final String COLUMN_ID = "TaskId";
	}
}
