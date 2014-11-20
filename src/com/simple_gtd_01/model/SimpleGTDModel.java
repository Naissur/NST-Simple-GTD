package com.simple_gtd_01.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import android.content.Context;
import android.content.ContextWrapper;

import com.simple_gtd_01.view.AbstractView;

public class SimpleGTDModel extends ContextWrapper implements AbstractModel {
	
	public static final String JSON_DATA_FILENAME = "tasksJson";
	
	private TaskPool taskPool;
	private AbstractView m_view;
	private JsonTaskWorker json;

	public SimpleGTDModel(AbstractView view) {
		super(view);
		m_view = view;
		json = new JsonTaskWorker();
		taskPool = new TaskPool();
		System.out.println("Model: Simple GTD Model initialized");
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
		taskPool.setTaskState(id, TaskState.DONE);
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

	@Override
	public void setTaskAsUndone(int id) {
		taskPool.setTaskState(id, TaskState.UNDONE);
		String taskObjective = taskPool.getTaskObjective(id);
		m_view.removeTaskFromView(id);
		m_view.addNewTaskToView(id, taskObjective);
	}

	@Override
	public void saveTasksToJson() {
		try {
			this.updateTasksOrder();
			FileOutputStream fos = openFileOutput(JSON_DATA_FILENAME, Context.MODE_PRIVATE);
			json.writeToJson(fos, taskPool.getTasks());
		} catch (Exception e) {
			System.out.println("Exception opening file");
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
	}

	@Override
	public void readTasksFromJson() {
		FileInputStream fis = null;
		boolean doesFileExists = false;
		String[] files = fileList();
		for(String s : files){
			if(s.equals(JSON_DATA_FILENAME)){
				doesFileExists = true;
			}
		}
		if(doesFileExists){
			try {
				fis = openFileInput(JSON_DATA_FILENAME);
				TreeMap<Integer, Task> tasksMap = json.readFromJson(fis);
				taskPool.setTasks(tasksMap);
			} catch (FileNotFoundException e) {
				System.out.println("Exception in opening file");
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendAllTasksToView() {
		this.updateTasksOrder();
		TreeMap<Integer, Task> tasks = taskPool.getTasks();
		TreeSet<Task> orderedTasks = new TreeSet<Task>();
		for (Map.Entry<Integer, Task> task : tasks.entrySet()){
			orderedTasks.add(task.getValue());
		}
		for (Iterator<Task> i = orderedTasks.descendingIterator();i.hasNext();){
			Task task = i.next();
			if(task.getTaskState() == TaskState.UNDONE){
				m_view.addNewTaskToView(task.getId(), task.getTaskObjective());
			}
			else{
				m_view.addDoneTaskToView(task.getId(), task.getTaskObjective());
			}
		}
	}

	@Override
	public void updateTasksOrder() {
		for (Map.Entry<Integer, Task> task : taskPool.getTasks().entrySet()){
			task.getValue().setTaskOrder(m_view.getTaskPos(task.getKey()));
		}
		
	}
}
