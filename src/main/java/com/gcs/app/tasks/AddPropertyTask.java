package com.gcs.app.tasks;

import com.gcs.app.contoller.PropertyController;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class AddPropertyTask extends Task<String> {
    ArrayList<String> propertyDetails;
    String choiceOfProperty;

    public AddPropertyTask(String choiceOfProperty, String streetNumber, String streetName, String city, String postalCode) {
        this.choiceOfProperty = choiceOfProperty;
        propertyDetails = new ArrayList<>();
        propertyDetails.add(streetNumber);
        propertyDetails.add(streetName);
        propertyDetails.add(city);
        propertyDetails.add(postalCode);
    }

    public AddPropertyTask(String choiceOfProperty, String streetNumber, String streetName, String city,String postalCode, String unitNumber,String numberOfBedrooms, String numberOfBathrooms, String squareFootage) {
        this.choiceOfProperty = choiceOfProperty;
        propertyDetails = new ArrayList<>();
        propertyDetails.add(streetNumber);
        propertyDetails.add(streetName);
        propertyDetails.add(city);
        propertyDetails.add(postalCode);
        propertyDetails.add(unitNumber);
        propertyDetails.add(numberOfBedrooms);
        propertyDetails.add(numberOfBathrooms);
        propertyDetails.add(squareFootage);
    }

    public AddPropertyTask(String choiceOfProperty, String streetNumber, String streetName, String city,String postalCode, String unitNumber,String numberOfBedrooms, String numberOfBathrooms, String squareFootage, String civicAddress) {
        this.choiceOfProperty = choiceOfProperty;
        propertyDetails = new ArrayList<>();
        propertyDetails.add(streetNumber);
        propertyDetails.add(streetName);
        propertyDetails.add(city);
        propertyDetails.add(postalCode);
        propertyDetails.add(unitNumber);
        propertyDetails.add(numberOfBedrooms);
        propertyDetails.add(numberOfBathrooms);
        propertyDetails.add(squareFootage);
        propertyDetails.add(civicAddress);
    }

    @Override
    protected String call() throws Exception {
        if(choiceOfProperty.equalsIgnoreCase("Building")) {
            if (!propertyDetails.get(0).isBlank() && !propertyDetails.get(1).isBlank() && !propertyDetails.get(2).isBlank() && !propertyDetails.get(3).isBlank())
                PropertyController.addProperty(choiceOfProperty, propertyDetails);
            else
                return "Please fill all details!";
            return "Building added successfully";
        }
        else if(choiceOfProperty.equalsIgnoreCase("House")) {
            if (!propertyDetails.get(0).isBlank() && !propertyDetails.get(1).isBlank() && !propertyDetails.get(2).isBlank() && !propertyDetails.get(3).isBlank() &&!propertyDetails.get(4).isBlank() && !propertyDetails.get(5).isBlank() && !propertyDetails.get(6).isBlank() && !propertyDetails.get(7).isBlank())
                PropertyController.addProperty(choiceOfProperty, propertyDetails);
            else
                return "Please fill all details!";
            return "House added successfully";
        }
        else if(choiceOfProperty.equalsIgnoreCase("Apartment")) {
            if (!propertyDetails.get(0).isBlank() && !propertyDetails.get(1).isBlank() && !propertyDetails.get(2).isBlank() && !propertyDetails.get(3).isBlank() &&!propertyDetails.get(4).isBlank() && !propertyDetails.get(5).isBlank() && !propertyDetails.get(6).isBlank() && !propertyDetails.get(7).isBlank())
                PropertyController.addProperty(choiceOfProperty, propertyDetails);
            else
                return "Please fill all details!";
            return "Apartment added successfully";
        }
        else if(choiceOfProperty.equalsIgnoreCase("Condo")) {
            if (!propertyDetails.get(0).isBlank() && !propertyDetails.get(1).isBlank() && !propertyDetails.get(2).isBlank() && !propertyDetails.get(3).isBlank() &&!propertyDetails.get(4).isBlank() && !propertyDetails.get(5).isBlank() && !propertyDetails.get(6).isBlank() && !propertyDetails.get(7).isBlank())
                PropertyController.addProperty(choiceOfProperty, propertyDetails);
            else
                return "Please fill all details!";
            return "condo added successfully";
        }

        return "Successful";
    }

}
