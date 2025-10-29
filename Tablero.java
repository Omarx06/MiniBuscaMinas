package com.example.minibuscaminas.minibuscaminas;

import javafx.scene.layout.GridPane;

public class Tablero {
    private GridPane grid;
    private Juego juego;

    public Tablero(int filas, int columnas, int minas) {
        grid = new GridPane();
        juego = newJuego(filas, columnas, minas);

//TODO: Inicializar botones en el GridPane
//TODO: Asignar eventos a cada botón
//TODO: Mostrar minas y número de minas adyacentes al hacer clic
    }

    public GridPane getGrid() {
        return grid;
    }
}
