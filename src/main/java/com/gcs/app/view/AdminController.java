package com.gcs.app.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import com.gcs.app.session.Session;


public class AdminController {
    @FXML
    private Label welcomeText;
    @FXML
    private AnchorPane currentViewPane;

    @FXML
    private Text userName;
    
    @FXML
	private Button logoutBtn;
    
    public void initialize() {
    	userName.setText(Session.getInstance().getUserName());
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
        DisplayController controller = loader.getController();
        controller.setOperation("displayTenants");
        currentViewPane.getChildren().setAll(root);
        controller.display();
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
        DisplayController controller = loader.getController();
        controller.setOperation("displayProperties");
        currentViewPane.getChildren().setAll(root);
        controller.display();
    }
    @FXML
    protected void onDisplayRentedUnitsButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
        DisplayController controller = loader.getController();
        controller.setOperation("displayRentedUnits");
        controller.display();
    }
    @FXML
    protected void onDisplayVacantUnitsButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
        DisplayController controller = loader.getController();
        controller.setOperation("displayVacantUnits");
        controller.display();
    }
    @FXML
    protected void onDisplayLeasesButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
        DisplayController controller = loader.getController();
        controller.setOperation("displayLeases");
        controller.display();
    }
    @FXML
    protected void onDisplayTheRentStatusButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
        Node root = loader.load();
        currentViewPane.getChildren().setAll(root);
        DisplayController controller = loader.getController();
        controller.setOperation("displayRentStatus");
        controller.display();
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
    
    @FXML
    protected void onLogoutBtnClick() throws IOException {
    	SessionController.logoutSession();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load(), 700, 400);
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
    	stage.setScene(scene);
    }

}