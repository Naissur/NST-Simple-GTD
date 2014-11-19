package com.simple_gtd_01.controller;

import com.simple_gtd_01.model.AbstractModel;


public class SimpleGTDController implements AbstractController {

	public SimpleGTDController(AbstractModel model) {
		System.out.println("Controller: Simple GTD Controller initialized");
		m_model = model;
	}

	public void addTaskDialogExecuted(String task_objective) {
		System.out.println("Controller: Adding new task dialog executed, passing to model");
		m_model.addNewTaskToModel(task_objective);
	}

	public void editTaskDialogExecuted(int id, String new_objective){
		m_model.modifyTask(id, new_objective);
	}

	public void setModel(AbstractModel model) {
		m_model = model;
	}
	
	public void setTaskAsDone(int id){
		m_model.setTaskAsDone(id);
	}
	
	public AbstractModel getModel(){
		return m_model;
	}
	
	private AbstractModel m_model;
}
