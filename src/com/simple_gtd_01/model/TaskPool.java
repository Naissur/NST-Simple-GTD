package com.simple_gtd_01.model;

import java.util.TreeMap;

import android.content.Context;

public class TaskPool {
	int maxId;
	private TreeMap<Integer, Task> tasks;
	//private TaskDao taskDataSource;

	public TaskPool(Context context){
		tasks = new TreeMap<Integer, Task>();
		maxId = 0;
		//taskDataSource = new TaskDao(context);
		//taskDataSource.open();
	}
	

	public int createTask(String objective){
		Task task = new Task();
		maxId++;
		task.setTaskObjective(objective);
		task.setTaskState(TaskState.Undone);
		task.setTaskId(maxId);
		tasks.put(task.getId(), task);
		return task.getId();
	}
	
	public void loadTasksFromDB(){
		//tasks = taskDataSource.getAllTasks();
		maxId = tasks.lastKey();
	}
	
	public void insertTask(Task task){
		tasks.put(task.getId(), task);
	}
	
	public void setTaskObjective(int id, String newObjective){
		Task modifiedTask = tasks.get(id);
		modifiedTask.setTaskObjective(newObjective);
	}
	
	public void removeTask(int id){
		tasks.remove(id);
	}
	
	public void setTaskState(int id, TaskState state){
		Task taskToModify = tasks.get(id);
		taskToModify.setTaskState(state);
	}
	
	public String getTaskObjective(int id){
		Task task = tasks.get(id);
		return task.getTaskObjective();
	}
}
