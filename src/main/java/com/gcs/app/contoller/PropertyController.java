package com.gcs.app.contoller;

import java.util.ArrayList;

import com.gcs.app.model.Property;

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

}
