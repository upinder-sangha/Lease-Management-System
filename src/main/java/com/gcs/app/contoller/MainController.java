package com.gcs.app.contoller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.gcs.app.model.Property;
import com.gcs.app.model.Tenant;

public class MainController {


	/**
     * Load data for properties and tenants
     */
    public String loadData() {
        try {
            FileInputStream propertyFIS = new FileInputStream("properties.bin");
            ObjectInputStream propertyOIS = new ObjectInputStream(propertyFIS);
            PropertyController.setProperties((ArrayList<Property>) propertyOIS.readObject());
            propertyOIS.close();
            propertyFIS.close();
        } catch (FileNotFoundException e) {
            return "Input file not found for properties";
        } catch (IOException e) {
            return "Cannot read input file";
        } catch (ClassNotFoundException e) {
            return "Cannot find PropertyList in file";
        }

        try {
            FileInputStream tenantFIS = new FileInputStream("tenants.bin");
            ObjectInputStream tenantOIS = new ObjectInputStream(tenantFIS);
            TenantController.setTenants((HashMap<String, Tenant>) tenantOIS.readObject());
            tenantOIS.close();
            tenantFIS.close();
        } catch (FileNotFoundException e) {
            return "Input file not found for tenants";
        } catch (IOException e) {
            return "Cannot read input file";
        } catch (ClassNotFoundException e) {
            return "Cannot find TenantList in file";
        }
        return "1";
    }
	
}
