package com.simple_gtd_01.model;

import java.util.TreeMap;
import java.util.UUID;

public class TaskPool {
	private TreeMap<UUID, Task> Tasks;

	public TaskPool(){
		Tasks = new TreeMap<UUID, Task>();
	}
	
	public void insertTask(Task task){
		Tasks.put(task.getId(), task);
	}
	
	public void modifyTask(UUID id, String newObjective){
		Task modifiedTask = Tasks.get(id);
		modifiedTask.setTaskName(newObjective);
	}
	
	public void deleteTask(UUID id){
		Tasks.remove(id);
	}
}
