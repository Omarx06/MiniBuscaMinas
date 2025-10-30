module com.example.minibuscaminas.minibuscaminas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.minibuscaminas.minibuscaminas to javafx.fxml;
    exports com.example.minibuscaminas.minibuscaminas;
}