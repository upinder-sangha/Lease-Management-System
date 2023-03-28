package com.gcs.app.tasks;


import com.gcs.app.contoller.MainController;

import javafx.concurrent.Task;

public class LoadDataTask extends Task<String> {
	private final MainController mainController;

	public LoadDataTask() {
		mainController = new MainController();
	}

	@Override
	protected String call() throws Exception {
		updateMessage(mainController.loadData());
		return "";
	}
}
