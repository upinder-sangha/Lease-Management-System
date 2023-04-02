package com.gcs.app.view;

import java.io.IOException;
import java.util.ArrayList;

import com.gcs.app.contoller.TenantController;
import com.gcs.app.model.Lease;
import com.gcs.app.model.Tenant;
import com.gcs.app.session.Session;
import com.gcs.app.tasks.ExistingTenantTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {

	private ArrayList<Lease> leases;
	
	@FXML
	private Label userName;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button displayUserProperties;

	@FXML
	private Button displayUserVacantUnits;

	@FXML
	private Button payRent;

	@FXML
	private Button displayUserInterestedProperties;

	@FXML
	private Button displayUserLeases;

	@FXML
	private AnchorPane displayAnchorPane;

	public void initialize() {
		Tenant tenant = TenantController.getTenant(Session.getInstance().getUserName());
		userName.setText(tenant.getName());
		ExistingTenantTask task = new ExistingTenantTask(tenant.getPhoneNumber());
		task.valueProperty().addListener((observable,oldValue,newValue)->{
			if(newValue != null) {
				if(payRent.isDisabled())
					payRent.setDisable(false);
				leases = newValue;
			}
		});
		Thread taskThread = new Thread(task);
		taskThread.setDaemon(true);
		taskThread.start();
	}

	@FXML
	protected void onLogoutBtnClick() throws IOException {
		SessionController.logoutSession();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
		Scene scene = new Scene(loader.load(), 700, 400);
		Stage stage = (Stage) logoutBtn.getScene().getWindow();
		stage.setScene(scene);
	}

	@FXML
	protected void onDisplayPropertiesBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
		Node root = loader.load();
		DisplayController controller = loader.getController();
		controller.setOperation("displayProperties");
		displayAnchorPane.getChildren().setAll(root);
		controller.display();
	}

	@FXML
	protected void onDisplayVacantUnitsBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
		Node root = loader.load();
		displayAnchorPane.getChildren().setAll(root);
		DisplayController controller = loader.getController();
		controller.setOperation("displayVacantUnits");
		controller.display();
	}

	@FXML
	protected void onPayRentBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
		Node root = loader.load();
		displayAnchorPane.getChildren().setAll(root);
		DisplayController controller = loader.getController();
		controller.setOperation("displayRentedUnitsByUser");
		controller.setUnitsForUser(leases);
		controller.display();
	}

	@FXML
	protected void onDisplayInterestedPropertiesBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
		Node root = loader.load();
		displayAnchorPane.getChildren().setAll(root);
		DisplayController controller = loader.getController();
		controller.setOperation("displayInterestedPropertiesForUser");
		controller.display();
	}

	@FXML
	protected void onDisplayLeasesBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("display-info.fxml"));
		Node root = loader.load();
		displayAnchorPane.getChildren().setAll(root);
		DisplayController controller = loader.getController();
		controller.setOperation("displayLeasesForUser");
		controller.display();
	}
}
