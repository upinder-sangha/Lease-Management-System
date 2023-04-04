package com.gcs.app.contoller;

import com.gcs.app.session.Session;

public class AccountSystemController {

	private static final String PASSWORD = "password";
	private static final String ADMIN = "admin";
	private Session session;

	/**
	 * @return Session of the logged in user
	 */
	public Session login(String username, String password) throws Exception{
		if (ADMIN.equalsIgnoreCase(username)) {
			if (PASSWORD.equalsIgnoreCase(password)) {
				session = Session.getInstance();
				session.setUserName(username);
			} else
				throw new Exception("Wrong Password");
		} else if (TenantController.getTenant(username) != null) {
			if (TenantController.getTenant(username).getPassword().equalsIgnoreCase(password)) {
				session = Session.getInstance();
				session.setUserName(username);
			} else
				throw new Exception("Wrong Password");
		} else
			throw new Exception("Sorry this username does not exist. Please contact admin.");
		return session;
	}
}
