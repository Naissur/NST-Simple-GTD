package com.simple_gtd_01.controller;

import com.simple_gtd_01.model.AbstractModel;

public interface AbstractController {
	public void addTaskDialogExecuted(String task_objective);
	public void editTaskDialogExecuted(int id, String objective); // Pass null where no mod is needed
	public AbstractModel getModel();
	public void setTaskAsDone(int id);
}
