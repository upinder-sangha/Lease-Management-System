package com.gcs.app.view;


import com.gcs.app.tasks.AddTenantTask;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TenantViewController {
	
	@FXML
	TextField tenantName;
	@FXML
	TextField tenantPhone;
	@FXML
	TextField tenantEmail;
	@FXML
	TextField tenantPassword;
	@FXML
	Label message;
	
	public void initialize() {
		
	}
	
	public void addTenant() {
		String name = tenantName.getText();
		String phone = tenantPhone.getText();
		String email = tenantEmail.getText();
		String password = tenantPassword.getText();
		
		AddTenantTask addTenantTask = new AddTenantTask(name, phone, email, password);
		addTenantTask.valueProperty().addListener((observable, oldValue, newValue) -> {
				message.setText(newValue);
			});
	}
}
