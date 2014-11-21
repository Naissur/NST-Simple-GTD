package com.simple_gtd_01.model;

import java.util.Date;


public class Task implements Comparable<Task> {
	private String taskObjective;
	private int id;
	private TaskState state;
	private int order;
	private Date addedDate;
	private Date doneDate;

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
	public void setTaskOrder(int order){
		this.order = order;
	}
	public void setTaskAddedDate(Date date){
		this.addedDate = date;
	}
	public void setTaskDoneDate(Date date){
		this.doneDate = date;
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
	public int getOrder(){
		return this.order;
	}
	public Date getTaskAddedDate(){
		return addedDate;
	}
	public Date getTaskDoneDate(){
		return doneDate;
	}
	
	//Comparator
	@Override
	public int compareTo(Task another) {
		if(this.state == another.state){
			return this.order - another.order;
		}
		else if (this.state == TaskState.UNDONE){
			return 1;
		}
		else {
			return -1;
		}
	}
}

