package com.example.simple_gtd_01;

public class SimpleGTDController extends AbstractController {

	public SimpleGTDController() {
		super();
		System.out.println("Controller: Simple GTD Controller initialized");
	}

	@Override
	public void addTaskDialogExecuted(String task_objective) {
		System.out.println("Controller: Adding new task dialog executed, passing to model");
	}

}
