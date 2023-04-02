package com.gcs.app.tasks;

import java.util.ArrayList;

import com.gcs.app.contoller.PropertyController;
import com.gcs.app.contoller.TenantController;
import com.gcs.app.model.Lease;
import com.gcs.app.model.Property;

import javafx.concurrent.Task;

public class ExistingTenantTask extends Task<ArrayList<Lease>>{

	String phone;
	
	public ExistingTenantTask(String phone) {
		this.phone = phone;
	}
	
	@Override
	protected ArrayList<Lease> call() throws Exception {
		ArrayList<Property> properties = PropertyController.getProperties();
        ArrayList<Lease> leases = TenantController.getLeases(properties,phone);
        if(leases != null && !leases.isEmpty())
        	return leases;
        return null;
	}

}
