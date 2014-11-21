package com.simple_gtd_01.view;

import android.app.Activity;

import com.simple_gtd_01.ViewTask;
import com.simple_gtd_01.controller.AbstractController;

public abstract class AbstractView extends Activity {
	public abstract void setController(AbstractController controller);
	public abstract AbstractController getController();
	public abstract void addNewTaskToView(int identifier, String objective, String date_added);
	public abstract void addDoneTaskToView(int identifier, String objective, String date_added, String date_done);
	public abstract void removeTaskFromView(int id);
	public abstract void modifyTask(int id, String new_obj, String date_added, String date_done);
	public abstract void setTaskAsUndone(int id);
	public abstract void setTaskAsDone(int id, String date_done);
	public abstract int getTaskPos(int id);
}
