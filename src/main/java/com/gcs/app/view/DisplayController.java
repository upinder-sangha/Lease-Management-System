package com.gcs.app.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gcs.app.model.Lease;
import com.gcs.app.tasks.DisplayTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private static ArrayList<Lease> leases;
	private static ArrayList<String> leaseIndexes;
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void setUnitsForUser(ArrayList<Lease> leases) {
		DisplayController.leases = leases;
		
	}
	
	public void initialize() {
		displayBox.getChildren().clear();
		leaseIndexes = new ArrayList<>();
	}
	
	public EventHandler<ActionEvent> confirmAndPayHandler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            if(!leaseIndexes.isEmpty()) {            	
            	for(String index : leaseIndexes) {
            		leases.get(Integer.valueOf(index)).payRent();
            	}
            	Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Success");
    			alert.setHeaderText("Rent");
    			alert.setContentText("Rent Payment Success");
    			Optional<ButtonType> result = alert.showAndWait();
    			ButtonType button = result.orElse(ButtonType.CANCEL);
    			List<CheckBox> checkBoxesToRemove = new ArrayList<>();
    			if (button == ButtonType.OK) {
    				AnchorPane checkBoxPane = (AnchorPane) displayBox.getChildren().get(0);
    				for(Node checkBoxes : checkBoxPane.getChildren()) {
		    			checkBoxesToRemove.add((CheckBox)checkBoxes);
		    		}
    				checkBoxPane.getChildren().removeAll(checkBoxesToRemove);
    				
    				if(checkBoxPane.getChildren().size() == 0) {
    					displayBox.getChildren().clear();
    				}
    			} else {
    			    System.out.println("canceled");
    			}
            }   
        };
    };
		
    public EventHandler<ActionEvent> checkBoxCheckedHandler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
        	if(((CheckBox)e.getSource()).isSelected()) {
        		String leaseIndex = ((CheckBox)e.getSource()).getId();
        		leaseIndexes.add(leaseIndex);
        	}
        	else if(!((CheckBox)e.getSource()).isSelected()) {
        		String leaseIndex = ((CheckBox)e.getSource()).getId();
        		leaseIndexes.remove(leaseIndex);
        	}
        };
    };

    
	public void display() {
		initialize();
		DisplayTask displayTask = new DisplayTask(operation,leases,confirmAndPayHandler,checkBoxCheckedHandler);
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
		}else if("displayLeasesForUser".equalsIgnoreCase(operation)) {
			headingLabel.setText("Your Leases");
		}else if("displayInterestedPropertiesForUser".equalsIgnoreCase(operation)) {
			headingLabel.setText("Your Leases");
		}else if("displayRentedUnitsByUser".equalsIgnoreCase(operation)) {
			headingLabel.setText("Pay Rent");
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
