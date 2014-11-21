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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.simple_gtd_01.R;
import com.simple_gtd_01.ViewTask.State;
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
		
		
		//Set up tasks containers
		LinearLayout scroll_layout = (LinearLayout) this.findViewById(R.id.SimpleGTD_Scroll_Layout);

		UndoneTasksContainer undone_tasks_view = new UndoneTasksContainer(this);
		scroll_layout.addView(undone_tasks_view.getView());
		
		DoneTasksContainer done_tasks_view = new DoneTasksContainer(this);
		scroll_layout.addView(done_tasks_view.getView());

		ViewGroup.LayoutParams undone_tasks_view_params = undone_tasks_view.getView().getLayoutParams();
		undone_tasks_view_params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		undone_tasks_view.getView().setLayoutParams(undone_tasks_view_params);
		
		ViewGroup.LayoutParams done_tasks_view_params = done_tasks_view.getView().getLayoutParams();
		done_tasks_view_params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		done_tasks_view.getView().setLayoutParams(done_tasks_view_params);
		
		final MainActivity m_activity = this;

		
		Button todo_btn = (Button)this.findViewById(R.id.SimpleGTD_Todo);
		// set up todo button listener
		todo_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				final AddNewTaskDialog add_dialog = new AddNewTaskDialog(m_activity, m_activity){
					@Override
					public void onSuccess() {
						m_controller.addTaskDialogExecuted(getObjective());
					}
				};
				add_dialog.show();
			}
		});
		
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

		//transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.disableTransitionType(LayoutTransition.APPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
		transition.enableTransitionType(LayoutTransition.DISAPPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGING);


		/*transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.setDuration(LayoutTransition.APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskDone_adding_duration));
		transition.setStartDelay(LayoutTransition.APPEARING, 400);*/

		transition.setDuration(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskDone_expand_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskDone_adding_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 400);

		Animator obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_undo);
		transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
		transition.setDuration(LayoutTransition.DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
		transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

		transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_collapse_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
		
		transition.setDuration(LayoutTransition.CHANGING,
								getResources().getInteger(R.integer.SimpleGTD_TaskDone_expand_changing_duration));
		transition.setStartDelay(LayoutTransition.CHANGING, 0);
		
		
		
		m_done_tasks_container.setLayoutTransition(transition);

		transition = m_undone_tasks_container.getLayoutTransition();

		//transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.disableTransitionType(LayoutTransition.APPEARING);
		transition.enableTransitionType(LayoutTransition.DISAPPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
		transition.enableTransitionType(LayoutTransition.CHANGING);


		/*transition.enableTransitionType(LayoutTransition.APPEARING);
		transition.setDuration(LayoutTransition.APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskUndone_adding_duration));
		transition.setStartDelay(LayoutTransition.APPEARING, 400);*/

		transition.setDuration(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskUndone_expand_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 
                                getResources().getInteger(R.integer.SimpleGTD_TaskUndone_adding_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 400);

		obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_do);
		transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
		transition.setDuration(LayoutTransition.DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_move_duration));
		transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

		transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_collapse_duration));
		transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING,
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_move_duration));
		m_undone_tasks_container.setLayoutTransition(transition);
		
		
		transition.setDuration(LayoutTransition.CHANGING,
								getResources().getInteger(R.integer.SimpleGTD_TaskUndone_expand_changing_duration));
		transition.setStartDelay(LayoutTransition.CHANGING, 0);
		
		
		/*m_controller.addTaskDialogExecuted("Set things to be done");
		m_controller.addTaskDialogExecuted("Set more things to be done");
		m_controller.addTaskDialogExecuted("Be ultimate");
		m_controller.addTaskDialogExecuted("Win.");*/
		
		m_model = new SimpleGTDModel(this);
		m_model.readTasksFromJson();
		m_model.sendAllTasksToView();
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
	public void addNewTaskToView(int identifier, String objective, String date_added) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		final MainActivity _context = this;
		ViewTask task = new ViewTask(this, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                                    	identifier, objective, date_added, "", ViewTask.State.UNDONE){
			@Override
			public void onDoneButtonClicked() {
				m_controller.setTaskAsDone(this.getId());
                System.out.println("View: "+"Child pos = "+getTaskPos(this.getId()));
			};
			
			@Override
			public void onEditButtonClicked(){
				EditTaskDialog edit_dialog = new EditTaskDialog(_context, _context, this){
					@Override
					public void onSuccess() {
						m_controller.editTaskDialogExecuted(getTask().getId(), getObjective());
                    }
				};
				edit_dialog.show();
			};
		};

		View task_view = task.getView();
		
		m_undone_tasks_list.add(task);
		m_undone_tasks_container.addView(task_view, 0);
	}

	@Override
	public void addDoneTaskToView(int identifier, String objective, String date_added, String date_done) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		ViewTask task = new ViewTask(this, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                                    	identifier, objective, date_added, date_done, ViewTask.State.DONE){
            @Override
            public void onUndoneButtonClicked() {
                m_controller.setTaskAsUndone(this.getId());
                System.out.println("View: "+"Child pos = "+getTaskPos(this.getId()));
            };
            
            @Override
            public void onDiscardButtonClicked(){
            	m_controller.removeTask(this.getId());
            };
        };

		View task_view = task.getView();
		
		m_done_tasks_list.add(task);
		m_done_tasks_container.addView(task_view, 0);
	}
	
	/*public void onToDoButtonClicked(View v){
		AddNewTaskDialog dialog = new AddNewTaskDialog(this, this){
			@Override
			public void onStop(){
				m_controller.addTaskDialogExecuted(getObjective());
				super.onStop();
			};
		};
		dialog.show();
	}*/
	
	@Override
	public void removeTaskFromView(int id){
		// search in undone tasks
		for(int i = 0; i < m_undone_tasks_list.size(); i++){
			ViewTask task = m_undone_tasks_list.get(i);
			if(task.getId() == id){
				
				// Need to change disappear animation, and move task to another view
            	LayoutTransition transition = m_undone_tasks_container.getLayoutTransition();
                Animator obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_remove);
                transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
                transition.setDuration(LayoutTransition.DISAPPEARING,
                                        getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
                transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
                m_undone_tasks_container.setLayoutTransition(transition);	
				
				m_undone_tasks_container.removeView(task.getView());
				m_undone_tasks_list.remove(i);
				return;
			}
		}
		
		// search in done tasks
		for(int i = 0; i < m_done_tasks_list.size(); i++){
			ViewTask task = m_done_tasks_list.get(i);
			if(task.getId() == id){
				// Need to change disappear animation, and move task to another view
            	LayoutTransition transition = m_done_tasks_container.getLayoutTransition();
                Animator obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_remove);
                transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
                transition.setDuration(LayoutTransition.DISAPPEARING,
                                        getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
                transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
                m_done_tasks_container.setLayoutTransition(transition);	

				m_done_tasks_container.removeView(task.getView());
				m_done_tasks_list.remove(i);
				return;
			}
		}
	}

	public void setTaskAsDone(int id, String date_done) {
		// search in undone tasks
        for(int i = 0; i < m_undone_tasks_list.size(); i++){
            ViewTask task = m_undone_tasks_list.get(i);
            if(task.getId() == id){
            	task.setState(State.DONE);

            	// Need to change disappear animation, and move task to another view
            	LayoutTransition transition = m_undone_tasks_container.getLayoutTransition();
                Animator obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_do);
                transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
                transition.setDuration(LayoutTransition.DISAPPEARING,
                                        getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
                transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
                m_undone_tasks_container.setLayoutTransition(transition);
                
                ViewTask task_copy = task;
                m_undone_tasks_container.removeView(task.getView());
                m_undone_tasks_list.remove(i);
                
                this.addDoneTaskToView(id, task_copy.getObjective(), task_copy.getDateAdded(), date_done);
                return;
            }
        }
	}
	
	public void setTaskAsUndone(int id) {
		// search in undone tasks
        for(int i = 0; i < m_done_tasks_list.size(); i++){
            ViewTask task = m_done_tasks_list.get(i);
            if(task.getId() == id){
            	task.setState(State.UNDONE);

            	// Need to change disappear animation, and move task to another view
            	LayoutTransition transition = m_done_tasks_container.getLayoutTransition();
                Animator obj_anim = AnimatorInflater.loadAnimator(this, R.anim.task_undo);
                transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
                transition.setDuration(LayoutTransition.DISAPPEARING,
                                        getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
                transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
                m_done_tasks_container.setLayoutTransition(transition);
                
                ViewTask task_copy = task;

                m_done_tasks_container.removeView(task.getView());
                m_done_tasks_list.remove(i);
                this.addNewTaskToView(id, task_copy.getObjective(), task_copy.getDateAdded());
                return;
            }
        }
	}
	
	public void onDestroy(){
		m_model.saveTasksToJson();
		super.onDestroy();
	}
	
	private AbstractModel m_model;
	private AbstractController m_controller;

	@Override
	public void modifyTask(int id, String new_obj, String date_added, String date_done) {
		// search in undone tasks
        for(int i = 0; i < m_undone_tasks_list.size(); i++){
            ViewTask task = m_undone_tasks_list.get(i);
            if(task.getId() == id){
            	if(new_obj != null){
                    task.setObjective(new_obj);
            	}
            	if(date_added != null){
            		task.setDateAdded(date_added);
            	}
            	if(date_done != null){
            		task.setDateDone(date_added);
            	}
            	                  
                return;
            }
        }
        
        // search in done tasks
        for(int i = 0; i < m_done_tasks_list.size(); i++){
            ViewTask task = m_done_tasks_list.get(i);
            if(task.getId() == id){
            	if(new_obj != null){
                    task.setObjective(new_obj);
            	}
            	if(date_added != null){
            		task.setDateAdded(date_added);
            	}
            	if(date_done != null){
            		task.setDateDone(date_added);
            	}
                return;
            }
        }	
	}

	@Override
	public int getTaskPos(int id) {
		// Undone tasks
		for(int i = 0; i < m_undone_tasks_list.size(); i++){
			ViewTask task = m_undone_tasks_list.get(i);
			if(task.getId() == id){
				return m_undone_tasks_container.indexOfChild(task.getView());
			}
		}
		// Done tasks
		for(int i = 0; i < m_done_tasks_list.size(); i++){
			ViewTask task = m_done_tasks_list.get(i);
			if(task.getId() == id){
				return m_done_tasks_container.indexOfChild(task.getView());
			}
		}
		
		return 0;
	}
}
