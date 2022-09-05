package com.example.varianza_desviacionestandar;

public class Contenido {
    private int num;
    private double dato;
    public Contenido(){}

    public Contenido(int num, double dato) {
        this.num = num;
        this.dato = dato;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDato(double dato) {
        this.dato = dato;
    }

    public int getNum() {
        return num;
    }

    public double getDato() {
        return dato;
    }
}
