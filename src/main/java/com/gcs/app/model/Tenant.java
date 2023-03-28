package com.gcs.app.model;

import com.gcs.app.observer.TenantObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tenant 
 *
 */
public class Tenant implements TenantObserver, Serializable {
    private static final long serialVersionUID = 1L;
	private String name;
    private String phoneNumber;
    private String emailId;
    private String password;

	private ArrayList<RentableUnit> unitsInterestedIn;

    /** 
     * @param name Name of the Tenant
     * @param phoneNumber Phone number of the Tenant
     * @param emailId Email Id of the Tenant
     * @param password Password of the Unit
     */
    public Tenant(String name, String phoneNumber, String emailId, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.password = password;
        unitsInterestedIn = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

	public List<RentableUnit> getUnitsInterestedIn() {
		return (Collections.unmodifiableList(unitsInterestedIn));
	}
	
	public void addUnitInterestedIn(RentableUnit unit) {
		if(!unitsInterestedIn.contains(unit))
			unitsInterestedIn.add(unit);
	}
	
	public void removeUnitInterestedIn(RentableUnit unit) {
		unitsInterestedIn.remove(unit);
	}
    
	@Override
	public String update(RentableUnit unit) {
		System.out.println(unit.getAddress() + " is open for leasing: "+this.emailId);
        return unit.getAddress() + " is open for leasing: "+this.emailId;
	}
	
    public String getPassword() {
		return password;
	}
    
}
