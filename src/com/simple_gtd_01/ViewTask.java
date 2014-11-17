package com.simple_gtd_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ViewTask {
	private int m_id;
	private String m_objective;
	private View m_view;
	
	public View getView(){
		return m_view;
	}

	public static enum State{
		UNDONE, DONE
	}
	
	public void onDoneButtonClicked(){
		System.out.println("Clicked set done button in some undone task");
	}
	
	public void onUndoneButtonClicked(){
		System.out.println("Clicked set undone button in some done task");
	}
	
	public View createTaskView(Context context,int width, int height){
		
		View res_view = null;
		switch(this.m_state){
            case UNDONE:{
                res_view = ((LayoutInflater)context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.task_undone_template, null);
                
                final RelativeLayout control_bar = (RelativeLayout)res_view.findViewById(R.id.SimpleGTD_TaskUndone_ControlBar);
                
                TextView task_text = (TextView) res_view.findViewById(R.id.SimpleGTD_TaskUndone_TextView);
                task_text.setText(m_objective);
                
                // Expand call listener
                task_text.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						ViewGroup.LayoutParams params = control_bar.getLayoutParams();
						if(params.height == LayoutParams.WRAP_CONTENT){
                            params.height = 0;
						}else
						if(params.height == 0){
                            params.height = LayoutParams.WRAP_CONTENT;
						}

						control_bar.setLayoutParams(params);
					}
                });
                
                ImageButton task_set_done_button = (ImageButton) res_view.findViewById(
                		R.id.SimpleGTD_TaskUndone_ControlBar_DoButton);
                task_set_done_button.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						onDoneButtonClicked();
					}
                });
                
                

                LayoutParams params = new LayoutParams(width,height);
                res_view.setLayoutParams(params);
                m_view = res_view;
                
                break;
            }
            case DONE:{
                res_view = ((LayoutInflater)context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.task_done_template, null);
                
                TextView task_text = (TextView) res_view.findViewById(R.id.SimpleGTD_TaskDone_TextView);
                task_text.setText(m_objective);

                ImageButton task_set_undone_button = (ImageButton) res_view.findViewById(
                		R.id.SimpleGTD_TaskDone_ControlBar_UndoButton);
                task_set_undone_button.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						onUndoneButtonClicked();
					}
                });

                final RelativeLayout control_bar = (RelativeLayout)res_view.findViewById(R.id.SimpleGTD_TaskDone_ControlBar);

                // Expand call listener
                task_text.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						ViewGroup.LayoutParams params = control_bar.getLayoutParams();
						if(params.height == LayoutParams.WRAP_CONTENT){
                            params.height = 0;
						}else
						if(params.height == 0){
                            params.height = LayoutParams.WRAP_CONTENT;
						}

						control_bar.setLayoutParams(params);
					}
                });

                LayoutParams params = new LayoutParams(width,height);
                res_view.setLayoutParams(params);
                m_view = res_view;
                
                break;
            }
		}


		return res_view;
	}

	
	State m_state;

	ViewTask(){
		m_id = 0;
		m_objective = "";
		m_state = State.UNDONE;
		m_view = null;
	}
	
	ViewTask(Context context, int width, int height, int id, String obj, State state){
		m_id = id;
		m_objective = obj;
		m_state = state;
		this.createTaskView(context, width, height);
	}
	
	public void setId(int id){
		m_id = id;
	}
	
	public int getId(){
		return m_id;
	}
	
	public State getState(){
		return m_state;
	}
	
	public void setState(State state){
		m_state = state;
	}
	
	public String getObjective(){
		return m_objective;
	}
	
	public void setObjective(String obj){
		m_objective = obj;
	}
}
