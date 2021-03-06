package com.simple_gtd_01.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import android.content.Context;
import android.content.ContextWrapper;

import com.simple_gtd_01.ViewTask;
import com.simple_gtd_01.ViewTask.State;
import com.simple_gtd_01.view.AbstractView;

public class SimpleGTDModel extends ContextWrapper implements AbstractModel {
	
	public static final String JSON_DATA_FILENAME = "tasksJson";
	
	private TaskPool taskPool;
	private AbstractView m_view;
	private JsonTaskWorker json;
	private SimpleDateFormat dateFormat;

	public SimpleGTDModel(AbstractView view) {
		super(view);
		m_view = view;
		json = new JsonTaskWorker();
		taskPool = new TaskPool();
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Model: Simple GTD Model initialized");
	}
	
	public AbstractView getView(){
		return m_view;
	}

	@Override
	public void addNewTaskToModel(String objective) {
		System.out.println("Model: Adding task \""+objective+"\" to model");
		int id = taskPool.createTask(objective);
		m_view.addNewTaskToView(id, objective, dateFormat.format(taskPool.getTaskAddedDate(id)));
	}

	@Override
	public void setTaskAsDone(int id) {
		taskPool.setTaskState(id, TaskState.DONE);
		m_view.setTaskAsDone(id, dateFormat.format(taskPool.getTaskDoneDate(id)));
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
            m_view.modifyTask(id, newObjective, null, null);
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
	public void sendAllTasksToView() {   // If invoked in runtime, use this.updateTasksOrder()
		TreeMap<Integer, Task> tasks = taskPool.getTasks();
		TreeSet<Task> orderedTasks = new TreeSet<Task>();
		for (Map.Entry<Integer, Task> task : tasks.entrySet()){
			orderedTasks.add(task.getValue());
		}
		for (Iterator<Task> i = orderedTasks.descendingIterator();i.hasNext();){
			Task task = i.next();
			if(task.getTaskState() == TaskState.UNDONE){
				m_view.addNewTaskToView(task.getId(), task.getTaskObjective(), dateFormat.format(task.getTaskAddedDate()));
			}
			else{
				m_view.addDoneTaskToView(task.getId(), task.getTaskObjective(), dateFormat.format(task.getTaskAddedDate()),
						dateFormat.format(task.getTaskDoneDate()));
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
