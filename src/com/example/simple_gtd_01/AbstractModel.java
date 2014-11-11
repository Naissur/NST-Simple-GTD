package com.example.simple_gtd_01;

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
