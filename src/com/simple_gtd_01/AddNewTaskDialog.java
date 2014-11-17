package com.simple_gtd_01;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

public class AddNewTaskDialog extends Dialog implements OnClickListener {

	private MainActivity m_act;
	
	public AddNewTaskDialog(Context context, MainActivity m_activity) {
		super(context, R.style.SimpleGTD_DialogTheme);
		m_act = m_activity;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.add_new_task_dialog);
		View pos_btn = this.findViewById(R.id.SimpleGTD_AddTaskDialog_positive_button);
		View neg_btn = this.findViewById(R.id.SimpleGTD_AddTaskDialog_negative_button);
        EditText ed_text = (EditText)findViewById(R.id.SimpleGTD_AddTaskDialog_edit_text);
        ed_text.requestFocus();
        
        // Show keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		final Dialog dialog = (Dialog)this;
		final MainActivity main_activity = m_act;

		pos_btn.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				EditText ed_text = (EditText)dialog.findViewById(R.id.SimpleGTD_AddTaskDialog_edit_text);
				main_activity.getController().addTaskDialogExecuted(ed_text.getText().toString());
				dialog.dismiss();
			}
		});
		neg_btn.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				dialog.cancel();
			}
		});
	    this.setTitle(R.string.SimpleGTD_AddTaskDialog_title);
	    
	    
	}

	@Override
    public void onClick(View v) {
		System.out.println("Clicked!");
      	switch (v.getId()) {
      		case R.id.SimpleGTD_AddTaskDialog_negative_button:
      			this.cancel();
      		}
	}

	@Override
	public void onStop(){
		// Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		super.onStop();
	}
}
