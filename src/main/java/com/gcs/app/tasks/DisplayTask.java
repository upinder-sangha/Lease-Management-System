package com.gcs.app.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.gcs.app.contoller.PropertyController;
import com.gcs.app.contoller.TenantController;
import com.gcs.app.model.Apartment;
import com.gcs.app.model.Condo;
import com.gcs.app.model.House;
import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;
import com.gcs.app.model.Tenant;

import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DisplayTask extends Task<ArrayList<AnchorPane>> {

	private String operation;

	public DisplayTask(String operation) {
		this.operation = operation;
	}

	@Override
	protected ArrayList<AnchorPane> call() throws Exception {
		StringBuffer buffer;
		ArrayList<AnchorPane> panes = new ArrayList<>();
		AnchorPane pane;
		Text text;
		TextFlow textFlow;

		if ("displayTenants".equalsIgnoreCase(operation)) {
			HashMap<String, Tenant> tenants = TenantController.getTenants();
			for (Entry<String, Tenant> tenant : tenants.entrySet()) {
				buffer = new StringBuffer();
				buffer.append("Tenant Name: " + tenant.getValue().getName() + System.lineSeparator());
				buffer.append("Tenant Phone Number: " + tenant.getValue().getPhoneNumber() + System.lineSeparator());
				buffer.append("Tenant Email Id: " + tenant.getValue().getEmailId() + System.lineSeparator());

				List<RentableUnit> propertiesInterestedIn = tenant.getValue().getUnitsInterestedIn();
				if (!propertiesInterestedIn.isEmpty()) {
					int count = 0;
					buffer.append(tenant.getValue().getName() + " is interested in the following Units"
							+ System.lineSeparator());
					for (RentableUnit rentableUnit : propertiesInterestedIn) {
						buffer.append(++count + ". " + rentableUnit.getAddress() + System.lineSeparator());
					}
				}
				text = new Text(buffer.toString());
				textFlow = new TextFlow(text);
				pane = new AnchorPane();
				pane.getChildren().add(textFlow);
				panes.add(pane);
			}
		}

		if ("displayProperties".equalsIgnoreCase(operation)) {
			ArrayList<Property> properties = PropertyController.getProperties();
			int count = 0;
			if (!properties.isEmpty()) {
				for (Property property : properties) {
					buffer = new StringBuffer();
					buffer.append(++count + ". " + property.getCivicAddress() + " (" + property.getType() + ")"
							+ System.lineSeparator());
					if (property.getType().equalsIgnoreCase("multistoryBuilding")) {
						ArrayList<RentableUnit> units = property.getUnits();
						if (!units.isEmpty()) {
							buffer.append("Units in this building are: " + System.lineSeparator());
							for (RentableUnit unit : units) {
								if (unit instanceof Condo)
									buffer.append(
											((Condo) unit).getCondoNumber() + " (Condo)" + System.lineSeparator());
								else if (unit instanceof Apartment) {
									buffer.append(((Apartment) unit).getApartmentNumber() + " (Apartment)"
											+ System.lineSeparator());
								}
							}
						}
					}
					text = new Text(buffer.toString());
					textFlow = new TextFlow(text);
					pane = new AnchorPane();
					pane.getChildren().add(textFlow);
					panes.add(pane);
				}
			} else {
				text = new Text("There are no properties");
				textFlow = new TextFlow(text);
				pane = new AnchorPane();
				pane.getChildren().add(textFlow);
				panes.add(pane);
			}
		}

		if ("displayRentedUnits".equalsIgnoreCase(operation)) {
			ArrayList<Property> properties = PropertyController.getProperties();
			int count = 0;
			if (!properties.isEmpty()) {
//				buffer.append("Rented Units are: " + System.lineSeparator());
				for (Property property : properties) {
					buffer = new StringBuffer();
					ArrayList<RentableUnit> units = property.getUnits();
					if (!units.isEmpty()) {
						for (RentableUnit unit : units) {
							if (unit.getLease() != null) {
								if (unit instanceof Condo)
									buffer.append(
											++count + ". " + unit.getAddress() + " (Condo)" + System.lineSeparator());
								else if (unit instanceof Apartment)
									buffer.append(++count + ". " + unit.getAddress() + " (Apartment)"
											+ System.lineSeparator());
								else if (unit instanceof House)
									buffer.append(
											++count + ". " + unit.getAddress() + " (House)" + System.lineSeparator());
								text = new Text(buffer.toString());
								textFlow = new TextFlow(text);
								pane = new AnchorPane();
								pane.getChildren().add(textFlow);
								panes.add(pane);
							}
						}
					}
					
				}
				if (count == 0) {
					text = new Text("There are no rented units");
					textFlow = new TextFlow(text);
					pane = new AnchorPane();
					pane.getChildren().add(textFlow);
					panes.add(pane);
				}
			} else {
				text = new Text("There are no properties");
				textFlow = new TextFlow(text);
				pane = new AnchorPane();
				pane.getChildren().add(textFlow);
				panes.add(pane);
			}
		}

		if ("displayVacantUnits".equalsIgnoreCase(operation)) {
			ArrayList<Property> properties = PropertyController.getProperties();
			int count = 0;
			if (!properties.isEmpty()) {
//				buffer.append("Vacant Units are: " + System.lineSeparator());
				for (Property property : properties) {
					buffer = new StringBuffer();
					ArrayList<RentableUnit> units = property.getUnits();
					if (!units.isEmpty()) {
						for (RentableUnit unit : units) {
							if (unit.getLease() == null) {
								if (unit instanceof Condo)
									buffer.append(
											++count + ". " + unit.getAddress() + " (Condo)" + System.lineSeparator());
								else if (unit instanceof Apartment)
									buffer.append(++count + ". " + unit.getAddress() + " (Apartment)"
											+ System.lineSeparator());
								else if (unit instanceof House)
									buffer.append(
											++count + ". " + unit.getAddress() + " (House)" + System.lineSeparator());
								text = new Text(buffer.toString());
								textFlow = new TextFlow(text);
								pane = new AnchorPane();
								pane.getChildren().add(textFlow);
								panes.add(pane);
							}
						}
					}
				}
				if (count == 0) {
					text = new Text("There are no vacant units");
					textFlow = new TextFlow(text);
					pane = new AnchorPane();
					pane.getChildren().add(textFlow);
					panes.add(pane);
				}
			} else {
				text = new Text("There are no properties");
				textFlow = new TextFlow(text);
				pane = new AnchorPane();
				pane.getChildren().add(textFlow);
				panes.add(pane);
			}
		}

		if ("displayLeases".equalsIgnoreCase(operation)) {
			ArrayList<Property> properties = PropertyController.getProperties();
			int count = 0;
			if (!properties.isEmpty()) {
//				buffer.append("Following are the leases: " + System.lineSeparator());
				for (Property property : properties) {
					buffer = new StringBuffer();
					ArrayList<RentableUnit> units = property.getUnits();
					if (!units.isEmpty()) {
						for (RentableUnit unit : units) {
							if (unit.getLease() != null) {
								buffer.append(++count + ".");
								buffer.append(unit.getLease().toString());
								text = new Text(buffer.toString());
								textFlow = new TextFlow(text);
								pane = new AnchorPane();
								pane.getChildren().add(textFlow);
								panes.add(pane);
							}
						}
					}
				}
				if (count == 0) {
					text = new Text("There are no leases");
					textFlow = new TextFlow(text);
					pane = new AnchorPane();
					pane.getChildren().add(textFlow);
					panes.add(pane);
				}
			} else {
				text = new Text("There are no properties");
				textFlow = new TextFlow(text);
				pane = new AnchorPane();
				pane.getChildren().add(textFlow);
				panes.add(pane);
			}
		}

		if ("displayRentStatus".equalsIgnoreCase(operation)) {
			ArrayList<Property> properties = PropertyController.getProperties();
			int count = 0;
			if (!properties.isEmpty()) {
//				buffer.append("Rented Units are: " + System.lineSeparator());
				for (Property property : properties) {
					buffer = new StringBuffer();
					ArrayList<RentableUnit> units = property.getUnits();
					if (!units.isEmpty()) {
						for (RentableUnit unit : units) {
							if (unit.getLease() != null) {
								if (unit instanceof Condo)
									buffer.append(++count + ". " + unit.getAddress() + " (Condo)");
								else if (unit instanceof Apartment)
									buffer.append(++count + ". " + unit.getAddress() + " (Apartment)");
								else if (unit instanceof House)
									buffer.append(++count + ". " + unit.getAddress() + " (House)");
								if (unit.getLease().isMonthlyRentPaid())
									buffer.append(" (Paid)");
								else
									buffer.append(" (Not Paid)");
								text = new Text(buffer.toString());
								textFlow = new TextFlow(text);
								pane = new AnchorPane();
								pane.getChildren().add(textFlow);
								panes.add(pane);
							}
						}
					}
				}
				if (count == 0) {
					text = new Text("There are no rented units");
					textFlow = new TextFlow(text);
					pane = new AnchorPane();
					pane.getChildren().add(textFlow);
					panes.add(pane);
				}
			} else {
				text = new Text("There are no properties");
				textFlow = new TextFlow(text);
				pane = new AnchorPane();
				pane.getChildren().add(textFlow);
				panes.add(pane);
			}
		}
		return panes;
	}
}
