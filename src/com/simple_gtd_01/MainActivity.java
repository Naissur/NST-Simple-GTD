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
import com.simple_gtd_01.view.SimpleGTDView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		m_model = new SimpleGTDModel();
		m_view = new SimpleGTDView();
		m_controller = new SimpleGTDController();
		
		m_model.setView(m_view);
		m_view.setController(m_controller);
		m_controller.setModel(m_model);
	}
	
	public void newTaskButtonClicked(View v){
		TextView textview = new TextView(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		textview.setLayoutParams(params);
		textview.setText("Hello, world!");
		LinearLayout layout = (LinearLayout)findViewById(R.id.doneTaskList);	
		layout.addView(textview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
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
	
	
	private AbstractModel m_model;
	private AbstractView m_view;
	private AbstractController m_controller;
}
