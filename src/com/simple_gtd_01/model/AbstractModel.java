package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public interface AbstractModel {
	public void addNewTaskToModel(String objective);
	public AbstractView getView();

	public void setTaskAsDone(int id);
}
