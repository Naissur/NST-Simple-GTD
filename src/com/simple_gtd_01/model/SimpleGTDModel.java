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
	
	public AbstractView getView(){
		return m_view;
	}

	@Override
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
	public void modifyTask(int id, String newObjective) {
		if(newObjective != null){
            taskPool.setTaskObjective(id, newObjective);
            m_view.modifyTask(id, newObjective);
		}
	}

	@Override
	public void setTaskAsUndone(int id) {
		taskPool.setTaskState(id, TaskState.UNDONE);
		m_view.setTaskAsUndone(id);
	}

	@Override
	public void saveTasksToJson() {
		try {
			FileOutputStream fos = openFileOutput(JSON_DATA_FILENAME, Context.MODE_PRIVATE);
			json.writeToJson(fos, taskPool.getTasks());
		} catch (Exception e) {
			System.out.println("Exception opening file");
			System.out.println(e.toString());
			e.printStackTrace();
		}
>>>>>>> Stashed changes
		
	}
	
	
}
