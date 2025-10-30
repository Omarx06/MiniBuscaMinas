package com.example.minibuscaminas.minibuscaminas;

import javafx.scene.control.Button;

public class Casilla {
    private int fila;
    private int columna;
    private boolean mina;
    private boolean descubierta;
    private int minasAdyacentes;
    private Button boton;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.mina = false;
        this.descubierta = false;
        this.minasAdyacentes = 0;
        boton = new Button(" ");
        boton.setPrefSize(45, 45);
        boton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
    }

    public Button getBoton() {
        return boton;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
