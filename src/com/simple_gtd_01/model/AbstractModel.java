package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public interface AbstractModel {
	public abstract void addNewTaskToModel(String objective);
	public abstract void setTaskAsDone(int id);
	public abstract void setTaskAsUndone(int id);
	public abstract void removeTaskFromModel(int id);
	public abstract void setTaskObjective(int id, String newObjective);
}
