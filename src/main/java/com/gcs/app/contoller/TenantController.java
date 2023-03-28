package com.gcs.app.contoller;

import java.util.HashMap;

import com.gcs.app.model.Tenant;

public class TenantController {
	
	
		
	private static HashMap<String, Tenant> tenants = new HashMap<>();
	
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
