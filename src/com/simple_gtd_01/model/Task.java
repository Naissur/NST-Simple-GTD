package com.simple_gtd_01.model;


public class Task {
	private String taskObjective;
	private int id;
	private TaskState state;

	//Constructors
	//Initializing Constructor
	public Task(String taskObjective, int id, TaskState state){
		this.taskObjective = taskObjective;
		this.id = id;
		this.state = state;
	}
	public Task(){
		
	}
	
	//Setters
	public void setTaskObjective(String taskObjective){
		this.taskObjective = taskObjective;
	}
	public void setTaskState(TaskState state){
		this.state = state;
	}
	public void setTaskId(int id){
		this.id = id;
	}
	
	//Getters
	public String getTaskObjective(){
		return taskObjective;
	}
	public int getId(){
		return id;
	}
	public TaskState getTaskState(){
		return state;
	}
}

