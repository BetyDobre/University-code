package model;

public abstract class Spectacol {
    protected String data;
    protected String nume;
    protected String oras;
    protected int ora;
    protected int durata;
    protected int pret;

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getData() {
        return data;
    }

    public String getNume() {
        return nume;
    }

    public String getOras() {
        return oras;
    }

    public int getOra() {
        return ora;
    }

    public int getDurata() {
        return durata;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Spectacol(String nume, String oras, int ora, int durata, int pret, String data) {
        this.data = data;
        this.nume = nume;
        this.oras = oras;
        this.ora = ora;
        this.durata = durata;
        this.pret = pret;
    }

    public Spectacol(){};
}
