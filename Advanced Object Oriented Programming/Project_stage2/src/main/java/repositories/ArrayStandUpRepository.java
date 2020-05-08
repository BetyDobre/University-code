package repositories;


import model.StandUp;

import java.util.ArrayList;

public class ArrayStandUpRepository {
    ArrayList<StandUp> standups = new ArrayList<>();

    public void addSpectacol(StandUp standup) {
        standups.add(standup);
    }

    public void findSpectacolByName(String nume) {
        for (int i = 0; i < standups.size(); i++) {
            if (standups.get(i).getNume().equals(nume)) {
                System.out.println(standups.get(i));
            }
        }
    }

    public void findSpectacolByData(String data) {
        for (int i = 0; i < standups.size(); i++) {
            if (standups.get(i).getData().equals(data)) {
                System.out.println(standups.get(i));
            }
        }
    }

    public void findSpectacolByOra(int ora) {
        for (int i = 0; i < standups.size(); i++) {
            if (standups.get(i).getOra() == ora) {
                System.out.println(standups.get(i));
            }
        }
    }

    public void afisSpectacole() {
        for (int i = 0; i < standups.size(); i++) {
            System.out.println(standups.get(i));
        }
    }

    public void findSpectacol(int ora, String data, String nume) {
        for (int i = 0; i < standups.size(); i++) {
            if (standups.get(i).getNume().equals(nume) && standups.get(i).getData().equals(data) && standups.get(i).getOra() == ora) {
                System.out.println(standups.get(i));
            }
        }
    }

    public void deleteSpectacolByNume(String nume) {
        for (int i = 0; i < standups.size(); i++) {
            if (standups.get(i).getNume().equals(nume)) {
                standups.remove(i);
            }
        }
    }

    public void updateOraSpectacol(int ora_veche, int ora) {
        for (int i = 0; i < standups.size(); i++) {
            if (standups.get(i).getOra() == ora_veche) {
                standups.get(i).setOra(ora);
            }
        }
    }

}
