package com.example.minibuscaminas.minibuscaminas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Tablero {
    private GridPane grid;
    private VBox root;
    private Juego juego;
    private int casillasDescubiertas;
    private TextField filasInput;
    private TextField columnasInput;
    private TextField minasInput;
    private Button reiniciarBtn;

    public Tablero(int filas, int columnas, int minas) {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #e9f0ff, #bcd0ff);");

        grid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #cfd8dc; -fx-border-color: #455a64; -fx-border-width: 2px;");

        crearControlesSuperiores(filas, columnas, minas);
        iniciarJuego(filas, columnas, minas);
    }

    private void crearControlesSuperiores(int filas, int columnas, int minas) {
        HBox controles = new HBox(10);
        controles.setAlignment(Pos.CENTER);

        filasInput = new TextField(String.valueOf(filas));
        columnasInput = new TextField(String.valueOf(columnas));
        minasInput = new TextField(String.valueOf(minas));

        filasInput.setPrefWidth(50);
        columnasInput.setPrefWidth(50);
        minasInput.setPrefWidth(50);

        reiniciarBtn = new Button("🔄 Reiniciar");
        reiniciarBtn.setStyle("""
            -fx-background-color: #4CAF50;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-padding: 8px 16px;
            -fx-background-radius: 8;
        """);
        reiniciarBtn.setOnAction(e -> reiniciarJuego());

        controles.getChildren().addAll(
                new Label("Filas:"), filasInput,
                new Label("Columnas:"), columnasInput,
                new Label("Minas:"), minasInput,
                reiniciarBtn
        );

        root.getChildren().addAll(controles, grid);
    }

    private void iniciarJuego(int filas, int columnas, int minas) {
        juego = new Juego(filas, columnas, minas);
        casillasDescubiertas = 0;
        grid.getChildren().clear();

        Casilla[][] casillas = juego.getTablero();

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                Casilla casilla = casillas[f][c];
                grid.add(casilla.getBoton(), c, f);

                int finalF = f;
                int finalC = c;
                casilla.getBoton().setOnAction(e -> {
                    boolean continua = juego.descubrirCasilla(finalF, finalC);
                    mostrarCasilla(casilla);

                    if (!continua) {
                        mostrarMinas();
                        mostrarMensaje(" ¡Perdiste! Había una mina.");
                        deshabilitarTablero();
                    } else {
                        casillasDescubiertas++;
                        if (verificarVictoria()) {
                            mostrarMensaje(" ¡Ganaste! Has descubierto todas las casillas seguras.");
                            deshabilitarTablero();
                        }
                    }
                });
            }
        }
    }

    private void reiniciarJuego() {
        try {
            int filas = Integer.parseInt(filasInput.getText());
            int columnas = Integer.parseInt(columnasInput.getText());
            int minas = Integer.parseInt(minasInput.getText());

            if (minas >= filas * columnas) {
                mostrarMensaje(" Demasiadas minas para ese tamaño de tablero.");
                return;
            }

            iniciarJuego(filas, columnas, minas);
        } catch (NumberFormatException e) {
            mostrarMensaje(" Por favor, ingresa solo números válidos.");
        }
    }

    private void mostrarCasilla(Casilla casilla) {
        if (casilla.isMina()) {
            casilla.getBoton().setText("💣");
            casilla.getBoton().setStyle("-fx-background-color: #ff6b6b; -fx-font-size: 16px;");
        } else {
            int n = casilla.getMinasAdyacentes();
            casilla.getBoton().setText(n == 0 ? "" : String.valueOf(n));
            casilla.getBoton().setStyle("-fx-background-color: #b7f7b7; -fx-font-size: 16px;");
        }
        casilla.getBoton().setDisable(true);
    }

    private void mostrarMinas() {
        for (Casilla[] fila : juego.getTablero()) {
            for (Casilla c : fila) {
                if (c.isMina()) {
                    c.getBoton().setText("💣");
                    c.getBoton().setStyle("-fx-background-color: #ff6b6b; -fx-font-size: 16px;");
                }
            }
        }
    }

    private boolean verificarVictoria() {
        int totalSeguras = juego.getFilas() * juego.getColumnas() - contarMinas();
        return casillasDescubiertas == totalSeguras;
    }

    private int contarMinas() {
        int count = 0;
        for (Casilla[] fila : juego.getTablero()) {
            for (Casilla c : fila) {
                if (c.isMina()) count++;
            }
        }
        return count;
    }

    private void deshabilitarTablero() {
        for (Casilla[] fila : juego.getTablero()) {
            for (Casilla c : fila) {
                c.getBoton().setDisable(true);
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public VBox getRoot() {
        return root;
    }
}
