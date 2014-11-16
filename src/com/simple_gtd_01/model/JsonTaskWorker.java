package com.simple_gtd_01.model;

import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTaskWorker {
	
	
	private JSONObject createJsonObjectFromTask(Task task){
		JSONObject json = new JSONObject();
		try {
			json.put("id", task.getId());
			json.put("objective", task.getTaskObjective());
			json.put("state", task.getTaskState().ordinal());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public void writeToJson(TreeMap<Integer,Task> tasks){
		JSONArray array = new JSONArray();
		for (Map.Entry<Integer, Task> pair : tasks.entrySet()){
			array.put(createJsonObjectFromTask(pair.getValue()));
		}
		//TODO put this json to file
	}
	
	private Task createTaskFromJsonObject(JSONObject json){
		Task task = new Task();
		try {
			task.setTaskId(json.getInt("id"));
			task.setTaskObjective(json.getString("objective"));
			task.setTaskState(TaskState.values()[json.getInt("state")]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public TreeMap<Integer, Task> readFromJson(){
		TreeMap<Integer, Task> tasks = new TreeMap<Integer, Task>();
		//TODO get from file and parse
		return tasks;
	}
}
