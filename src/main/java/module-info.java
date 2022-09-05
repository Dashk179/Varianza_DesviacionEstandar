module com.example.varianza_desviacionestandar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.varianza_desviacionestandar to javafx.fxml;
    exports com.example.varianza_desviacionestandar;
}