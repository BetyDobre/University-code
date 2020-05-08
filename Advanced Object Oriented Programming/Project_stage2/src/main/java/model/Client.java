package model;

public class Client{
    private int id;
    private String nume;
    private String parola;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Client(int id, String nume, String parola) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
    }

    public Client() {}

    @Override
    public String toString(){
       return  " {Id : " + id +
                " Nume : " + nume +
                '}';
    }
}
