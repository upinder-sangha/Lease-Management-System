module com.gcs.app.app_project_phase_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gcs.app.view to javafx.fxml;
    exports com.gcs.app.view;
}