package com.simple_gtd_01.model;

import java.util.Date;
import java.util.TreeMap;

public class TaskPool {
	int maxId;
	private TreeMap<Integer, Task> tasks;

	public TaskPool(){
		tasks = new TreeMap<Integer, Task>();
		maxId = 0;
	}
	
	public TreeMap<Integer, Task> getTasks(){
		return tasks;
	}
	

	public int createTask(String objective){
		Task task = new Task();
		maxId++;
		task.setTaskObjective(objective);
		task.setTaskState(TaskState.UNDONE);
		task.setTaskId(maxId);
		task.setTaskAddedDate(new Date());
		tasks.put(task.getId(), task);
		return task.getId();
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
		if(state == TaskState.DONE){
			taskToModify.setTaskDoneDate(new Date());
		}
		else{
			taskToModify.setTaskDoneDate(null);
		}
	}
	
	public String getTaskObjective(int id){
		Task task = tasks.get(id);
		return task.getTaskObjective();
	}
	
	public void setTasks(TreeMap<Integer, Task> tasks){
		if(tasks != null){
			this.tasks = tasks;
			if(!tasks.isEmpty()){
				maxId = tasks.lastKey();
			}
			else {
				maxId = 0;
			}
		}
	}
}
