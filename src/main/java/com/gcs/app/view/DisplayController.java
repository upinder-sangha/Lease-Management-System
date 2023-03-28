package com.gcs.app.view;

import java.util.ArrayList;

import com.gcs.app.tasks.DisplayTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DisplayController {
	@FXML
	AnchorPane parentAnchorPane;
	@FXML
	SplitPane parentSplitPane;
	@FXML
	Label headingLabel;
	@FXML
	ScrollPane scrollPane;
	@FXML
	VBox displayBox;
	
	@FXML		
	TextFlow textFlowBox;
	
	@FXML
	Text text;
	
	private String operation;
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void initialize() {
		displayBox.getChildren().clear();
	}

	public void display() {
		initialize();
		DisplayTask displayTask = new DisplayTask(operation);
		if("displayTenants".equalsIgnoreCase(operation)) {
			headingLabel.setText("Tenants");
		}else if("displayProperties".equalsIgnoreCase(operation)) {
			headingLabel.setText("Properties");
		}else if("displayRentedUnits".equalsIgnoreCase(operation)) {
			headingLabel.setText("Rented Units");
		}else if("displayVacantUnits".equalsIgnoreCase(operation)) {
			headingLabel.setText("Vacant Units");
		}else if("displayLeases".equalsIgnoreCase(operation)) {
			headingLabel.setText("Leases");
		}else if("displayRentStatus".equalsIgnoreCase(operation)) {
			headingLabel.setText("Rent Status");
		}
		displayTask.valueProperty().addListener(new ChangeListener<ArrayList<AnchorPane>>() {

			@Override
			public void changed(ObservableValue<? extends ArrayList<AnchorPane>> observable, ArrayList<AnchorPane> oldValue, ArrayList<AnchorPane> newValue) {
				displayBox.getChildren().addAll(newValue);
			}
		});
		
		Thread displayThread = new Thread(displayTask);
		displayThread.setDaemon(true);
		displayThread.start();
	}
}
