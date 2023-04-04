package com.gcs.app.model;

import com.gcs.app.observer.RentalUnitObservable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Rentable Unit
 *
 */
public abstract class RentableUnit implements RentalUnitObservable, Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Tenant> interestedTenants;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private double squareFootage;
    private String streetNumber;
    private String streetName;
    private String city;
    private String postalCode;

    private Lease lease;

    /**
     * @param numberOfBedrooms Number of Bedrooms in the Unit
     * @param numberOfBathrooms Number of Bathrooms in the Unit
     * @param squareFootage Square Footage of the Unit
     * @param streetNumber Street Number of the Unit
     * @param streetName Street Name of the Unit
     * @param city City of the Unit
     * @param postalCode Postal Code of the Unit
     */
    public RentableUnit(int numberOfBedrooms,int numberOfBathrooms,double squareFootage,String streetNumber,String streetName, String city,String postalCode){
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squareFootage = squareFootage;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;
        interestedTenants = new ArrayList<Tenant>();
    }
    
	public void addObserverTenant(Tenant t) {
		if(!interestedTenants.contains(t))
			interestedTenants.add(t);
	}
	public void removeObserverTenant(Tenant t) {
		interestedTenants.remove(t);
	}
	public String notifyInterestedTenants() {
		StringBuilder messageToDisplay = new StringBuilder();
		for(Tenant tenant: interestedTenants) {
			String str = tenant.update(this);
			messageToDisplay.append(str).append("\n");
		}
		return messageToDisplay.toString();
	}

    public void rentUnit(Tenant tenant, double rent, String startsOn, String endsOn){
        lease = new Lease(tenant,getAddress(),rent,startsOn,endsOn);
    }
    public abstract String getAddress();

    protected String getStreetAddress(){
        return (streetNumber+", "+streetName+", "+city+", "+postalCode);
    }

    public Lease getLease(){
        return lease;
    }

	public String endLease() {
		this.lease = null;
        return this.notifyInterestedTenants();
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public double getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(double squareFootage) {
		this.squareFootage = squareFootage;
	}

}
