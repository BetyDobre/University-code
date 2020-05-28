package model;


public class StandUp extends Spectacol {
    private String comedianti;

    public String getComedianti() {
        return comedianti;
    }

    public void setComedianti(String comedianti) {
        this.comedianti = comedianti;
    }

    public StandUp(String nume, String oras, int ora, int durata, int pret, String data,  String comedianti) {
        super(nume, oras, ora, durata, pret, data);
        this.comedianti = comedianti;
    }

    public StandUp(){};

    @Override
    public String toString() {
        return "StandUp{" +
                "comedianti='" + comedianti + '\'' +
                ", data='" + data + '\'' +
                ", nume='" + nume + '\'' +
                ", oras='" + oras + '\'' +
                ", ora=" + ora +
                ", durata=" + durata +
                ", pret=" + pret +
                '}';
    }
}
