package com.gcs.app.tasks;

import com.gcs.app.contoller.AccountSystemController;
import com.gcs.app.session.Session;

import javafx.concurrent.Task;

public class LoginTask extends Task<Session> {

	private final String username, password;
	private final AccountSystemController accSystemController;

	public LoginTask(String username, String password) {
		this.username = username;
		this.password = password;
		this.accSystemController = new AccountSystemController();
	}

	@Override
	protected Session call() throws Exception {
		updateMessage("");
		Session session = null;
		try {
			 session = accSystemController.login(username, password);
		} catch (Exception e) {
			updateMessage(e.getMessage());
		}
		return session;
	}

}
