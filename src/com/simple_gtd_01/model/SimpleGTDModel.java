package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public class SimpleGTDModel implements AbstractModel {
	
	private TaskPool taskPool;
	private AbstractView m_view;

	public SimpleGTDModel(AbstractView view) {
		System.out.println("Model: Simple GTD Model initialized");
		m_view = view;
		taskPool = new TaskPool();
	}

	public void addNewTaskToModel(String objective) {
		System.out.println("Model: Adding task \""+objective+"\" to model");
		Task task = new Task(objective, TaskState.Undone);
		taskPool.insertTask(task);
		m_view.addNewTaskToView(objective);
	}
}
