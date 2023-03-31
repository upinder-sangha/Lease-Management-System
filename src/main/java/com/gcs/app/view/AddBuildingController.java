package com.gcs.app.view;

import com.gcs.app.tasks.AddPropertyTask;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBuildingController {
    @FXML
    TextField streetNumber;
    @FXML
    TextField streetName;
    @FXML
    TextField city;
    @FXML
    TextField postalCode;
    @FXML
    Label message;

    @FXML
    Button submitPropertyButton;


    @FXML
    protected void onSubmitBuildingDetailsClick() {
        String streetNumber = this.streetNumber.getText();
        String streetName = this.streetName.getText();
        String city = this.city.getText();
        String postalCode = this.postalCode.getText();

        AddPropertyTask addPropertyTask = new AddPropertyTask("Building", streetNumber, streetName, city, postalCode);
        addPropertyTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            message.setText(newValue);
            this.streetNumber.setText("");
            this.streetName.setText("");
            this.city.setText("");
            this.postalCode.setText("");
        });

        Thread addBuildingThread = new Thread(addPropertyTask);
        addBuildingThread.setDaemon(true);
        addBuildingThread.start();
    }

}
