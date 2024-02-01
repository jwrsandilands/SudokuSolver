module com.example.sodukusolver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sodukusolver to javafx.fxml;
    exports com.example.sodukusolver;
}