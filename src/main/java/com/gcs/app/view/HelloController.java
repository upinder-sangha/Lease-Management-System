package com.gcs.app.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private AnchorPane currentViewPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onAddATenantButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-a-tenant.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onDisplayTenantsButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onRentAUnitButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rent-a-unit.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onDisplayPropertiesButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onDisplayRentedUnitsButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onDisplayVacantUnitsButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onDisplayLeasesButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onDisplayTheRentStatusButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onAddABuildingButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-a-building.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onAddAHouseButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-a-house.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onAddAnApartmentButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-an-apartment.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }
    @FXML
    protected void onAddACondoButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-a-condo.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
    }

}