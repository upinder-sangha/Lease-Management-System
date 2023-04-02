package com.gcs.app.view;

import com.gcs.app.tasks.AddPropertyTask;
import com.gcs.app.tasks.DisplayDynamicBuildingsTask;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddCondoController {
    @FXML
    TabPane addACondoTabPane;
    @FXML
    Tab addACondoBuildingsTab;
    @FXML
    VBox addACondoBuildingsVBox;
    @FXML
    Tab addACondoCondoTab;
    @FXML
    Button submitCondoButton;
    @FXML
    TextField unitNumber;
    @FXML
    TextField numberOfBedrooms;
    @FXML
    TextField numberOfBathrooms;
    @FXML
    TextField squareFootage;
    @FXML
    Label message;

    String streetNumber;
    String streetName;
    String city;
    String postalCode;
    String civicAddress;


    public void initialize() {
        displayTab("buildingsTab");
    }

    private void displayTab(String currentTab) {
        DisplayDynamicBuildingsTask displayDynamicBuildingsTask = new DisplayDynamicBuildingsTask(currentTab, this);

        displayDynamicBuildingsTask.valueProperty().addListener((observable,oldValue,newValue)->{
            addACondoBuildingsVBox.getChildren().addAll(newValue);
        });

        Thread displayThread = new Thread(displayDynamicBuildingsTask);
        displayThread.setDaemon(true);
        displayThread.start();
    }

    public void onSelectingBuilding(String streetNumber, String streetName, String city, String postalCode, String civicAddress){
        //move to next tab
        addACondoTabPane.getSelectionModel().select(addACondoCondoTab);

        this.civicAddress = civicAddress;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;

    }

    @FXML
    protected void onSubmitCondoDetailsClick(){

        String unitNumber = this.unitNumber.getText();
        String numberOfBedrooms = this.numberOfBedrooms.getText();
        String numberOfBathrooms = this.numberOfBathrooms.getText();
        String squareFootage = this.squareFootage.getText();

        AddPropertyTask addPropertyTask = new AddPropertyTask("Condo", streetNumber, streetName, city, postalCode,unitNumber, numberOfBedrooms,numberOfBathrooms,squareFootage, civicAddress);
        addPropertyTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            message.setText(newValue);
            this.unitNumber.setText("");
            this.numberOfBedrooms.setText("");
            this.numberOfBathrooms.setText("");
            this.squareFootage.setText("");
        });

        Thread addCondoThread = new Thread(addPropertyTask);
        addCondoThread.setDaemon(true);
        addCondoThread.start();
    }

}
