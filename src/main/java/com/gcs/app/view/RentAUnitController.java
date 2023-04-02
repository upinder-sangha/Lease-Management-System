package com.gcs.app.view;

import com.gcs.app.tasks.DisplayDynamicButtonsTask;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
		displayTab("tenantsTab");
	}
	
	private void displayTab(String currentTab) {
		DisplayDynamicButtonsTask displayDynamicButtonsTask = new DisplayDynamicButtonsTask(currentTab);

		displayDynamicButtonsTask.valueProperty().addListener((observable,oldValue,newValue)->{
			RentAUnitTenantVBox.getChildren().addAll(newValue);
		});

//		displayDynamicButtonsTask.valueProperty().addListener(new ChangeListener<ArrayList<Button>>() {
//
//			@Override
//			public void changed(ObservableValue<? extends ArrayList<Button>> observable, ArrayList<Button> oldValue, ArrayList<Button> newValue) {
//				RentAUnitTenantVBox.getChildren().addAll(newValue);
//			}
//		});
		
		Thread displayThread = new Thread(displayDynamicButtonsTask);
		displayThread.setDaemon(true);
		displayThread.start();
	}
	
	public static void onTenantClick(String id) {
		System.out.println("in controller " +id);
	}
	
}
