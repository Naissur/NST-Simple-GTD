package com.simple_gtd_01;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

public class DoneTasksContainer{
	private LinearLayout m_view;
	private LinearLayout m_container;
	private Activity m_context;
	
	public LinearLayout getView(){
		return m_view;
	}
	
	public LinearLayout getContainerView(){
		return m_container;
	}
	
	public void setMoveAnimation(){
		// Need to change disappear animation, and move task to another view
    	LayoutTransition transition = m_container.getLayoutTransition();
        Animator obj_anim = AnimatorInflater.loadAnimator(m_context, R.anim.task_undo);
        transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
        transition.setDuration(LayoutTransition.DISAPPEARING,
                                m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
        transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
        m_container.setLayoutTransition(transition);
	}

	public void setRemoveAnimation(){
    	LayoutTransition transition = m_container.getLayoutTransition();
        Animator obj_anim = AnimatorInflater.loadAnimator(m_context, R.anim.task_remove);
        transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
        transition.setDuration(LayoutTransition.DISAPPEARING,
                                m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
        transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
        m_container.setLayoutTransition(transition);	
	}
	
	public DoneTasksContainer(Activity context) {
		m_view = (LinearLayout)context.getLayoutInflater().inflate(R.layout.done_tasks_view, null);
		m_container = (LinearLayout)m_view.findViewById(R.id.SimpleGTD_DoneTasksContainer);
		m_context = context;
		
		
		// Set up layout transitions
				LayoutTransition transition = m_container.getLayoutTransition();
				if(transition == null){
					transition = new LayoutTransition();
				}

				//transition.enableTransitionType(LayoutTransition.APPEARING);
				transition.disableTransitionType(LayoutTransition.APPEARING);
				transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
				transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
				transition.enableTransitionType(LayoutTransition.DISAPPEARING);
				transition.enableTransitionType(LayoutTransition.CHANGING);


				/*transition.enableTransitionType(LayoutTransition.APPEARING);
				transition.setDuration(LayoutTransition.APPEARING, 
		                                m_context.m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_adding_duration));
				transition.setStartDelay(LayoutTransition.APPEARING, 400);*/

				transition.setDuration(LayoutTransition.CHANGE_APPEARING, 
		                                m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_expand_duration));
				transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 
		                                m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_adding_duration));
				transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 400);

				Animator obj_anim = AnimatorInflater.loadAnimator(m_context, R.anim.task_undo);
				transition.setAnimator(LayoutTransition.DISAPPEARING, obj_anim);
				transition.setDuration(LayoutTransition.DISAPPEARING,
										m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
				transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

				transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 
										m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_collapse_duration));
				transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING,
										m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_move_duration));
				
				transition.setDuration(LayoutTransition.CHANGING,
										m_context.getResources().getInteger(R.integer.SimpleGTD_TaskDone_expand_changing_duration));
				transition.setStartDelay(LayoutTransition.CHANGING, 0);
				
				
				
				m_container.setLayoutTransition(transition);
	}

}
