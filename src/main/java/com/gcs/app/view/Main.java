package com.gcs.app.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import com.gcs.app.tasks.LoadDataTask;
// use separate fxml files for right side of pane and set the right side of the pane to those fxml files instead of switching the visibility
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Rental Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    	loadData();
        launch();
    }

	private static void loadData() {
		LoadDataTask loadDataTask = new LoadDataTask();
		loadDataTask.messageProperty().addListener((observer, oldValue, newValue)->{
			if("1".equalsIgnoreCase(newValue)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Success");
				alert.setContentText("Data Loaded Successfully!");
				alert.show();
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Err");
				alert.setHeaderText("Data Error");
				alert.setContentText(newValue);
				alert.show();
			}
			
		});
    	Thread loadDataThread = new Thread(loadDataTask);
    	loadDataThread.setDaemon(true);
    	loadDataThread.start();
	}
	
}