package com.gcs.app.contoller;

import com.gcs.app.factory.PropertyFactory;
import com.gcs.app.factory.RentableUnitFactory;
import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;

import java.util.ArrayList;

public class PropertyController {
	
	private static ArrayList<Property> properties = new ArrayList<>();

	 /**
     * @return ArrayList
     */
    public static ArrayList<Property> getProperties(){
    	return properties;
    }

    /**
     * @param properties List of properties
     */
    public static void setProperties(ArrayList<Property> properties) {
        PropertyController.properties = properties;
    }

    public static void addProperty(String choiceOfProperty, ArrayList<String> propertyDetails){
        Property property;
        switch (choiceOfProperty) {
            case "Building":
                property = PropertyFactory.createProperty("multistoryBuilding", propertyDetails.get(0), propertyDetails.get(1), propertyDetails.get(2), propertyDetails.get(3));
                properties.add(property);
                break;
            case "House":
                Property houseProperty = PropertyFactory.createProperty("House", propertyDetails.get(0), propertyDetails.get(1), propertyDetails.get(2), propertyDetails.get(3));
                RentableUnit house = createUnit(propertyDetails,"House");
                houseProperty.addUnit(house);
                properties.add(houseProperty);
                break;
            case "Apartment":
                property = getPropertyFromAddress(propertyDetails.get(8));
                RentableUnit apartment = createUnit(propertyDetails,"apartment");
                if (property != null) {
                    property.addUnit(apartment);
                }
                break;
            case "Condo":
                property = getPropertyFromAddress(propertyDetails.get(8));
                RentableUnit condo = createUnit(propertyDetails,"condo");
                if (property != null) {
                    property.addUnit(condo);
                }
                break;
            default:
                System.out.println("Wrong choice!");
        }
    }

    public static Property getPropertyFromAddress(String address){
        for (Property property: properties) {
            if(property.getCivicAddress().equalsIgnoreCase(address))
                return property;
        }
        return null;
    }

    public static RentableUnit createUnit(ArrayList<String> propertyDetails, String type){
        String unitNumber = propertyDetails.get(4);
        int numOfBedrooms = Integer.parseInt(propertyDetails.get(5));
        int numOfBathrooms = Integer.parseInt(propertyDetails.get(6));
        double squareFootage = Double.parseDouble(propertyDetails.get(7));
        RentableUnit unit = RentableUnitFactory.createRentableUnit(type, unitNumber, numOfBedrooms,numOfBathrooms,squareFootage, propertyDetails.get(0), propertyDetails.get(1), propertyDetails.get(2), propertyDetails.get(3));
        return unit;
    }

}
