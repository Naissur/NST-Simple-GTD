package com.simple_gtd_01;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.simple_gtd_01.R;
import com.simple_gtd_01.controller.AbstractController;
import com.simple_gtd_01.controller.SimpleGTDController;
import com.simple_gtd_01.model.AbstractModel;
import com.simple_gtd_01.model.SimpleGTDModel;
import com.simple_gtd_01.view.AbstractView;

public class MainActivity extends AbstractView {
	
	private List<ViewTask> m_done_tasks_list;
	private List<ViewTask> m_undone_tasks_list;
	private LinearLayout m_done_tasks_container;
	private LinearLayout m_undone_tasks_container;
	

	//Activity methods

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
		setContentView(R.layout.activity_main);
		
		Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
		ViewGroup.LayoutParams params;
		
		m_undone_tasks_list = new ArrayList<ViewTask>();
		m_done_tasks_list = new ArrayList<ViewTask>();
		
		m_done_tasks_container = (LinearLayout)findViewById(R.id.SimpleGTD_DoneTasksContainer);
		params = m_done_tasks_container.getLayoutParams();
		params.width = screenWidth;
		m_done_tasks_container.setLayoutParams(params);
		
		m_undone_tasks_container = (LinearLayout)findViewById(R.id.SimpleGTD_UndoneTasksContainer);
		params = m_undone_tasks_container.getLayoutParams();
		params.width = screenWidth;
		m_undone_tasks_container.setLayoutParams(params);

		// Set up layout transitions
		LayoutTransition transition = m_done_tasks_container.getLayoutTransition();

		transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
		transition.enableTransitionType(LayoutTransition.DISAPPEARING);
		

		transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);

		transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.setDuration(LayoutTransition.APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskDone_adding_duration));
		transition.setStartDelay(LayoutTransition.APPEARING, 400);

		transition.setDuration(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskDone_expand_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskDone_adding_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 400);

		transition.setDuration(LayoutTransition.DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_removal_duration));
		transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

		transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_collapse_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_removal_duration));
		m_done_tasks_container.setLayoutTransition(transition);

		transition = m_undone_tasks_container.getLayoutTransition();

		transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
		transition.enableTransitionType(LayoutTransition.DISAPPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);

		Animator obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_removal);
		transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);

		transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.setDuration(LayoutTransition.APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskUndone_adding_duration));
		transition.setStartDelay(LayoutTransition.APPEARING, 400);

		transition.setDuration(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskUndone_expand_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskUndone_adding_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 400);

		transition.setDuration(LayoutTransition.DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_removal_duration));
		transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

		transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_collapse_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_removal_duration));
		m_undone_tasks_container.setLayoutTransition(transition);
		
		
		
		
		/*m_controller.addTaskDialogExecuted("Set things to be done");
		m_controller.addTaskDialogExecuted("Set more things to be done");
		m_controller.addTaskDialogExecuted("Be ultimate");
		m_controller.addTaskDialogExecuted("Win.");*/
		
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

		ViewTask task = new ViewTask(this, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                                    	identifier, objective, ViewTask.State.UNDONE){
			@Override
			public void onDoneButtonClicked() {
				setTaskAsDone(this.getId());
			};
		};

		View task_view = task.getView();
		
		m_undone_tasks_list.add(task);
		m_undone_tasks_container.addView(task_view, 0);
	}

	@Override
	public void addDoneTaskToView(int identifier, String objective) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		ViewTask task = new ViewTask(this, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                                    	identifier, objective, ViewTask.State.DONE);

		View task_view = task.getView();
		
		m_done_tasks_list.add(task);
		m_done_tasks_container.addView(task_view, 0);
	}
	
	public void onToDoButtonClicked(View v){
		AddNewTaskDialog dialog = new AddNewTaskDialog(this, this);
		dialog.show();
	}
	
	@Override
	public void removeTaskFromView(int id){
		for(int i = 0; i < m_undone_tasks_list.size(); i++){
			ViewTask task = m_undone_tasks_list.get(i);
			if(task.getId() == id){
				task.getView().setHasTransientState(true);
				m_undone_tasks_container.removeView(task.getView());
				m_undone_tasks_list.remove(i);
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
