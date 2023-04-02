package com.gcs.app.contoller;

import java.util.ArrayList;
import java.util.HashMap;

import com.gcs.app.model.Lease;
import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;
import com.gcs.app.model.Tenant;

public class TenantController {
		
	private static HashMap<String, Tenant> tenants = new HashMap<>();
	
	
    /**
     * Add a new Tenant.
     */
    public static void addTenant(String name, String phone, String email, String password) {
        Tenant tenant = new Tenant(name, phone, email,password);
        tenants.put(tenant.getPhoneNumber(), tenant);
    }
	
    /**
     * @param properties
     * @param tenant
     * @return Lease 
     */
    public static ArrayList<Lease> getLeases(ArrayList<Property> properties, String phoneNumber){
        ArrayList<Lease> leases = new ArrayList<>();
        for (Property property : properties) {
            ArrayList<RentableUnit> units = property.getUnits();
            for (RentableUnit unit : units) {
                Lease lease = unit.getLease();
                if (lease != null) {
                    if (lease.getTenant().getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                        leases.add(lease);
                    }
                }
            }
        }
        return leases;
    }
    
    
	public static void setTenants(HashMap<String, Tenant> tenants) {
        TenantController.tenants = tenants;
    }
	
	public static Tenant getTenant(String phoneNumber) {
        return tenants.get(phoneNumber);
    }

	 /**
     * @return Tenants
     */
    public static HashMap<String, Tenant> getTenants() {
        return tenants;
    }
}
