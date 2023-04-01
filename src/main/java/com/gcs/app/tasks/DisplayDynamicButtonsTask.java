package com.gcs.app.tasks;

import com.gcs.app.contoller.PropertyController;
import com.gcs.app.contoller.TenantController;
import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;
import com.gcs.app.model.Tenant;
import com.gcs.app.view.RentAUnitController;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class DisplayDynamicButtonsTask extends Task<ArrayList<Button>>{

	private String currentTab;
	private Object idToDisplayFrom;

	public DisplayDynamicButtonsTask(String currentTab, Object idToDisplayFrom) {
		this.currentTab = currentTab;
		this.idToDisplayFrom = idToDisplayFrom;
	}

	@Override
	protected ArrayList<Button> call() throws Exception {
		StringBuffer propertyBuffer = null, unitBuffer;
		ArrayList<Button> buttons = new ArrayList<>();
//		AnchorPane pane;
//		Text text;
//		TextFlow textFlow;

		if ("displayTenantsTab".equalsIgnoreCase(currentTab)) {
			HashMap<String, Tenant> tenants = TenantController.getTenants();
			for (Entry<String, Tenant> tenant : tenants.entrySet()) {
				//Button button = new Button(tenant.getValue().getName()+" ("+tenant.getValue().getPhoneNumber()+")");
				//prefHeight="26.0" prefWidth="105.0" style="	x-background-color: dddddd; -fx-border-color: ffffff;" text="Button"
				
				buttons.add(createTenantButton(tenant.getValue().getName(),tenant.getValue().getPhoneNumber()));
			}
		}
		
		else if("displayPropertyTab".equals(currentTab)) {
			ArrayList<Property> properties = PropertyController.getProperties();
			System.out.println("properties size "+properties.size());
			for(int i = 0;i<properties.size();i++) {
				Property property = properties.get(i);
				buttons.add(createPropertyButton(property.getCivicAddress(),property.getType(), Integer.toString(i)));
			}
		}

		else if("displayUnitTab".equals(currentTab)) {
			Property property = (Property) idToDisplayFrom;
			ArrayList<RentableUnit> units = property.getUnits();
			for(int i =0;i<units.size();i++) {
				RentableUnit unit = units.get(i);
				buttons.add(createUnitButton(unit.getAddress(), Integer.toString(i)));
			}
		}
		return buttons;
	}
	
	private Button createTenantButton(String tenantName, String tenantPhoneNumber) {
		Button button = new Button(tenantName+" ("+tenantPhoneNumber+")");
		button.setId(tenantPhoneNumber);
		addAttributesToButton(button);
		return button;
	}
	
	private Button createPropertyButton(String address, String type, String i) {
		Button button = new Button(address+" ("+type+")");
		button.setId(i);
		addAttributesToButton(button);
		return button;
	}
	
	private Button createUnitButton(String address, String id) {
		Button button = new Button(address);
		button.setId(id);
		addAttributesToButton(button);
		return button;
	}
	
	private void addAttributesToButton(Button button) {
		button.setMnemonicParsing(false);
		button.setPrefHeight(26.0);
		button.setPrefWidth(1.7976931348623157E308);
		button.setStyle("x-background-color: dddddd; -fx-border-color: ffffff");
	}
	
	
}
