package com.gcs.app.tasks;

import com.gcs.app.utility.Date;
import javafx.concurrent.Task;

public class ChangeDateTask extends Task<String> {

	String newDate;

	public ChangeDateTask(String newDate) {
		this.newDate = newDate;
	}

	@Override
	protected String call() throws Exception {

		String alertMessage = null;
		if (!newDate.isBlank()) {
			alertMessage = Date.setDate(newDate);
			if (!(alertMessage == null)) {
				return alertMessage;
			}
		}
		return "Please select a date!";

	}
}
