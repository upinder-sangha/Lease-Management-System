package com.gcs.app.tasks;

import com.gcs.app.contoller.PropertyController;
import com.gcs.app.contoller.TenantController;
import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;

import javafx.concurrent.Task;

public class FetchDataTask extends Task<Object> {

	private String fetchOpertationType;
	private Object idToFetch;
	private Object objectToFetchFrom;
	
	public FetchDataTask(String fetchOperationType, Object objectToFetchFrom, Object idToFetch){
		this.fetchOpertationType = fetchOperationType;
		this.objectToFetchFrom = objectToFetchFrom; 
		this.idToFetch = idToFetch;
	}
	
	@Override
	protected Object call() throws Exception {
		if("fetchTenant".equals(fetchOpertationType)) {
			return TenantController.getTenant((String)idToFetch);
		}
		else if("fetchProperty".equals(fetchOpertationType)) {
			return PropertyController.getProperties().get(Integer.parseInt((String) idToFetch));
		}
		else if("fetchUnit".equals(fetchOpertationType)) {
			Property property = (Property) objectToFetchFrom;
			RentableUnit unit = property.getUnits().get(Integer.parseInt((String)idToFetch));
			return unit;
		}
		return null;
	}

}
