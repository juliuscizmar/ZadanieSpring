package com.example.springboot.Models;

public class Produkt {
    private final String nazov;
    private final String popis;
    private final int cena;

    public Produkt(final String nazov, final String popis, final int cena) {
        this.nazov = nazov;
        this.popis = popis;
        this.cena = cena;
    }

    public String getNazov() {
        return this.nazov;
    }

    public String getPopis() {
        return this.popis;
    }

    public int getCena() {
        return this.cena;
    }
}
