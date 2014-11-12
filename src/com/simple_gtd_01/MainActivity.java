package com.simple_gtd_01;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.simple_gtd_01.R;
import com.simple_gtd_01.controller.AbstractController;
import com.simple_gtd_01.controller.SimpleGTDController;
import com.simple_gtd_01.model.AbstractModel;
import com.simple_gtd_01.model.SimpleGTDModel;
import com.simple_gtd_01.view.AbstractView;

public class MainActivity extends Activity implements AbstractView {
	
	//Activity methods

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
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
		if (id == R.id.SimpleGTD_action_settings) {
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
	public void addNewTaskToView(int identifier, String objective) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		//TODO null checks
		TextView task_done_view = (TextView)getLayoutInflater().inflate(R.layout.task_done_template, null);
		task_done_view.setText(objective);
		LinearLayout layout = (LinearLayout)this.findViewById(R.id.SimpleGTD_doneTaskList);	
		layout.addView(task_done_view);
    	System.out.println("View: Error - coudln't find doneTaskList");
	}

	@Override
	public void onNewTaskDialogExecuted(String objective) {
		
	}

	private AbstractModel m_model;
	private AbstractController m_controller;
}
