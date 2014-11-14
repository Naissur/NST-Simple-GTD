package com.simple_gtd_01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

import com.simple_gtd_01.R;
import com.simple_gtd_01.controller.AbstractController;
import com.simple_gtd_01.controller.SimpleGTDController;
import com.simple_gtd_01.model.AbstractModel;
import com.simple_gtd_01.model.SimpleGTDModel;
import com.simple_gtd_01.view.AbstractView;

public class MainActivity extends Activity implements AbstractView {
	
	private ListView undone_tasks_list;
	private List<ViewTask> undone_tasks_array;
	private TaskListViewAdapter undone_tasks_adapter;

	private ListView done_tasks_list;
	private List<ViewTask> done_tasks_array;
	private TaskListViewAdapter done_tasks_adapter;
	

	//Activity methods

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
		setContentView(R.layout.activity_main);
		
		Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
		LayoutParams params;

		done_tasks_array = new ArrayList<ViewTask>();
		undone_tasks_array = new ArrayList<ViewTask>();

		undone_tasks_list = new ListView(this);
		params = new LayoutParams(size.x, LayoutParams.MATCH_PARENT);
		undone_tasks_list.setLayoutParams(params);
		LinearLayout undone_container = (LinearLayout)findViewById(R.id.SimpleGTD_UndoneTasksContainer);
		ViewTask tsk = new ViewTask(1, "Do my thing", ViewTask.State.UNDONE);
		undone_tasks_array.add(tsk);
		tsk = new ViewTask(2, "Get born in state of Mississippi", ViewTask.State.UNDONE);
		undone_tasks_array.add(tsk);
		
		
		undone_tasks_adapter = new TaskListViewAdapter(this, R.layout.task_undone_template, undone_tasks_array);
		undone_tasks_list.setAdapter(undone_tasks_adapter);
        undone_tasks_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		undone_container.addView(undone_tasks_list);

		done_tasks_list = new ListView(this);
		params = new LayoutParams(size.x, LayoutParams.MATCH_PARENT);
		done_tasks_list.setLayoutParams(params);
		LinearLayout done_container = (LinearLayout)findViewById(R.id.SimpleGTD_DoneTasksContainer);

		done_tasks_adapter = new TaskListViewAdapter(this, R.layout.task_done_template, done_tasks_array);
		done_tasks_list.setAdapter(done_tasks_adapter);
        done_tasks_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		done_container.addView(done_tasks_list);
		
		
		
		m_model = new SimpleGTDModel(this);
		m_controller = new SimpleGTDController(m_model);
		this.setController(m_controller);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.SimpleGTD_action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	// View methods
	
	public void setController(AbstractController controller) {
		m_controller = controller;
	}
	public AbstractController getController(){
		return m_controller;
	}


	@Override
	public void addNewTaskToView(int identifier, String objective) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		undone_tasks_array.add(0, new ViewTask(identifier, objective, ViewTask.State.UNDONE) );
		undone_tasks_adapter.notifyDataSetChanged();
	}

	@Override
	public void addDoneTaskToView(int identifier, String objective) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		done_tasks_array.add(0, new ViewTask(identifier, objective, ViewTask.State.DONE) );
		done_tasks_adapter.notifyDataSetChanged();
	}
	
	public void onToDoButtonClicked(View v){
		AddNewTaskDialog dialog = new AddNewTaskDialog(this, this);
		dialog.show();
	}
	
	@Override
	public void removeTaskFromView(int id){
		for(int i = 0; i < undone_tasks_array.size(); i++){
			if(undone_tasks_array.get(i).getId() == id){
				undone_tasks_array.remove(i);
				undone_tasks_adapter.notifyDataSetChanged();
				return;
			}
		}
	}

	public void setTaskAsDone(int id) {
		m_controller.setTaskAsDone(id);
	}
	
	private AbstractModel m_model;
	private AbstractController m_controller;

}
