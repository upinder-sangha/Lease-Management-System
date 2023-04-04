package com.gcs.app.tasks;

import com.gcs.app.utility.Date;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChangeDateTask extends Task<String> {

    String newDate;
    public ChangeDateTask(String newDate){
        this.newDate = newDate;
    }


    @Override
    protected String call() throws Exception {

        String alertMessage = null;
        if(!newDate.isBlank()) {
            alertMessage = Date.setDate(newDate);
            if (!(alertMessage == null)) {
                System.out.println(alertMessage);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notifications Sent");
                alert.setContentText("alertMessage");
                alert.show();
            }
        }
        else
            return "Please select a date!";

        return "Date changed successfully";
    }
}
