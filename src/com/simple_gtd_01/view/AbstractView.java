package com.simple_gtd_01.view;

import android.app.Activity;

import com.simple_gtd_01.controller.AbstractController;

public abstract class AbstractView extends Activity {

	public abstract void setController(AbstractController controller);
	
	public abstract void addNewTaskToView(int id, String objective);
	
	public abstract void removeTaskFromView(int id);
	
	public abstract void onNewTaskDialogExecuted(String objective);
	
	public abstract void addDoneTaskToView(int identifier, String objective);
	
}
