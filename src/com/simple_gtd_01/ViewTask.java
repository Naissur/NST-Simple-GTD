package com.simple_gtd_01;

public class ViewTask {
	private int m_id;
	private String m_objective;

	public static enum State{
		UNDONE, DONE
	}
	
	State m_state;

	ViewTask(){
		m_id = 0;
		m_objective = "";
		m_state = State.UNDONE;
	}
	
	ViewTask(int id, String obj, State state){
		m_id = id;
		m_objective = obj;
		m_state = state;
	}
	
	public void setId(int id){
		m_id = id;
	}
	
	public int getId(){
		return m_id;
	}
	
	public State getState(){
		return m_state;
	}
	
	public void setState(State state){
		m_state = state;
	}
	
	public String getObjective(){
		return m_objective;
	}
	
	public void setObjective(String obj){
		m_objective = obj;
	}
}
