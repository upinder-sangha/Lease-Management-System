package com.gcs.app.view;

import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;
import com.gcs.app.model.Tenant;
import com.gcs.app.tasks.DisplayDynamicButtonsTask;
import com.gcs.app.tasks.FetchDataTask;
import com.gcs.app.tasks.SignLeaseTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class RentAUnitController {

	@FXML
	TabPane RentAUnitTabPane;
	@FXML
	Tab RentAUnitTenantsTab;
	@FXML
	Tab RentAUnitBuildingsTab;
	@FXML
	Tab RentAUnitUnitsTab;
	@FXML
	Tab RentAUnitLeaseTab;
	@FXML
	VBox RentAUnitTenantVBox;
	@FXML
	VBox RentAUnitBuildingVBox;
	@FXML
	VBox RentAUnitUnitVBox;
	@FXML
	Label RentAUnitTenantLabel;
	@FXML
	Label RentAUnitBuildingLabel;
	@FXML
	Label RentAUnitUnitLablel;
	@FXML
	TextField RentTextField;
	@FXML
	DatePicker StartDate;
	@FXML
	DatePicker EndDate;
	@FXML
	Label SignLeaseLabel;

	Tenant tenant = null;
	Property property = null;
	RentableUnit unit = null;

	public void initialize() {
		RentAUnitBuildingsTab.setDisable(true);
		RentAUnitUnitsTab.setDisable(true);
		RentAUnitLeaseTab.setDisable(true);
		displayTenantTab();
	}

	private void displayTenantTab() {
		DisplayDynamicButtonsTask displayDynamicButtonsTask = new DisplayDynamicButtonsTask("displayTenantsTab", null);
		displayDynamicButtonsTask.valueProperty().addListener(new ChangeListener<ArrayList<Button>>() {

			@Override
			public void changed(ObservableValue<? extends ArrayList<Button>> observable, ArrayList<Button> oldValue,
								ArrayList<Button> newValue) {
				addEventHandlerToTenantButtons(newValue);
				RentAUnitTenantVBox.getChildren().addAll(newValue);
			}
		});

		Thread displayThread = new Thread(displayDynamicButtonsTask);
		displayThread.setDaemon(true);
		displayThread.start();
	}

	private void displayPropertyTab() {
		RentAUnitBuildingVBox.getChildren().clear();
		DisplayDynamicButtonsTask displayDynamicButtonsTask = new DisplayDynamicButtonsTask("displayPropertyTab", null);
		displayDynamicButtonsTask.valueProperty().addListener(new ChangeListener<ArrayList<Button>>() {

			@Override
			public void changed(ObservableValue<? extends ArrayList<Button>> observable, ArrayList<Button> oldValue,
								ArrayList<Button> newValue) {
				addEventHandlerToPropertyButtons(newValue);
				RentAUnitBuildingVBox.getChildren().addAll(newValue);
			}
		});

		Thread displayThread = new Thread(displayDynamicButtonsTask);
		displayThread.setDaemon(true);
		displayThread.start();
	}

	private void displayUnitTab() {
		RentAUnitUnitVBox.getChildren().clear();
		DisplayDynamicButtonsTask displayDynamicButtonsTask = new DisplayDynamicButtonsTask("displayUnitTab", property );
		displayDynamicButtonsTask.valueProperty().addListener(new ChangeListener<ArrayList<Button>>() {

			@Override
			public void changed(ObservableValue<? extends ArrayList<Button>> observable, ArrayList<Button> oldValue,
								ArrayList<Button> newValue) {
				addEventHandlerToUnitButtons(newValue);
				RentAUnitUnitVBox.getChildren().addAll(newValue);
			}
		});

		Thread displayThread = new Thread(displayDynamicButtonsTask);
		displayThread.setDaemon(true);
		displayThread.start();
	}

	private void addEventHandlerToTenantButtons(ArrayList<Button> buttons) {
		for (Button btn : buttons) {
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					onTenantClick(btn.getId());
				}
			});
		}
	}

	private void addEventHandlerToPropertyButtons(ArrayList<Button> buttons) {
		for (Button btn : buttons) {
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					onPropertyClick(btn.getId());
				}
			});
		}
	}

	private void addEventHandlerToUnitButtons(ArrayList<Button> buttons) {
		for (Button btn : buttons) {
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					onUnitClick(btn.getId());
				}
			});
		}
	}

	private void onTenantClick(String id) {
		FetchDataTask fetchTenantData = new FetchDataTask("fetchTenant", null, id);
		fetchTenantData.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				tenant = (Tenant) newValue;
				RentAUnitBuildingsTab.setDisable(false);
				RentAUnitTabPane.getSelectionModel().select(RentAUnitBuildingsTab);
				RentAUnitBuildingLabel.setText("Select a building to rent for "+tenant.getName());
				RentAUnitBuildingLabel.setTextAlignment(TextAlignment.LEFT);
				displayPropertyTab();
			}
		});
		Thread displayThread = new Thread(fetchTenantData);
		displayThread.setDaemon(true);
		displayThread.start();
	}

	private void onPropertyClick(String id) {
		FetchDataTask fetchPropertyData = new FetchDataTask("fetchProperty", null,  id);
		fetchPropertyData.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				property = (Property) newValue;
				if(property.getType().equalsIgnoreCase("HOUSE")) {
					unit = property.getUnits().get(0);
					if(unit.getLease()==null) {
						RentAUnitLeaseTab.setDisable(false);
						RentAUnitTabPane.getSelectionModel().select(RentAUnitLeaseTab);
					}
					else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information");
						alert.setHeaderText("Success");
						unit.addObserverTenant(tenant);
						tenant.addUnitInterestedIn(unit);
						alert.setContentText("Added to your interested list of units!\n (The unit is rented right now)");
						alert.show();
					}
				}
				else {
					RentAUnitUnitsTab.setDisable(false);
					RentAUnitTabPane.getSelectionModel().select(RentAUnitUnitsTab);
					RentAUnitUnitLablel.setText("Select a unit to rent for "+tenant.getName()+" in "+property.getStreetNumber()+" "+property.getStreetName());
					RentAUnitUnitLablel.setTextAlignment(TextAlignment.LEFT);
					displayUnitTab();
				}
			}
		});
		Thread displayThread = new Thread(fetchPropertyData);
		displayThread.setDaemon(true);
		displayThread.start();
	}

	private void onUnitClick(String id) {
		FetchDataTask fetchUnitData = new FetchDataTask("fetchUnit", property, id);
		fetchUnitData.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				unit = (RentableUnit) newValue;
				if(unit.getLease()==null) {
					RentAUnitLeaseTab.setDisable(false);
					RentAUnitTabPane.getSelectionModel().select(RentAUnitLeaseTab);
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText("Success");
					unit.addObserverTenant(tenant);
					tenant.addUnitInterestedIn(unit);
					alert.setContentText("Added to your interested list of units!\n (The unit is rented right now)");
					alert.show();
				}
			}
		});
		Thread displayThread = new Thread(fetchUnitData);
		displayThread.setDaemon(true);
		displayThread.start();
	}

	@FXML
	public void onSignLeaseButtonClicked() {

		if(RentTextField.getText().isBlank() || StartDate.getValue()==null || EndDate.getValue()==null) {
			//return error
			return;
		}
		String rentValue = RentTextField.getText();
		Double rent = Double.parseDouble(rentValue);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = formatter.withLocale( Locale.CANADA );
		String startsOn = formatter.format(StartDate.getValue());
		String endsOn = formatter.format(EndDate.getValue());

		SignLeaseTask signLeaseTask = new SignLeaseTask(tenant, unit, rent, startsOn, endsOn);
		signLeaseTask.messageProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				SignLeaseLabel.setText(newValue);
			}
		});
		Thread displayThread = new Thread(signLeaseTask);
		displayThread.setDaemon(true);
		displayThread.start();

	}
}