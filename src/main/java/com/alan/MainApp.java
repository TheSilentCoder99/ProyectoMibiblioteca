package com.alan;

import com.alan.clases.Alertas;
import com.alan.clases.conexionDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaPrincipal.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("MI BIBLIOTECA");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);  // ← Esto maximiza la ventana
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Iniciando el programa...");
        launch(args);
        System.out.println("Programa cerrado.");
    }
}