package com.simple_gtd_01.view;

import com.simple_gtd_01.controller.AbstractController;

public interface AbstractView {

	public void setController(AbstractController controller);
	public AbstractController getController();
	
	public void addNewTaskToView(int identifier, String objective);
	public void addDoneTaskToView(int identifier, String objective);
	public void removeTaskFromView(int id);
}
