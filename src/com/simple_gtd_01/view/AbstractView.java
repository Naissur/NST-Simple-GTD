package com.simple_gtd_01.view;

import android.app.Activity;

import com.simple_gtd_01.controller.AbstractController;

public abstract class AbstractView extends Activity {
	public abstract void setController(AbstractController controller);
	public abstract AbstractController getController();
	public abstract void addNewTaskToView(int identifier, String objective);
	public abstract void addDoneTaskToView(int identifier, String objective);
	public abstract void removeTaskFromView(int id);
	public abstract int getTaskPos(int id);
}
