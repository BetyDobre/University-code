package repositories;

import model.Teatru;

import java.util.ArrayList;

public class ArrayTeatruRepository {
    ArrayList<Teatru> teatre = new ArrayList<>();

    public void addSpectacol(Teatru teatru) {
        teatre.add(teatru);
    }

    public void findSpectacolByName(String nume) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getNume().equals(nume)) {
                System.out.println(teatre.get(i));
            }
        }
    }

    public void findSpectacolByData(String data) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getData().equals(data)) {
                System.out.println(teatre.get(i));
            }
        }
    }

    public void findSpectacolByNumeOpera(String numeOpera) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getNumeTeatru().equals(numeOpera)) {
                System.out.println(teatre.get(i));
            }
        }
    }

    public void findSpectacolByOra(int ora) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getOra() == ora) {
                System.out.println(teatre.get(i));
            }
        }
    }

    public void afisSpectacole() {
        for (int i = 0; i < teatre.size(); i++) {
            System.out.println(teatre.get(i));
        }
    }

    public void findSpectacol(int ora, String data, String nume) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getNume().equals(nume) && teatre.get(i).getData().equals(data) && teatre.get(i).getOra() == ora) {
                System.out.println(teatre.get(i));
            }
        }
    }

    public void deleteSpectacolByNume(String nume) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getNume().equals(nume)) {
                teatre.remove(i);
            }
        }
    }

    public void updateOraSpectacol(int ora_veche, int ora) {
        for (int i = 0; i < teatre.size(); i++) {
            if (teatre.get(i).getOra() == ora_veche) {
                teatre.get(i).setOra(ora);
            }
        }
    }

}
