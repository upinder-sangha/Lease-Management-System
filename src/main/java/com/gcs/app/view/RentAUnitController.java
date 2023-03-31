package com.gcs.app.view;

import java.util.ArrayList;

import com.gcs.app.tasks.DisplayTabTask;
import com.gcs.app.tasks.DisplayTask;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
	VBox RentAUnitBuildinVBox;
	@FXML
	VBox RentAUnitUnitVBox;
	
	
	public void initialize() {
		displayTab("displayTenantsTab");
	}
	
	private void displayTab(String operation) {
		DisplayTabTask displayTabTask = new DisplayTabTask(operation);
		displayTabTask.valueProperty().addListener(new ChangeListener<ArrayList<Button>>() {

			@Override
			public void changed(ObservableValue<? extends ArrayList<Button>> observable, ArrayList<Button> oldValue, ArrayList<Button> newValue) {
				RentAUnitTenantVBox.getChildren().addAll(newValue);
			}
		});
		
		Thread displayThread = new Thread(displayTabTask);
		displayThread.setDaemon(true);
		displayThread.start();
	}
	
	public static void onTenantClick(String id) {
		System.out.println("in controller " +id);
	}
	
}
