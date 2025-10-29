package com.example.minibuscaminas.minibuscaminas;

public class Juego {
    private int filas;
    private int columnas;
    private int minas;
    private Casilla[] tablero; // matriz que representa el tablero

    public Juego(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas;
        tablero = new Casilla[filas][columnas];
//TODO: inicializar la matriz con objetos Casilla
//TODO: Colocar minas aleatoriamente
//TODO: Calcular minas adyacentes para cada casilla
    }

    //TODO: Método para descubrir casilla
    public boolean descubrirCasilla(int f, int c) {
        return true; //placeholder
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
