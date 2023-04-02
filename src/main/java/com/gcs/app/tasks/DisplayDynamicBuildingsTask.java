package com.gcs.app.tasks;

import com.gcs.app.contoller.PropertyController;
import com.gcs.app.model.Property;
import com.gcs.app.view.AddApartmentController;
import com.gcs.app.view.AddCondoController;
import javafx.concurrent.Task;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class DisplayDynamicBuildingsTask extends Task<ArrayList<Button>>{

    private String currentTab;
    private AddApartmentController addApartmentController;
    private AddCondoController addCondoController;

    public DisplayDynamicBuildingsTask(String currentTab, AddApartmentController addApartmentController) {
        this.currentTab = currentTab;
        this.addApartmentController = addApartmentController;
    }
    public DisplayDynamicBuildingsTask(String currentTab, AddCondoController addCondoController) {
        this.currentTab = currentTab;
        this.addCondoController = addCondoController;
    }

    @Override
    protected ArrayList<Button> call() throws Exception {
        ArrayList<Button> buttons = new ArrayList<>();

        if("buildingsTab".equalsIgnoreCase(currentTab)){
            ArrayList<Property> properties = PropertyController.getProperties();
            for (Property property : properties) {
                if ("multistoryBuilding".equalsIgnoreCase(property.getType()))
                    buttons.add(createPropertyButton(property.getCivicAddress()));
            }
        }
        return buttons;
    }



    private Button createPropertyButton(String address) {
        Button button = new Button(address);
        button.setId(address);
        addAttributesToPropertyButton(button);
        return button;
    }
    private void addAttributesToPropertyButton(Button button) {
        button.setMnemonicParsing(false);
        button.setPrefHeight(26.0);
        button.setPrefWidth(500);
        button.setStyle("x-background-color: dddddd; -fx-border-color: ffffff");

        button.setOnAction(event -> {
            for (Property property : PropertyController.getProperties()) {
                if(property.getCivicAddress().equalsIgnoreCase(button.getId()))
                    if(addApartmentController!= null)
                        addApartmentController.onSelectingBuilding(property.getStreetNumber(),property.getStreetName(),property.getCity(),property.getPostalCode(),property.getCivicAddress());
                    else if(addCondoController!=null)
                        addCondoController.onSelectingBuilding(property.getStreetNumber(),property.getStreetName(),property.getCity(),property.getPostalCode(),property.getCivicAddress());
            }
        });
    }

}
