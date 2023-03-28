package com.gcs.app.observer;

import com.gcs.app.model.RentableUnit;

/**
 * Observer for notifications.
 *
 */
public interface TenantObserver {

	public String update(RentableUnit unit);
	
}
