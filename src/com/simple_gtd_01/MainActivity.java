package com.simple_gtd_01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.simple_gtd_01.R;
import com.simple_gtd_01.controller.AbstractController;
import com.simple_gtd_01.controller.SimpleGTDController;
import com.simple_gtd_01.model.AbstractModel;
import com.simple_gtd_01.model.SimpleGTDModel;
import com.simple_gtd_01.view.AbstractView;

public class MainActivity extends AbstractView {
	
	//Activity methods

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	// View/controller methods

	public void newTaskButtonClicked(View v){
		m_controller.addTaskDialogExecuted("Get things done");
	}
	
	
	// View methods
	
	public void setController(AbstractController controller) {
		m_controller = controller;
	}

	@Override
	public void addNewTaskToView(int id, String objective) {
		System.out.println("View: Adding task \""+objective+"\" with id = "+id+" to view");
		TextView textview = new TextView(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);

		textview.setLayoutParams(params);
		textview.setText(objective);
		LinearLayout layout = (LinearLayout)this.findViewById(R.id.doneTaskList);	
		if(layout != null){
            layout.addView(textview);
		}else{
			System.out.println("View: Error - coudln't find doneTaskList");
		}
	}

	@Override
	public void onNewTaskDialogExecuted(String objective) {
		
	}

	private AbstractModel m_model;
	private AbstractController m_controller;
	@Override
	public void removeTaskFromView(int id) {
		//TODO
		
	}


	@Override
	public void addDoneTaskToView(int identifier, String objective) {
		// TODO Auto-generated method stub
		
	}
}
