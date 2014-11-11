package com.example.simple_gtd_01;

public class SimpleGTDModel extends AbstractModel {

	public SimpleGTDModel() {
		super();
		System.out.println("Model: Simple GTD Model initialized");
	}

	@Override
	public void addNewTaskToModel(String objective) {
		System.out.println("Model: Adding task \""+objective+"\" to model");
		
		m_view.addNewTaskToView(objective);
	}

}
