package com.simple_gtd_01;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class TaskListViewAdapter extends ArrayAdapter<ViewTask>{
	private MainActivity m_context;
    private List<ViewTask> m_tasks;

	public TaskListViewAdapter(MainActivity context, int resource, List<ViewTask> objects) {
		super(context, resource, objects);
		m_context = context;
		m_tasks = objects;
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        final ViewTask pos_task = m_tasks.get(position);
        View rowView = null;
        if(pos_task.getState() == ViewTask.State.UNDONE){
            rowView = inflater.inflate(R.layout.task_undone_template, null, false);
            TextView tv = (TextView) rowView.findViewById(R.id.SimpleGTD_TaskUndoneTemplate_TextView);
            ImageButton do_task_btn = (ImageButton) rowView.findViewById(R.id.SimpleGTD_TaskUndoneTemplate_DoButton);
            do_task_btn.setOnClickListener(
                new OnClickListener(){
					@Override
					public void onClick(View v) {
						m_context.setTaskAsDone(pos_task.getId());
					}
                });
            tv.setText(pos_task.getObjective());
        }else
        if(pos_task.getState() == ViewTask.State.DONE){
            rowView = inflater.inflate(R.layout.task_done_template, null, false);
            TextView tv = (TextView) rowView.findViewById(R.id.SimpleGTD_TaskDoneTemplate_TextView);
            tv.setText(pos_task.getObjective());
        }
        

        return rowView;
    }

}
