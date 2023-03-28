package com.gcs.app.model;

import java.io.Serializable;

/**
 * Rentable Unit: Apartment
 *
 */
public class Apartment  extends RentableUnit implements Serializable {
    private static final long serialVersionUID = 1L;
	private String apartmentNumber;

    public Apartment(String apartmentNumber,int numberOfBedrooms,int numberOfBathrooms,double squareFootage,String streetNumber,String streetName, String city,String postalCode){
        super(numberOfBedrooms,numberOfBathrooms,squareFootage,streetNumber, streetName, city, postalCode);
        this.apartmentNumber = apartmentNumber;
    }

    public String getAddress(){
        return (apartmentNumber+"-"+this.getStreetAddress());
    }

    public String getApartmentNumber(){
        return apartmentNumber;
    }
}
