package com.example.springboot.Models;

import java.util.ArrayList;
import java.util.List;

public class Objednavka {
    private final String nazov;
    private final String popis;
    private final Float cenaModifier;
    private final String menoUsera;
    private final ArrayList<Produkt> zoznamProduktov;

    private Float celkovaCena;

    public Objednavka(final String nazov, final String popis, final Integer cenaModifier, final String menoUsera, final ArrayList<Produkt> zoznamProduktov) {
        this.nazov = nazov;
        this.popis = popis;
        this.cenaModifier = (float)cenaModifier;
        this.menoUsera = menoUsera;
        this.zoznamProduktov = zoznamProduktov;    

        this.celkovaCena = 0.0f;
        for (Produkt produkt : zoznamProduktov) {
            this.celkovaCena += produkt.getCena();
        }
        this.celkovaCena += this.celkovaCena * -(this.cenaModifier / 100);
        if (this.celkovaCena < 0.0f) this.celkovaCena = 0.0f;
    }

    public Float getCenaModifier() {
        return cenaModifier;
    }

    public Float getCena() {
        return celkovaCena;
    }

    public String getNazov() {
        return nazov;
    }

    public String getPopis() {
        return popis;
    }

    public String getMenoUsera() {
        return menoUsera;
    }
    
    public List<Produkt> getZoznamProduktov() {
        return zoznamProduktov;
    }
}
