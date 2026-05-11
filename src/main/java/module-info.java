module com.alan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    opens com.alan to javafx.fxml;
    exports com.alan;
    exports com.alan.controladores;
    opens com.alan.controladores to javafx.fxml;
    opens com.alan.clases to javafx.base;
    opens com.alan.clases.clasesCompuestas to javafx.base;
    requires javafx.base;
    requires atlantafx.base;
    requires javafx.web;
    requires com.github.librepdf.openpdf;
}

//Explicación línea por línea:
//
//        requires javafx.controls; → Para usar botones, labels, etc.
//
//        requires javafx.fxml; → Para poder usar FXMLLoader y @FXML
//
//        opens com.alan to javafx.fxml; → Permite que JavaFX acceda a tu controlador por reflexión
//
//        exports com.alan; → Exporta tu paquete para que otros módulos puedan usarlo