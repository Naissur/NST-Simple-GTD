package com.simple_gtd_01.model;

import com.simple_gtd_01.view.AbstractView;

public class SimpleGTDModel implements AbstractModel {
	
	private TaskPool taskPool;
	private AbstractView m_view;

	public SimpleGTDModel(AbstractView view) {
		System.out.println("Model: Simple GTD Model initialized");
		m_view = view;
		taskPool = new TaskPool(m_view);
	}

	public void addNewTaskToModel(String objective) {
		System.out.println("Model: Adding task \""+objective+"\" to model");
		int id = taskPool.createTask(objective);
		m_view.addNewTaskToView(id, objective);
	}

	@Override
	public void setTaskAsDone(int id) {
		taskPool.setTaskState(id, TaskState.Done);
		String taskObjective = taskPool.getTaskObjective(id);
		m_view.removeTaskFromView(id);
		m_view.addDoneTaskToView(id, taskObjective);
	}

	@Override
	public void removeTaskFromModel(int id) {
		taskPool.removeTask(id);
		m_view.removeTaskFromView(id);
		
	}

	@Override
	public void setTaskObjective(int id, String newObjective) {
		taskPool.setTaskObjective(id, newObjective);
		//TODO Call View method!!!!
		
	}
	
	
	
	
}
