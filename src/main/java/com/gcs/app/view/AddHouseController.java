package com.gcs.app.view;

import com.gcs.app.tasks.AddPropertyTask;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddHouseController {
    @FXML
    TextField streetNumber;
    @FXML
    TextField streetName;
    @FXML
    TextField city;
    @FXML
    TextField postalCode;

    @FXML
    TextField numberOfBedrooms;
    @FXML
    TextField numberOfBathrooms;
    @FXML
    TextField squareFootage;
    @FXML
    Label message;

    @FXML
    Button submitHouseButton;


    @FXML
    protected void onSubmitHouseDetailsClick() {
        String streetNumber = this.streetNumber.getText();
        String streetName = this.streetName.getText();
        String city = this.city.getText();
        String postalCode = this.postalCode.getText();
        String numberOfBedrooms = this.numberOfBedrooms.getText();
        String numberOfBathrooms = this.numberOfBathrooms.getText();
        String squareFootage = this.squareFootage.getText();
        String unitNumber = "0";

        AddPropertyTask addPropertyTask = new AddPropertyTask("House", streetNumber, streetName, city, postalCode,unitNumber, numberOfBedrooms,numberOfBathrooms,squareFootage);
        addPropertyTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            message.setText(newValue);
            this.streetNumber.setText("");
            this.streetName.setText("");
            this.city.setText("");
            this.postalCode.setText("");
            this.numberOfBedrooms.setText("");
            this.numberOfBathrooms.setText("");
            this.squareFootage.setText("");
        });

        Thread addHouseThread = new Thread(addPropertyTask);
        addHouseThread.setDaemon(true);
        addHouseThread.start();
    }

}
