package com.gcs.app.view;

import com.gcs.app.tasks.ChangeDateTask;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ChangeDateController {

    @FXML
    DatePicker newDate;
    @FXML
    Button setDateButton;
    @FXML
    Label setDateLabel;

    @FXML
    protected void onSetDateButtonClicked() {
        if(newDate.getValue()==null) {
            //return error
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter = formatter.withLocale( Locale.CANADA );
        String newDate = formatter.format(this.newDate.getValue());

        ChangeDateTask changeDateTask = new ChangeDateTask(newDate);
        changeDateTask.valueProperty().addListener((observable,oldValue,newValue) -> {
            setDateLabel.setText(newValue);
            System.out.println(Thread.currentThread().getId());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Notifications Sent");
            alert.setContentText("alertMessage");
            alert.show();
        });
        Thread changeDateThread = new Thread(changeDateTask);
        changeDateThread.setDaemon(true);
        changeDateThread.start();
    }

}
