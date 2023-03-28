package com.gcs.app.factory;

import com.gcs.app.model.Property;

/**
 * Factory for creating Property.
 *
 */
public class PropertyFactory {
	// Creates Building or House
	public static Property createProperty(String type,String streetNumber,String streetName, String city,String postalCode) {
		return new Property(type, streetNumber, streetName, city, postalCode);
	}
}
