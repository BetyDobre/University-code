package repositories.opera;

import model.Opera;

import java.util.ArrayList;

public class ArrayOperaRepository {
    ArrayList<Opera> opere = new ArrayList<>();

    public void addSpectacol(Opera opera) {
        opere.add(opera);
    }

    public void findSpectacolByName(String nume) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getNume().equals(nume)) {
                System.out.println(opere.get(i));
            }
        }
    }

    public void findSpectacolByData(String data) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getData().equals(data)) {
                System.out.println(opere.get(i));
            }
        }
    }

    public void findSpectacolByNumeOpera(String numeOpera) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getNumeOpera().equals(numeOpera)) {
                System.out.println(opere.get(i));
            }
        }
    }

    public void findSpectacolByOra(int ora) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getOra() == ora) {
                System.out.println(opere.get(i));
            }
        }
    }

    public void afisSpectacole() {
        for (int i = 0; i < opere.size(); i++) {
                System.out.println(opere.get(i));
        }
    }

    public void findSpectacol(int ora, String data, String nume) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getNume().equals(nume) && opere.get(i).getData().equals(data) && opere.get(i).getOra() == ora) {
                System.out.println(opere.get(i));
            }
        }
    }

    public void deleteSpectacolByNume(String nume) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getNume().equals(nume)) {
                opere.remove(i);
            }
        }
    }

    public void updateOraSpectacol(int ora_veche, int ora) {
        for (int i = 0; i < opere.size(); i++) {
            if (opere.get(i).getOra() == ora_veche) {
                opere.get(i).setOra(ora);
            }
        }
    }

}
