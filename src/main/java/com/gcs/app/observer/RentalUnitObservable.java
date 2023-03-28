package com.gcs.app.observer;

import com.gcs.app.model.Tenant;

/**
 * Observable for Tenants to get notifications.
 *
 */
public interface RentalUnitObservable {

	public void addObserverTenant(Tenant t);
	public void removeObserverTenant(Tenant t);
	public void notifyInterestedTenants();
	
}
