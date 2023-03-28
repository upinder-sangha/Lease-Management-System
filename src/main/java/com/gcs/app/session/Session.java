package com.gcs.app.session;

/**
 * A utility class to persist user information.
 *
 */
public class Session {

	private String userName;
	
	private static final String ADMIN = "admin";

	private static Session instance = null;
	
    private Session() {
    }

    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }
    
    public String getUserName() {
    	return this.userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public static boolean isSessionValid() {
    	return instance!=null;
    }
    
    public static void closeSession(){
        instance = null;
    }

	
    public boolean isAdmin() {
    	return ADMIN.equalsIgnoreCase(this.userName);
    }
}
