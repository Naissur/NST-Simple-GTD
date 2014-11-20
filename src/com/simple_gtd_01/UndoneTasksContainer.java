package com.simple_gtd_01;

import android.app.Activity;
import android.view.View;

public class UndoneTasksContainer{
	private View m_view;
	
	public View getView(){
		return m_view;
	}

	public UndoneTasksContainer(Activity context) {
		m_view = context.getLayoutInflater().inflate(R.layout.undone_tasks_container, null);
	}

}
