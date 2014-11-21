package com.simple_gtd_01;

import java.util.ArrayList;
import java.util.List;

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
	/*private LinearLayout m_done_tasks_view;
	private LinearLayout m_undone_tasks_view;*/
	private DoneTasksContainer m_done_tasks_container;
	private UndoneTasksContainer m_undone_tasks_container;
	

	//Activity methods

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
		setContentView(R.layout.activity_main);
		
		
		//Set up tasks containers
		LinearLayout scroll_layout = (LinearLayout) this.findViewById(R.id.SimpleGTD_Scroll_Layout);

		m_undone_tasks_container = new UndoneTasksContainer(this);
		scroll_layout.addView(m_undone_tasks_container.getView());
		/*m_undone_tasks_view = undone_tasks_view.getView();
		m_undone_tasks_container = undone_tasks_view.getContainerView();*/
		
		
		m_done_tasks_container = new DoneTasksContainer(this);
		scroll_layout.addView(m_done_tasks_container.getView());
		/*m_done_tasks_view = done_tasks_view.getView();
		m_done_tasks_container = done_tasks_view.getContainerView();*/

		ViewGroup.LayoutParams undone_tasks_view_params = m_undone_tasks_container.getView().getLayoutParams();
		undone_tasks_view_params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		m_undone_tasks_container.getView().setLayoutParams(undone_tasks_view_params);
		
		ViewGroup.LayoutParams done_tasks_view_params = m_done_tasks_container.getView().getLayoutParams();
		done_tasks_view_params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		m_done_tasks_container.getView().setLayoutParams(done_tasks_view_params);

		
		final MainActivity m_activity = this;
		
		Button todo_btn = m_undone_tasks_container.getTodoButton();

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
		
		//m_done_tasks_view = (LinearLayout)findViewById(R.id.SimpleGTD_DoneTasksContainer);
		params = m_done_tasks_container.getView().getLayoutParams();
		params.width = screenWidth;
		m_done_tasks_container.getView().setLayoutParams(params);
		
		//m_undone_tasks_view = (LinearLayout)findViewById(R.id.SimpleGTD_UndoneTasksContainer);
		params = m_undone_tasks_container.getView().getLayoutParams();
		params.width = screenWidth;
		m_undone_tasks_container.getView().setLayoutParams(params);

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
		m_undone_tasks_container.getContainerView().addView(task_view, 0);
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
		m_done_tasks_container.getContainerView().addView(task_view, 0);
	}
	
	@Override
	public void removeTaskFromView(int id){
		// search in undone tasks
		for(int i = 0; i < m_undone_tasks_list.size(); i++){
			ViewTask task = m_undone_tasks_list.get(i);
			if(task.getId() == id){
				m_undone_tasks_container.setRemoveAnimation();
				m_undone_tasks_container.getContainerView().removeView(task.getView());
				m_undone_tasks_list.remove(i);
				return;
			}
		}
		
		// search in done tasks
		for(int i = 0; i < m_done_tasks_list.size(); i++){
			ViewTask task = m_done_tasks_list.get(i);
			if(task.getId() == id){
				m_done_tasks_container.setRemoveAnimation();
				m_done_tasks_container.getContainerView().removeView(task.getView());
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
            	m_undone_tasks_container.setMoveAnimation();
                ViewTask task_copy = task;
                m_undone_tasks_container.getContainerView().removeView(task.getView());
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
                ViewTask task_copy = task;
                m_done_tasks_container.getContainerView().removeView(task.getView());
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
				return m_undone_tasks_container.getContainerView().indexOfChild(task.getView());
			}
		}
		// Done tasks
		for(int i = 0; i < m_done_tasks_list.size(); i++){
			ViewTask task = m_done_tasks_list.get(i);
			if(task.getId() == id){
				return m_done_tasks_container.getContainerView().indexOfChild(task.getView());
			}
		}
		
		return 0;
	}
}
