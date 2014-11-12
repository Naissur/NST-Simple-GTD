package com.simple_gtd_01.view;

import com.simple_gtd_01.controller.AbstractController;

public abstract class AbstractView {
	public AbstractView(){
		System.out.println("View: Simple GTD AbstractView initialized");
	}
	
	public void setController(AbstractController controller){
		m_controller = controller;
		System.out.println("View: Set Controller");
	}
	
	public abstract void addNewTaskToView(String objective);
	
	public abstract void onNewTaskDialogExecuted(String objective);
	
	protected AbstractController m_controller;
}
