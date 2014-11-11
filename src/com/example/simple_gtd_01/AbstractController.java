package com.example.simple_gtd_01;

public abstract class AbstractController {
	public AbstractController(){
		System.out.println("Controller: Simple GTD AbstractController initialized");
	}
	
	public void setModel(AbstractModel model){
		m_model = model;
		System.out.println("Controller: Set Model");
	}
	
	public abstract void addTaskDialogExecuted(String task_objective);
	
	protected AbstractModel m_model;
}
