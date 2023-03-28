package com.gcs.app.model;

import java.io.Serializable;

/**
 * Rentable Unit: Condo
 *
 */
public class Condo extends RentableUnit implements Serializable {

    private static final long serialVersionUID = 1L;
	private String condoNumber;
    public Condo(String condoNumber,int numberOfBedrooms,int numberOfBathrooms,double squareFootage,String streetNumber,String streetName, String city,String postalCode){
        super(numberOfBedrooms,numberOfBathrooms,squareFootage,streetNumber, streetName, city, postalCode);
        this.condoNumber = condoNumber;
    }

    public String getAddress(){
        return (condoNumber+"-"+this.getStreetAddress());
    }

    public String getCondoNumber() {
        return condoNumber;
    }
}
