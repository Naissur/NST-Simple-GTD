package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public class SimpleGTDModel implements AbstractModel {
	
	private AbstractView m_view;

	public SimpleGTDModel(AbstractView view) {
		System.out.println("Model: Simple GTD Model initialized");
		m_view = view;
	}

	public AbstractView getView(){
		return m_view;
	}

	@Override
	public void addNewTaskToModel(String objective) {
		// TODO Auto-generated method stub
		m_view.addNewTaskToView(0, objective);
	}
}
