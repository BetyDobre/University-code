package model;

public class Client {
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

    public Client(String nume, String parola){
        this.nume = nume;
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
