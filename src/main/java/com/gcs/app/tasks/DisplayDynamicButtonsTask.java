package com.gcs.app.tasks;

import com.gcs.app.contoller.TenantController;
import com.gcs.app.model.Tenant;
import com.gcs.app.view.RentAUnitController;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class DisplayDynamicButtonsTask extends Task<ArrayList<Button>>{

	private String currentTab;

	public DisplayDynamicButtonsTask(String currentTab) {
		this.currentTab = currentTab;
	}

	@Override
	protected ArrayList<Button> call() throws Exception {
		StringBuffer propertyBuffer = null, unitBuffer;
		ArrayList<Button> buttons = new ArrayList<>();
//		AnchorPane pane;
//		Text text;
//		TextFlow textFlow;

		if ("tenantsTab".equalsIgnoreCase(currentTab)) {
			HashMap<String, Tenant> tenants = TenantController.getTenants();
			for (Entry<String, Tenant> tenant : tenants.entrySet()) {
				//Button button = new Button(tenant.getValue().getName()+" ("+tenant.getValue().getPhoneNumber()+")");
				//prefHeight="26.0" prefWidth="105.0" style="	x-background-color: dddddd; -fx-border-color: ffffff;" text="Button"
				
				buttons.add(createButton(tenant.getValue().getName(),tenant.getValue().getPhoneNumber()));
			}
		}
		return buttons;
	}
	
	private Button createButton(String tenantName, String tenantPhoneNumber) {
		Button button = new Button(tenantName+" ("+tenantPhoneNumber+")");
		button.setId(tenantPhoneNumber);
		addAttributesToButton(button);
		return button;
	}
	
	private void addAttributesToButton(Button button) {
		button.setMnemonicParsing(false);
		button.setPrefHeight(26.0);
		button.setPrefWidth(1.7976931348623157E308);
		button.setStyle("x-background-color: dddddd; -fx-border-color: ffffff");
		button.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	RentAUnitController.onTenantClick(button.getId());
	            }
	       });
	}
	
}
