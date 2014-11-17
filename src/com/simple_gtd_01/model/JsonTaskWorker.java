package com.simple_gtd_01.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
			System.out.println("Exception in creating json object from task");
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return json;
	}

	public void writeToJson(FileOutputStream fos, TreeMap<Integer,Task> tasks) throws IOException{
		JSONArray array = new JSONArray();
		for (Map.Entry<Integer, Task> pair : tasks.entrySet()){
			array.put(createJsonObjectFromTask(pair.getValue()));
		}
		try {
			fos.write(array.toString().getBytes());
		} catch (IOException e) {
			System.out.println("Exception in writing to file");
			System.out.println(e.toString());
			e.printStackTrace();
		}finally{
			fos.close();
		}
		fos.close();
	}
	
	private Task createTaskFromJsonObject(JSONObject json){
		Task task = new Task();
		try {
			task.setTaskId(json.getInt("id"));
			task.setTaskObjective(json.getString("objective"));
			task.setTaskState(TaskState.values()[json.getInt("state")]);
		} catch (JSONException e) {
			System.out.println(e.toString());
			System.out.println("Exception in creating task from json object");
			e.printStackTrace();
		}
		return task;
	}
	
	public TreeMap<Integer, Task> readFromJson(FileInputStream fis){
		TreeMap<Integer, Task> tasks = new TreeMap<Integer, Task>();
		String json = null;
		//Reading from file
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		try {
			json = reader.readLine();
		} catch (IOException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}
		//Parse json
		try {
			JSONArray jsonArray = new JSONArray(json);
			for(int i = 0; !jsonArray.isNull(i); i++){
				JSONObject jsonTask = jsonArray.getJSONObject(i);
				Task task = createTaskFromJsonObject(jsonTask);
				tasks.put(task.getId(), task);
				fis.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return tasks;
	}
}
