package com.simple_gtd_01.view;

public class SimpleGTDView extends AbstractView {

	public SimpleGTDView() {
		super();
		System.out.println("View: Simple GTD View initialized");
	}

	@Override
	public void addNewTaskToView(String objective) {
		System.out.println("View: Adding task \""+objective+"\" to view");
		
	}
	
	public void onNewTaskDialogExecuted(String name){
		System.out.println("View: Adding New task dialog executed");
	}

}
