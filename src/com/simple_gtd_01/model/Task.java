package com.simple_gtd_01.model;

import java.util.UUID;

public class Task {
	private String taskObjective;
	private UUID id;
	private TaskState state;

	//Constructors
	//Initializing Constructor
	public Task(String taskObjective, TaskState state){
		this.taskObjective = taskObjective;
		this.id = UUID.randomUUID();
		this.state = state;
	}
	//Copy Constructor
	public Task(Task templateTask){
		this.taskObjective = templateTask.getTaskName();
		this.id = templateTask.getId();
	}
	
	//Setters
	public void setTaskName(String taskObjective){
		this.taskObjective = taskObjective;
	}
	public void setTaskState(TaskState state){
		this.state = state;
	}
	
	//Getters
	public String getTaskName(){
		return taskObjective;
	}
	public UUID getId(){
		return id;
	}
	public TaskState getTaskState(){
		return state;
	}
}

