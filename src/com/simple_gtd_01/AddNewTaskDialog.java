package com.simple_gtd_01;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddNewTaskDialog extends Dialog implements OnClickListener {

	public AddNewTaskDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.add_new_task_dialog);
	    this.setTitle(R.string.SimpleGTD_add_task_dialog_title);

	    Button yes = (Button) findViewById(R.id.btn_yes);
	    yes.setOnClickListener(this);
	    /*yes = (Button) findViewById(R.id.btn_yes);
	    no = (Button) findViewById(R.id.btn_no);
	    yes.setOnClickListener(this);
	    no.setOnClickListener(this);*/
	  }

	@Override
    public void onClick(View v) {
      	switch (v.getId()) {
      	case R.id.btn_yes:
      		System.out.print("Sddinfasdfsef\n");
        	break;
      	}
	}

}
