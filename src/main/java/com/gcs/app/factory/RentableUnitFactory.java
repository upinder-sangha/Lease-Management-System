package com.gcs.app.factory;

import com.gcs.app.model.Apartment;
import com.gcs.app.model.Condo;
import com.gcs.app.model.House;
import com.gcs.app.model.RentableUnit;

/**
 * Factory for creating Rentable Units.
 *
 */
public class RentableUnitFactory {

	// Creates House, Condo, Apartment
	public static RentableUnit createRentableUnit(String type, String unitNumber , int numberOfBedrooms,int numberOfBathrooms,double squareFootage,String streetNumber,String streetName, String city,String postalCode) {
		
		if("House".equalsIgnoreCase(type)) {
			return new House(numberOfBedrooms, numberOfBathrooms, squareFootage, streetNumber, streetName, city, postalCode);
		}
		if("Condo".equalsIgnoreCase(type)) {
			return new Condo(unitNumber, numberOfBedrooms, numberOfBathrooms, squareFootage, streetNumber, streetName, city, postalCode);
		}
		if("Apartment".equalsIgnoreCase(type)) {
			return new Apartment(unitNumber, numberOfBedrooms, numberOfBathrooms, squareFootage, streetNumber, streetName, city, postalCode);
		}
		return null;
	}
	
}
