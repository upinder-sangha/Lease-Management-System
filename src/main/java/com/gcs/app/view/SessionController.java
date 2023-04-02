package com.gcs.app.view;

import java.io.IOException;

import com.gcs.app.session.Session;
import com.gcs.app.tasks.LoginTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SessionController {	
	private static final String ADMIN = "admin";
	
	@FXML
	TextField usernameField;

	@FXML
	PasswordField passwordField;

	@FXML
	Button loginButton;

	@FXML
	Label errorLabel;
		
	@FXML
	public void onLoginBtnClick() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		errorLabel.setText("");

		if (username.isBlank() || password.isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Err");
			alert.setHeaderText("Login Error");
			alert.setContentText("Username/Password is blank!");
			alert.show();
		} else {
			LoginTask loginTask = new LoginTask(username, password);
			loginTask.messageProperty().addListener((observable, oldValue, newValue) -> {
					errorLabel.setText(newValue);
			});
			loginTask.valueProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue!=null) {
					// get a handle to the stage
				    Stage stage = (Stage) loginButton.getScene().getWindow();
				    FXMLLoader fxmlLoader;
				    if(newValue.getUserName().equalsIgnoreCase(ADMIN))
				    	fxmlLoader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
				    else
				    	fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
				    try {
				    	Scene scene = new Scene(fxmlLoader.load(), 700, 400);
				    	stage.setScene(scene);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			});
			Thread loginThread = new Thread(loginTask);
			loginThread.setDaemon(true);
			loginThread.start();
		}
	}
	
	public static void logoutSession() {
		Session.closeSession();
	}

}
