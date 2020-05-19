package model;

public class Rezervare {
    private int nr;
    private String numeClient;
    private String numeSpectacol;
    private String data;
    private int ora;
    private int loc;
    private String sector;

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getNumeSpectacol() {
        return numeSpectacol;
    }

    public void setNumeSpectacol(String numeSpectacol) {
        this.numeSpectacol = numeSpectacol;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Rezervare(int nr, String numeClient, String numeSpectacol,String data, int ora, int loc, String sector) {
        this.nr = nr;
        this.numeClient = numeClient;
        this.numeSpectacol = numeSpectacol;
        this.data = data;
        this.ora = ora;
        this.loc = loc;
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "{Rezervare: " +
                " numar rezervare : " + nr +
                " Nume Client: " + numeClient+
                " Nume Spectacol: " + numeSpectacol +
                " Data : " + data +
                " Ora: " + ora +
                " Loc: " + loc +
                " Sector loc: " + sector +
                '}';
    }
    public Rezervare(){};
}
