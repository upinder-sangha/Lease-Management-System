package com.gcs.app.tasks;

import com.gcs.app.contoller.MainController;

import javafx.concurrent.Task;

public class SaveDataTask extends Task<String> {
	private final MainController mainController;

	public SaveDataTask() {
		mainController = new MainController();
	}

	@Override
	protected String call() throws Exception {
		updateMessage(mainController.saveAndExit());
		return "";
	}
}
