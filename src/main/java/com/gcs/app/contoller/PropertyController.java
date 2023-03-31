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
        switch (choiceOfProperty) {
            case "Building":
                Property property = PropertyFactory.createProperty("multistoryBuilding", propertyDetails.get(0), propertyDetails.get(1), propertyDetails.get(2), propertyDetails.get(3));
                properties.add(property);
                break;
            case "House":
                Property houseProperty = PropertyFactory.createProperty("House", propertyDetails.get(0), propertyDetails.get(1), propertyDetails.get(2), propertyDetails.get(3));
                RentableUnit house = createUnit(propertyDetails,"House");
                houseProperty.addUnit(house);
                properties.add(houseProperty);
                break;
//            case "Apartment":
//                int choice = gui.chooseBuilding(properties);
//                property = properties.get(choice);
//                propertyDetails = new String[]{property.getStreetNumber(), property.getStreetName(), property.getCity(), property.getPostalCode()};
//                RentableUnit apartment = createUnit(propertyDetails, "Apartment");
//                property.addUnit(apartment);
//                break;
//            case "Condo":
//                int choice_ = gui.chooseBuilding(properties);
//                property = properties.get(choice_);
//                propertyDetails = new String[]{property.getStreetNumber(), property.getStreetName(), property.getCity(), property.getPostalCode()};
//                RentableUnit condo = createUnit(propertyDetails, "condo");
//                property.addUnit(condo);
//                break;
            default:
                System.out.println("Wrong choice!");
        }
    }

    public static RentableUnit createUnit(ArrayList<String> propertyDetails, String type){
        String unitNumber = propertyDetails.get(4);
        int numOfBedrooms = Integer.parseInt(propertyDetails.get(5));
        int numOfBathrooms = Integer.parseInt(propertyDetails.get(6));
        double squareFootage = Double.parseDouble(propertyDetails.get(7));
        RentableUnit unit = RentableUnitFactory.createRentableUnit(type, unitNumber, numOfBedrooms,numOfBathrooms,squareFootage, propertyDetails.get(0), propertyDetails.get(1), propertyDetails.get(2), propertyDetails.get(3));
        return unit;
    }
//    public RentableUnit createUnit(String[] buildingDetails, String type){
//        String[] unitDetails = gui.getUnitDetails(type);
//        String unitNumber = unitDetails[0];
//        int numOfBedrooms = Integer.parseInt(unitDetails[1]);
//        int numOfBathrooms = Integer.parseInt(unitDetails[2]);
//        double squareFootage = Double.parseDouble(unitDetails[3]);
//        RentableUnit unit = RentableUnitFactory.createRentableUnit(type, unitNumber, numOfBedrooms,numOfBathrooms,squareFootage, buildingDetails[0], buildingDetails[1], buildingDetails[2], buildingDetails[3]);
//        return unit;
//    }

}
