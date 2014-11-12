package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public abstract class AbstractModel {
	public AbstractModel(){
		System.out.println("Model: Simple GTD AbstractModel initialized");
	}
	
	public void setView(AbstractView view){
		m_view = view;
		System.out.println("Model: Set View");
	}
	
	public abstract void addNewTaskToModel(String objective);
	
	protected AbstractView m_view;
}
