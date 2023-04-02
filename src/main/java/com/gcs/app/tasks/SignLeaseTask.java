package com.gcs.app.tasks;

import com.gcs.app.model.RentableUnit;
import com.gcs.app.model.Tenant;

import javafx.concurrent.Task;

public class SignLeaseTask extends Task<String>{
	
	private Tenant tenant;
	private RentableUnit unit;
	private Double rent;
	private String startsOn;
	private String endsOn;
	
	public SignLeaseTask(Tenant tenant, RentableUnit unit, Double rent, String startsOn, String endsOn){
		this.tenant = tenant;
		this.unit = unit;
		this.rent = rent;
		this.startsOn = startsOn;
		this.endsOn = endsOn;
	}
	

	@Override
	protected String call() throws Exception {
		if(unit.getLease()!=null) {
			updateMessage("Sorry the unit is already rented");
		}
		else {
			unit.rentUnit(tenant, rent, startsOn, endsOn);
			tenant.removeUnitInterestedIn(unit);
			unit.removeObserverTenant(tenant);
			updateMessage("Congratulations! The lease has been signed");
		}
		return "";
	}

}
