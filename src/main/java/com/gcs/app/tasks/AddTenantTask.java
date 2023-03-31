package com.gcs.app.tasks;

import com.gcs.app.contoller.TenantController;

import javafx.concurrent.Task;

public class AddTenantTask extends Task<String> {
	String tenantName, tenantPhone, tenantEmail, tenantPassword;
	
	public AddTenantTask(String name, String phone, String email, String password) {
		this.tenantName = name;
		this.tenantPhone = phone;
		this.tenantEmail = email;
		this.tenantPassword = password;
	}
	
	@Override
	protected String call() throws Exception {
		if(!tenantName.isBlank() && !tenantPhone.isBlank() && !tenantEmail.isBlank() && !tenantPassword.isBlank())
			TenantController.addTenant(tenantName, tenantPhone, tenantEmail, tenantPassword);
		else
			return "Please fill all details!";
		
		return "Tenant added successfully";
	}

}
