package com.example.minibuscaminas.minibuscaminas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Tablero tablero = new Tablero(5,5,3); // filas, columnas, minas
        Scene scene = new Scene(tablero.getGrid(), 300, 300);
        primaryStage.setTitle("Mini-Buscaminas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}