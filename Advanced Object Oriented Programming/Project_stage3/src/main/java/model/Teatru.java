package model;

import java.util.Set;

public class Teatru extends Spectacol {
    private String numeTeatru;
    String actori;

    public String getNumeTeatru() {
        return numeTeatru;
    }

    public void setNumeTeatru(String numeTeatru) {
        this.numeTeatru = numeTeatru;
    }

    public String getActori() {
        return actori;
    }

    public void setActori(String actori) {
        this.actori = actori;
    }

    public Teatru(String nume, String oras, int ora, int durata, int pret, String data, String numeTeatru, String actori) {
        super(nume, oras, ora, durata, pret, data);
        this.numeTeatru = numeTeatru;
        this.actori = actori;
    }

    public Teatru(){};

    @Override
    public String toString() {
        return "Teatru{" +
                "numeTeatru='" + numeTeatru + '\'' +
                ", actori='" + actori + '\'' +
                ", data='" + data + '\'' +
                ", nume='" + nume + '\'' +
                ", oras='" + oras + '\'' +
                ", ora=" + ora +
                ", durata=" + durata +
                ", pret=" + pret +
                '}';
    }
}
