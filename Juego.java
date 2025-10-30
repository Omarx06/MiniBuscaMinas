package com.example.minibuscaminas.minibuscaminas;

import java.util.Random;

public class Juego {
    private int filas;
    private int columnas;
    private int minas;
    private Casilla[][] tablero;

    public Juego(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        tablero = new Casilla[filas][columnas];
        inicializarCasillas();
        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void inicializarCasillas() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                tablero[f][c] = new Casilla(f, c);
            }
        }
    }

    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < minas) {
            int f = random.nextInt(filas);
            int c = random.nextInt(columnas);
            if (!tablero[f][c].isMina()) {
                tablero[f][c].setMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (!tablero[f][c].isMina()) {
                    int contador = 0;
                    for (int df = -1; df <= 1; df++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            int nf = f + df;
                            int nc = c + dc;
                            if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas) {
                                if (tablero[nf][nc].isMina()) contador++;
                            }
                        }
                    }
                    tablero[f][c].setMinasAdyacentes(contador);
                }
            }
        }
    }

    public boolean descubrirCasilla(int f, int c) {
        Casilla casilla = tablero[f][c];
        if (casilla.isDescubierta()) return true;
        casilla.setDescubierta(true);
        return !casilla.isMina();
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

    public int getMinas() {
        return minas;
    }
}

    public int getColumnas() {
        return columnas;
    }
}

