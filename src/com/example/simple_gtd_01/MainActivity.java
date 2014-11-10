package com.example.simple_gtd_01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
		m_view.addNewTaskToView("Write simple gtd!");
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
