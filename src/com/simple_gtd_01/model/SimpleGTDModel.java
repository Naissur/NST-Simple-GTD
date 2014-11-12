package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public class SimpleGTDModel implements AbstractModel {

	public SimpleGTDModel(AbstractView view) {
		System.out.println("Model: Simple GTD Model initialized");
		m_view = view;
	}

	public void addNewTaskToModel(String objective) {
		System.out.println("Model: Adding task \""+objective+"\" to model");
		m_view.addNewTaskToView(0, objective);   // GENERATE IDENTIFIER!
	}

	private AbstractView m_view;
}
