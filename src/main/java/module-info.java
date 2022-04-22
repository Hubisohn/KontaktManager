module com.example.observlist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.observlist to javafx.fxml;
    exports com.example.observlist;
}