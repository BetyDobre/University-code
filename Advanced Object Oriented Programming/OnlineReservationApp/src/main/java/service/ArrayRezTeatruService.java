package service;

import model.*;
import repositories.theater.ArrayTeatruRepository;

import java.util.ArrayList;

public class ArrayRezTeatruService {

    ArrayList<Rezervare> rezervari = new ArrayList<>();
    private ArrayTeatruRepository teatruRepository;

    public ArrayRezTeatruService() {teatruRepository = new ArrayTeatruRepository();}

    public void rezervare(Client client, ArrayLoginService loginService, Teatru teatru, LocTeatruOpera loc){
        if (loginService.login(client)){
            if(loc.isLiber()){

                Rezervare rez = new Rezervare(rezervari.size(), client.getNume(), teatru.getNume(), teatru.getData(), teatru.getOra(), loc.getNrLoc(), loc.getZona());
                rezervari.add(rez);
                loc.setLiber(false);

                System.out.println("Rezervarea a fost efectuata cu succes !");
            }
            else {
                System.out.println("Locul este ocupat !");
            }
        }
    }

    public void afisRezervari() {
        for (Rezervare i: rezervari) {
            System.out.println(i);
        }
    }
}
