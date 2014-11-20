package com.simple_gtd_01;

import android.app.Activity;
import android.view.View;

public class DoneTasksContainer{
	private View m_view;
	
	public View getView(){
		return m_view;
	}

	public DoneTasksContainer(Activity context) {
		m_view = context.getLayoutInflater().inflate(R.layout.done_tasks_container, null);
	}

}
