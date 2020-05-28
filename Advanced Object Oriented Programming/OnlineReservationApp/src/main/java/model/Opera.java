package model;

public class Opera extends Spectacol {
    private String numeOpera;
    private String limba;

    public String getNumeOpera() {
        return numeOpera;
    }

    public void setNumeOpera(String numeOpera) {
        this.numeOpera = numeOpera;
    }

    public String getLimba() {
        return limba;
    }

    public void setLimba(String limba) {
        this.limba = limba;
    }

    public Opera(String nume, String oras, int ora, int durata, int pret, String data, String numeOpera, String limba) {
        super(nume, oras, ora, durata, pret, data);
        this.numeOpera = numeOpera;
        this.limba = limba;
    }

    public Opera(){};

    @Override
    public String toString() {
        return "Opera{" +
                "numeOpera='" + numeOpera + '\'' +
                ", limba='" + limba + '\'' +
                ", data='" + data + '\'' +
                ", nume='" + nume + '\'' +
                ", oras='" + oras + '\'' +
                ", ora=" + ora +
                ", durata=" + durata +
                ", pret=" + pret +
                '}';
    }
}
