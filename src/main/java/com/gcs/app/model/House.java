package com.gcs.app.model;

import java.io.Serializable;

/**
 * Rentabble Unit: House
 *
 */
public class House extends RentableUnit implements Serializable {

    private static final long serialVersionUID = 1L;

	public House(int numberOfBedrooms,int numberOfBathrooms,double squareFootage,String streetNumber,String streetName, String city,String postalCode){
        super(numberOfBedrooms,numberOfBathrooms,squareFootage,streetNumber, streetName, city, postalCode);
    }

    public String getAddress(){
        return (this.getStreetAddress());
    }

}
