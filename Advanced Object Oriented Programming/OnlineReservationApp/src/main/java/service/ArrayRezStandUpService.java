package service;

import model.*;
import repositories.standup.ArrayStandUpRepository;

import java.util.ArrayList;

public class ArrayRezStandUpService {
    ArrayList<Rezervare> rezervari = new ArrayList<>();
    private ArrayStandUpRepository standupRepository;

    public ArrayRezStandUpService() {standupRepository = new ArrayStandUpRepository();}

    public void rezervare(Client client, ArrayLoginService loginService, StandUp standup, Loc loc){
        if (loginService.login(client)){
            if(loc.isLiber()){

                Rezervare rez = new Rezervare(rezervari.size(), client.getNume(), standup.getNume(), standup.getData(), standup.getOra(), loc.getNrLoc(), "-");
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
